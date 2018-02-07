package com.lanou.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lanou.bean.jOutProductBean;
import com.lanou.entity.ContractProduct;
import com.lanou.entity.ExtCproduct;
import com.lanou.entity.Factory;
import com.lanou.service.ContractPrductService;
import com.lanou.service.ExtCproductService;
import com.lanou.service.FactoryService;
import com.lanou.service.jOutProductService;
import com.lanou.util.ExcelUtil;

@Controller
@RequestMapping("/extCproduct")
public class ExtCproductController {
	@Resource
	private FactoryService factoryService;
	@Resource
	private ExtCproductService extCproductService;
	@Resource
	private ContractPrductService contractPrductService;
	@Autowired
	private jOutProductService joutProductService;
	public String Contractid;

	/**
	 * 进入附件页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/tocreate")
	public ModelAndView tocreate(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/contract/jExtCproductCreate");
		String id = request.getParameter("contractProductId");

		Contractid = id;
		// 所有附件 CONTRACT_PRODUCT_ID
		List<ExtCproduct> list = extCproductService.selectExtByProductId(id);
		//
		List<Factory> factoryList = factoryService.selectAll();

		m.addObject("CONTRACT_PRODUCT_ID", id);
		m.addObject("dataList", list);
		m.addObject("factoryList", factoryList);
		return m;
	}
	/**
	 * 添加附件
	 * @param e
	 * @param request
	 * @return
	 */
	@RequestMapping("/insert")
	public ModelAndView add(ExtCproduct e, HttpServletRequest request) {
		String url = "tocreate";
		ModelAndView m = new ModelAndView(new RedirectView(url + "?contractProductId=" + Contractid));
		String parameter = request.getParameter("factoryId");

		if (parameter.length() != 0) {
			// 根据id获取厂家姓名 然后放进入
			System.out.println(factoryService.selectFactoryByID(request.getParameter("factoryId")).getFACTORY_NAME());
			e.setFACTORY_NAME(factoryService.selectFactoryByID(request.getParameter("factoryId")).getFACTORY_NAME());
		}
		// 根据id获取厂家姓名 然后放进入
		e.setEXT_CPRODUCT_ID(UUID.randomUUID().toString());
		if (Contractid != null) {
			e.setCONTRACT_PRODUCT_ID(Contractid);
		}
		// factoryId

		int row = extCproductService.insert(e);
		if (row > 0) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败!!!");
		}
		return m;
	}
	/**
	 * 进入修改界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toupdate")
	public ModelAndView toupdate(HttpServletRequest request) {

		ModelAndView m = new ModelAndView("cargo/contract/jExtCproductUpdate");
		String contractProductId = request.getParameter("id");
		ExtCproduct e = extCproductService.selectByPrimaryKey(contractProductId);
		List<Factory> list = factoryService.selectAll();

		m.addObject("obj", e);
		m.addObject("factoryList", list);

		return m;
	}
	/**
	 * 修改附件
	 * @param e
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView update(ExtCproduct e, HttpServletRequest request) {

		String url = "tocreate";
		ModelAndView m = new ModelAndView(new RedirectView(url + "?contractProductId=" + Contractid));
		String parameter = request.getParameter("factoryId");
		if (parameter.length() != 0) {
			System.out.println(factoryService.selectFactoryByID(request.getParameter("factoryId")).getFACTORY_NAME());
			e.setFACTORY_NAME(factoryService.selectFactoryByID(request.getParameter("factoryId")).getFACTORY_NAME());
		}

		// 把修改后的名字重新给它
		e.setEXT_CPRODUCT_ID(request.getParameter("EXT_CPRODUCT_ID"));
		int row = extCproductService.updateByPrimaryKeySelective(e);

		if (row > 0) {
			System.out.println("修改成功");
		} else {
			System.out.println("修改失败");
		}

		return m;
	}
	/**
	 * 删除附件
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest request) {

		String url = "tocreate";
		ModelAndView m = new ModelAndView(new RedirectView(url + "?contractProductId=" + Contractid));
		System.out.println("附件ID:" + request.getParameter("id") + "*****************************************");
		int row = extCproductService.deleteByPrimaryKey(request.getParameter("id"));
		if (row > 0) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
		return m;

	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/return")
	public ModelAndView fanhui(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/contract/jContractProductCreate");
		String parameter = request.getParameter("contractId");
		String contractId = contractPrductService.selectContractProductByProductId(parameter).getCONTRACT_ID();
		List<ContractProduct> selectProductByContractID = contractPrductService.selectProductByContractID(contractId);
		List<Factory> factoryList = factoryService.selectAll();
		m.addObject("dataList", selectProductByContractID);
		m.addObject("factoryList", factoryList);
		m.addObject("contractId", contractId);
		return m;

	}
	/**
	 * 出货表月统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/jOutProduct")
	public ModelAndView outPriduct(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/contract/jOutProduct");
		String parameter = request.getParameter("inputDate");
		List<jOutProductBean> list = joutProductService.selectjOutProductByst(parameter);
		m.addObject("dataList", list);
		return m;

	}
	/**
	 * 打印成exc
	 * @param request
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		String parameter = request.getParameter("inputDate");
		List<jOutProductBean> list = joutProductService.selectjOutProductByst(parameter);
		Map<String, String> headMap = new HashMap<>();
		headMap.put("cONTRACT_PRODUCT_ID", "货物id");
		headMap.put("cUSTOM_NAME", "客户名称");
		headMap.put("cONTRACT_NO", "合同号");
		headMap.put("dELIVERY_PERIOD", "交货日期");
		headMap.put("sHIP_TIME", "船期");
		headMap.put("tRADE_TERMS", "贸易条款");
		headMap.put("fACTORY_NAME", "厂家名称");
		headMap.put("pRODUCT_NO", "货号");
		headMap.put("cNUMBER", "数量");
		System.out.println(JSON.toJSONString(list));
		JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
		System.out.println(jsonArray);
		ExcelUtil.downloadExcelFile("导出表", headMap, jsonArray, response);
	}

}
