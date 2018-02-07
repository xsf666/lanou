package com.lanou.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lanou.bean.ContractBean;
import com.lanou.bean.jOutProductBean;
import com.lanou.entity.ContractHis;
import com.lanou.entity.ContractProductHis;
import com.lanou.entity.ExtCproductHis;
import com.lanou.mapper.ContractHisMapper;
import com.lanou.mapper.ContractProductHisMapper;
import com.lanou.mapper.ExtCproductHisMapper;
import com.lanou.service.ContractService;
import com.lanou.util.ExcelUtil;

@Controller
@RequestMapping("/ContractHis")
public class ContractHisController {
	@Resource
	private ContractHisMapper contractHisMapper;
	@Resource
	private ContractProductHisMapper contractProductHisMapper;
	@Resource
	private ExtCproductHisMapper extCproductHisMapper;
	@Resource
	private ContractService contractService;
	private int EXTNUM;
	private int PNUM;

	@RequestMapping("/jContractList")
	public ModelAndView jContractList() {
		ModelAndView m = new ModelAndView("cargo/contracthis/jContractList");
		List<ContractBean> list = contractHisMapper.selectAll();
		for (ContractBean c : list) {
			String contractID = c.getCONTRACT_ID();
			// 货物数量
			int totalProductNum = 0;
			// 附件数量
			int totalExpCount = 0;
			if (!StringUtils.isEmpty(contractID)) {
				List<ContractProductHis> ps = contractProductHisMapper.selectProductHisByContractHisID(contractID);
				for (ContractProductHis cp : ps) {
					if (cp != null) {
						if (cp.getCNUMBER() != null) {
							totalProductNum += cp.getCNUMBER();
							String contract_PRODUCT_ID = cp.getCONTRACT_PRODUCT_ID();
							// 根据货物id 获取附件列表
							List<ExtCproductHis> ep = extCproductHisMapper
									.selectExtHisByProductHisId(contract_PRODUCT_ID);
							for (ExtCproductHis p : ep) {
								if (p.getCNUMBER() != null) {
									totalExpCount += p.getCNUMBER();
								}
							}
						}
					}
				}
				EXTNUM = totalProductNum;
				PNUM = totalExpCount;
				c.setEXTNUM(totalProductNum);
				c.setPNUM(totalExpCount);
			}
		}
		m.addObject("dataList", list);
		return m;
	}

	/**
	 * 进入查看合同历史界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toview.action")
	public ModelAndView toview(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/contracthis/jContractView");
		String parameter = request.getParameter("id");

		ContractHis c = contractHisMapper.selectByPrimaryKey(parameter);
		List<ContractProductHis> cph = contractProductHisMapper.selectProductHisByContractHisID(parameter);
		List<ExtCproductHis> ech = null;
		for (ContractProductHis cc : cph) {
			ech = extCproductHisMapper.selectExtHisByProductHisId(cc.getCONTRACT_PRODUCT_ID());
		}
		m.addObject("obj", c);
		// 货物
		m.addObject("contractProducts", cph);
		// 附件
		m.addObject("extCproducts", ech);
		return m;
	}
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		String parameter = request.getParameter("id");
		List<ContractBean> list = contractHisMapper.selectAll();
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
