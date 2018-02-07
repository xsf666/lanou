package com.lanou.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.lf5.util.StreamUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lanou.entity.Export;
import com.lanou.entity.ExportProduct;
import com.lanou.entity.ExtEproduct;
import com.lanou.mapper.ExportProductMapper;
import com.lanou.mapper.ExtEproductMapper;
import com.lanou.service.ExportService;

@Controller
@RequestMapping("/Export")
public class ExportController {
	@Resource
	private ExportService exportService;
	@Resource
	private ExportProductMapper exportProductMapper;
	@Resource
	private ExtEproductMapper extEproductMapper;
	/**
	 * 进入出口报运列表
	 * @return
	 */
	@RequestMapping("/jExportList")
	public ModelAndView jExportList() {
		ModelAndView m = new ModelAndView("cargo/export/jExportList");
		List<Export> list = exportService.selectAll();

		for (Export e : list) {
			String Export_ID = e.getEXPORT_ID();
			/*
			 * 货物数量
			 */
			int totalProductNum = 0;
			/**
			 * 附件数量
			 */
			int totalExpCount = 0;
			if (!StringUtils.isEmpty(Export_ID)) {
				List<ExportProduct> ep = exportProductMapper.selectProductByExportId(Export_ID);
				for (ExportProduct ept : ep) {
					if (ept != null) {
						totalProductNum += ept.getCNUMBER();
						String export_PRODUCT_ID = ept.getEXPORT_PRODUCT_ID();
						// 根据货物id获取附件列表
						List<ExtEproduct> eep = extEproductMapper.selectExteproductByProductId(export_PRODUCT_ID);
						for (ExtEproduct p : eep) {
							totalExpCount += p.getCNUMBER();
						}
					}
				}
			}
			e.setEXTNUM(totalProductNum);
			e.setPNUM(totalExpCount);
		}

		m.addObject("dataList", list);
		return m;
	}
	/**
	 * 进入修改出口报运
	 * @param request
	 * @return
	 */
	@RequestMapping("/toupdate.action")
	public ModelAndView toupdate(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/export/jExportUpdate");
		String parameter = request.getParameter("id");
		Export e = exportService.selectByPrimaryKey(parameter);
		System.out.println("************" + e);
		m.addObject("obj", e);
		return m;

	}
	/**
	 * 修改出口报运
	 * @param e
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public Object update(Export e, HttpServletRequest request) {
		e.setEXPORT_ID(request.getParameter("id"));
		int row = exportService.updateByPrimaryKeySelective(e);
		System.out.println("****************" + row);
		if (row > 0) {
			return "redirect:/Export/jExportList";
		} else {
			return "cargo/export/jExportUpdate";
		}
	}

	@RequestMapping("/deleteBatch.action")
	public Object delete(HttpServletRequest request) {
		String parameter = request.getParameter("id");
		int row = exportService.deleteByPrimaryKey(parameter);
		if (row > 0) {
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}
		return "redirect:/Export/jExportList";
	}
}
