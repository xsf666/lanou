package com.lanou.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lanou.entity.Export;
import com.lanou.entity.ExtCproduct;
import com.lanou.entity.PackingList;
import com.lanou.service.ExportService;
import com.lanou.service.PackingListService;
import com.lanou.util.UtilFuns;

@Controller
@RequestMapping("/PackingList")
public class PackingListController {
	@Resource
	private PackingListService packingListService;

	@Resource
	private ExportService exportService;

	/**
	 * 进入装箱单列表列表
	 * @return
	 */
	@RequestMapping("/jPackingListList")
	public ModelAndView jPackingListList() {
		ModelAndView m = new ModelAndView("cargo/packinglist/jPackingListList");
		List<PackingList> list = packingListService.selectAll();
		m.addObject("dataList", list);
		return m;
	}

	/**
	 * 浏览装箱单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toview.action", method = RequestMethod.POST)
	public ModelAndView toview(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/packinglist/jPackingListView");
		String parameter = request.getParameter("id");
		PackingList p = packingListService.selectById(parameter);
		request.setAttribute("divData", p.getEXPORT_NOS());
		m.addObject("obj", p);
		return m;
	}

	/**
	 * 进入修改装箱单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toupdate.action", method = RequestMethod.POST)
	public ModelAndView toupdate(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/packinglist/jPackingListUpdate");
		String parameter = request.getParameter("id");
		PackingList p = packingListService.selectByPrimaryKey(parameter);
		request.setAttribute("divData", p.getEXPORT_NOS());
		m.addObject("obj", p);
		return m;
	}

	/**
	 * 修改装箱单
	 * 
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping("/update.action")
	public Object update(PackingList p, HttpServletRequest request) {
		p.setPACKING_LIST_ID(request.getParameter("id"));
		int row = packingListService.updateByPrimaryKeySelective(p);
		System.out.println(row);
		if (row > 0) {
			return "redirect:/PackingList/jPackingListList";
		} else {
			return "cargo/packinglist/jPackingListUpdate";
		}

	}


	/**
	 * 删除多个 删除装箱单里的出口报运单
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteAll")
	public String deleteAll(String[] id, HttpServletRequest request) {
		// String PACKING_LIST_ID = request.getParameter("id");
		for (String s : id) {
			PackingList p = packingListService.selectByPrimaryKey(s);
			String[] s1 = p.getEXPORT_IDS().split(",");
			for (String string : s1) {
				exportService.deleteByPrimaryKey(string);
			}
			packingListService.deleteByPrimaryKey(s);
		}
		return "redirect:/PackingList/jPackingListList";
	}

	/**
	 * 上报
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/submit.action")
	public String submit(String[] id, HttpServletRequest request) {
		if (id.length != 0) {
			for (String d : id) {
				PackingList p = packingListService.selectByPrimaryKey(d);
				p.setSTATE((short) 1);
				packingListService.updateByPrimaryKeySelective(p);
			}
		}
		return "redirect:/PackingList/jPackingListList";
	}
	/**
	 * 新增装箱单
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/tocreate.action")
	public ModelAndView tocreate(String[] id, HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/packinglist/jPackingListCreate");
		List<Export> extportList = exportService.BoxUp(id);
		m.addObject("extportList", extportList);
		return m;
	}

	/**
	 * 添加装箱
	 * 
	 * @param p
	 * @param EXPORT_ID
	 * @return
	 */
	@RequestMapping("/insert")
	public ModelAndView add(PackingList p, String[] EXPORT_ID) {
		ModelAndView m = null;
		Export e = null;
		// nos 发票号集合
		StringBuffer sb = new StringBuffer();
		for (String id : EXPORT_ID) {
			e = exportService.selectByPrimaryKey(id);
			e.setSTATE((short) 2);
			exportService.updateByPrimaryKeySelective(e);
			sb.append(e.getCUSTOMER_CONTRACT() + " ");
		}
		p.setEXPORT_NOS(sb.toString().trim());
		// ids 合同编号集合
		p.setEXPORT_IDS(UtilFuns.joinStr(EXPORT_ID, ","));
		p.setPACKING_LIST_ID(UUID.randomUUID().toString());
		p.setSTATE((short) 0);
		int row = packingListService.insertSelective(p);
		System.out.println("**************************" + row);
		if (row > 0) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败");
		}
		return m = new ModelAndView(new RedirectView("jPackingListList"));
	}
}
