package com.lanou.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lanou.bean.PageBean;
import com.lanou.entity.Factory;
import com.lanou.service.FactoryService;
import com.lanou.util.Constants;
import com.lanou.util.ExcelUtil;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping("/factory")
public class FactoryController {

	@Resource
	private FactoryService factoryService;

	private int pageSize;

	/**
	 * 进入厂家列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/baseinfoLeft", method = RequestMethod.GET)
	public ModelAndView baseinfoLeft(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("baseinfo/factory/jFactoryList");
		int type = -1;
		int pageNo = 1;
		// 从页面获取参数
		String f_type = request.getParameter("f_type");
		// 非空验证
		if (!StringUtils.isNullOrEmpty(f_type)) {
			type = Integer.parseInt(f_type.trim());
		}
		String condition = request.getParameter("f_conditionStr");
		String number = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		if (!StringUtils.isNullOrEmpty(number)) {
			pageNo = Integer.parseInt(number);
		}
		int size = Constants.PAGESIZE;
		if (!StringUtils.isNullOrEmpty(pageSize)) {
			size = Integer.parseInt(pageSize);
			this.pageSize = size;
		}

		PageBean<Factory> page = factoryService.pageFactory(condition, type, pageNo, size);
		List<Factory> list = page.getList();
		System.out.println(list.size() + "**********************");

		m.addObject("factoryList", list);
		m.addObject("TotalCount", page.getTotalCount());
		m.addObject("pageNo", page.getPageNo());
		m.addObject("pageSize", page.getPageSize());
		// m.addObject("pageSize", list.size());
		m.addObject("totalPage", page.getTotalPage());

		m.addObject("f_type", f_type);
		m.addObject("f_conditionStr", condition);

		// 显示条数下拉选数组
		m.addObject("pageArray", new int[] { 10, 50, 100 });

		return m;
	}
	/**
	 * 工厂信息详情
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("baseinfo/factory/jFactoryDetail");
		String id = request.getParameter("id");
		System.out.println("ID" + id + "***********************");
		Factory factory = factoryService.selectFactoryByID(id);
		m.addObject("obj", factory);

		return m;
	}

	/**
	 * 删除多个工厂
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	public Object deleteAll(String[] id, HttpServletRequest request) {
		for (String string : id) {
			int row = factoryService.deleteFactoryByID(string);
			System.out.println("deleteAll row*******" + row);
		}

		return "redirect:/factory/baseinfoLeft";
	}

	/**
	 * 删除工厂
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Object delete(HttpServletRequest request) {
		String parameter = request.getParameter("id");
		int row = factoryService.deleteFactoryByID(parameter);
		System.out.println("delete row*******" + row);
		return "redirect:/factory/baseinfoLeft";
	}

	/**
	 * 进入添加工厂信息
	 * 
	 * @param f
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Factory f, HttpServletRequest request) {
		ModelAndView m1 = new ModelAndView("baseinfo/factory/jFactoryCreate");
		return m1;
	}

	/**
	 * 添加工厂信息
	 * 
	 * @param f
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(Factory f, HttpServletRequest request) {
		f.setCREATE_TIME(new Date());
		int row = factoryService.insertSelective(f);
		if (row > 0) {
			return "redirect:/factory/baseinfoLeft";
		}
		return "baseinfo/factory/jFactoryCreate";

	}

	/**
	 * 进入修改工厂页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public ModelAndView tooUpdate(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("baseinfo/factory/jFactoryUpdate");
		String id = request.getParameter("id");
		System.out.println("ID" + id + "***********************");
		Factory factory = factoryService.selectFactoryByID(id);
		// 把实体添加到模型视图中,一起返回给视图
		m.addObject("obj", factory);
		return m;

	}

	/**
	 * 修改工厂
	 * 
	 * @param f
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(Factory f, HttpServletRequest request) {
		f.setFACTORY_ID(request.getParameter("id"));
		int row = factoryService.updateByPrimaryKeySelective(f);
		if (row > 0) {
			return "redirect:/factory/baseinfoLeft";
		}
		return "baseinfo/factory/jFactoryUpdate";

	}

	/**
	 * 导出Excel
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		int type = -1;
		int pageNo = 1;
		// 从页面获取参数
		String f_type = request.getParameter("f_type");
		// //非空验证
		if (!StringUtils.isNullOrEmpty(f_type)) {
			type = Integer.parseInt(f_type.trim());
		}
		String condition = request.getParameter("f_conditionStr");
		String number = request.getParameter("pageNo");

		if (!StringUtils.isNullOrEmpty(number)) {
			pageNo = Integer.parseInt(number);
		}
		PageBean<Factory> page = factoryService.pageFactory(condition, type, pageNo, this.pageSize);
		List<Factory> list = page.getList();

		Map<String, String> headMap = new HashMap<>();
		headMap.put("fACTORY_ID", "id");
		headMap.put("fULL_NAME", "全称");
		headMap.put("fACTORY_NAME", "简称");
		headMap.put("cONTACTS", "联系人");
		headMap.put("iNSPECTOR", "验货员");
		headMap.put("pHONE", "联系电话");
		System.out.println(JSON.toJSONString(list));
		JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
		ExcelUtil.downloadExcelFile("工厂信息", headMap, jsonArray, response);
	}

}
