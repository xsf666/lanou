package com.lanou.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lanou.bean.LoginLogBean;
import com.lanou.service.ContractPrductService;
import com.lanou.service.ContractService;
import com.lanou.service.LoginLogService;

@Controller
public class ChatController {
	@Resource
	private ContractService contractService;
	@Resource
	private ContractPrductService contractPrductService;
	@Resource
	private LoginLogService loginLogService;

	// 进入厂家销售记录 获取数据
	@RequestMapping("/chat/factoryLog")
	public String factoryLog() {
		return "/cargo/chat/factoryLog";
	}

	/**
	 * 厂家销售记录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/chat/factoryRecord")
	public Object getFactoryRecord() {
		return contractService.selectFactoryLog();
	}

	// 进入产品销量前十 获取数据
	@RequestMapping("/chat/productTen")
	public String productTen() {
		return "/cargo/chat/productTen";
	}

	@ResponseBody
	@RequestMapping("/chat/productRecord")
	public Object getproductRecord() {
		return contractPrductService.selectByTen();
	}

	// 登录记录界面
	@RequestMapping("/chat/LoginLog")
	public ModelAndView LoginLog(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/chat/LoginLog");
		List<LoginLogBean> list = loginLogService.selectLoginYear();
		m.addObject("list", list);
		return m;
	}

	@ResponseBody
	@RequestMapping("/chat/LoginLogRecord")
	public Object getLoginLogRecord(@RequestParam("year") String year) {
		return loginLogService.selectLoginYearTime(year);

	}
}