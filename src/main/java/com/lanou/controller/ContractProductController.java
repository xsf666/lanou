package com.lanou.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lanou.entity.ContractProduct;
import com.lanou.entity.Factory;
import com.lanou.mapper.FactoryMapper;
import com.lanou.service.ContractPrductService;
import com.lanou.service.ExtCproductService;
import com.lanou.service.FactoryService;

@Controller
@RequestMapping("/contractProduct")
public class ContractProductController {
	@Resource
	private FactoryService  factoryService;
	@Resource
	private ContractPrductService   contractPrductService;
	@Resource
	private  FactoryMapper factoryMapper;
	@Resource
	private ExtCproductService extCproductService;
	
	private String Contractid;
	/**
	 * 新增货物信息视图
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tocreate")
	public ModelAndView tocreate(HttpServletRequest request ) {
		ModelAndView m = new ModelAndView("cargo/contract/jContractProductCreate");
		String parameter = request.getParameter("contractId");
		Contractid= parameter;
		List<ContractProduct> selectProductByContractID = contractPrductService.selectProductByContractID(parameter);
		List<Factory> factoryList = factoryService.selectAll();
		m.addObject("dataList",selectProductByContractID);
		m.addObject("factoryList",factoryList);
		m.addObject("contractId",parameter);

		return m;
	}
	
	/**
	 * 添加货物
	 * @param record
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/insert.action",method=RequestMethod.POST)
	public ModelAndView insert(ContractProduct record,HttpServletRequest  request ) {
		ModelAndView m = null;
		String url = "tocreate.action";
		record.setCONTRACT_PRODUCT_ID(UUID.randomUUID().toString());
		String factoryId = request.getParameter("FACTORY_ID");
		if(factoryId.length()!=0) {
			record.setFACTORY_NAME(factoryMapper.selectFactoryByID(request.getParameter("FACTORY_ID")).getFACTORY_NAME());	
		}
		record.setFACTORY_ID(factoryId);
		if(Contractid != null ) {
			record.setCONTRACT_ID(Contractid);
		}
		int row = contractPrductService.insert(record);
		if(row > 0) {
			System.out.println("添加成功!!!");
			m = new ModelAndView(new RedirectView(url+"?contractId="+request.getParameter("contractId")));
		}else {
			System.out.println("添加失败!!!");
		}
		return m;
	}
	/**
	 * 进入修改货物
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toupdate")
	public ModelAndView toUpdate(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/contract/jContractProductUpdate");
		String contractProductId = request.getParameter("contractProductId");
		System.out.println("contractProductId:"+contractProductId+" ********************************************");
		ContractProduct selectContractProductByProductId = contractPrductService.selectContractProductByProductId(contractProductId);
		List<Factory> factoryList = factoryMapper.selectAll();
		m.addObject("obj",selectContractProductByProductId);
		m.addObject("factoryList",factoryList);
		return m;
	}
	/**
	 * 修改货物
	 * @param record
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update")
	public ModelAndView update(ContractProduct record,HttpServletRequest request) {
		String url = "tocreate.action";
		ModelAndView m = new ModelAndView(new RedirectView(url+"?contractId="+Contractid));
		System.out.println("合同id"+Contractid+"***********************");
		record.setFACTORY_NAME(factoryMapper.selectFactoryByID(request.getParameter("factoryId")).getFACTORY_NAME());
		record.setCONTRACT_PRODUCT_ID(request.getParameter("CONTRACT_PRODUCT_ID"));
		int row = contractPrductService.updateContractProductByProductId(record);
		if(row > 0) {
			System.out.println("修改成功!!!");
		}else {
			System.out.println("修改失败!!!");
		}
		return m;
	}
	/*
	 * 删除货物
	 */
	@RequestMapping(value="/delete.action")
	public ModelAndView delete(HttpServletRequest request) {
		String url = "tocreate.action";
		ModelAndView m = new ModelAndView(new RedirectView(url+"?contractId="+Contractid));
		System.out.println("合同id"+Contractid+"***********************");
		String contractProductId = request.getParameter("productId");
		int row = contractPrductService.deleteContract_Product(contractProductId);
		if(row > 0) {
			System.out.println("删除货物成功!!!");
			int row1 = extCproductService.deleteExt_CproductByContractProductId(contractProductId);
			if(row1 >0 ) {
				System.out.println("删除附件成功!!!");
			}else {
				System.out.println("删除附件失败!!!");
			}
		}else {
			System.out.println("删除货物失败!!!");
		}
		return m;
	}
	
	
}
