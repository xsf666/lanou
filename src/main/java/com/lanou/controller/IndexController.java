package com.lanou.controller;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.lf5.util.StreamUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.druid.util.StringUtils;
import com.lanou.entity.LoginLog;
import com.lanou.entity.User;
import com.lanou.mapper.LoginLogMapper;
import com.lanou.service.UserService;

@Controller
public class IndexController {

	@Resource
	private UserService userService;
	@Resource
	private LoginLogMapper loginLogMapper;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		// 获取cookie
		String[] cookies = getCookie(request);
		if (cookies.length == 2) {
			request.setAttribute("name", cookies[0]);
			request.setAttribute("pwd", cookies[1]);

		}
		return "index";
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/fmain.action", method = RequestMethod.POST)
	public String fmain(HttpServletRequest request, HttpServletResponse response) {

		String[] cookies = getCookie(request);
		String username = null;
		String password = null;
		if (cookies[0] != null && cookies[1] != null) {
			username = cookies[0];
			password = cookies[1];
		} else {
			username = request.getParameter("userName");
			password = request.getParameter("password");
		}
		User u1 = userService.selectByName(username);
		User user = userService.login(username, password);

		if (u1 != null) {
			if (null != user) {

				boolean e = isExist(request);
				if (!e) {
					request.getSession().setAttribute("user", user);
					// 创建cookie
					Cookie name = new Cookie("userName", username);
					response.addCookie(name);
					Cookie pwd = new Cookie("password", password);
					response.addCookie(pwd);
				}
				return "redirect:rose";
			} else {
				request.setAttribute("loginFailed", 1);
				return "index";
			}
		} else {
			request.setAttribute("loginFailed", 2);
			return "index";
		}

	}

	public String[] getCookie(HttpServletRequest request) {
		String[] strs = new String[2];
		Cookie[] cs = request.getCookies();
		if (cs != null) {
			for (Cookie c : cs) {
				if (c.getName().equals("userName")) {
					// 获取账号
					strs[0] = c.getValue();
				}
				if (c.getName().equals("password")) {
					// 获取密码
					strs[1] = c.getValue();
				}
			}
		}

		return strs;
	}

	/**
	 * 判断cookie 里面是否存在用户名和密码
	 * 
	 * @return
	 */
	public boolean isExist(HttpServletRequest request) {
		// 不存在的
		boolean flag = false;
		Cookie[] cs = request.getCookies();
		int count = 0;// 记录比较次数
		if (cs != null) {

			for (Cookie c : cs) {
				if (c.getName().equals("userName")) {
					count++;
				}
				if (c.getName().equals("password")) {
					count++;
				}
			}
		}
		if (count == 2) {
			flag = true;
		}
		return flag;
	}

	@RequestMapping(value = "/title")
	public String title() {
		return "/home/title";
	}

	@RequestMapping(value = "/left")
	public String left() {
		return "/home/left";
	}

	@RequestMapping(value = "/main")
	public String main() {
		return "/home/olmsgList"; // 首页转向留言板
	}

	// 系统首页
	@RequestMapping("/fmain.action")
	public String first() {
		return "redirect:rose";
	}
	// 系统管理模块

	@RequestMapping("/sysadminMain.action")
	public String sysadminMain() {
		return "/sysadmin/main";
	}

	@RequestMapping("/sysadminLeft.action")
	public String sysadminLeft() {
		return "/sysadmin/left";
	}

	// 基础信息模块

	@RequestMapping("/baseinfoMain.action")
	public String baseinfoMain() {
		return "/baseinfo/main";
	}

	@RequestMapping("/baseinfoLeft.action")
	public String baseinfoLeft() {
		return "/baseinfo/left";
	}

	// 货运管理模块

	@RequestMapping("/cargoMain.action")
	public String cargoMain() {
		return "/cargo/main";
	}

	@RequestMapping("/cargoLeft.action")
	public String cargoLeft() {
		return "/cargo/left";
	}

	@RequestMapping("/home.action")
	public String logOut() {
		return "index";
	}

	@RequestMapping("/rose")
	public String fuck(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (null != user) {
			LoginLog loginLog = new LoginLog();
			loginLog.setLOGIN_LOG_ID(UUID.randomUUID().toString());
			loginLog.setLOGIN_NAME(user.getUSERNAME());
			loginLog.setIP_ADDRESS(request.getRemoteAddr());
			loginLog.setLOGIN_TIME(new Date());
			loginLogMapper.insertSelective(loginLog);
		}
		return "home/fmain";
	}

}
