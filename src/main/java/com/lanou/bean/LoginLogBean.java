package com.lanou.bean;

import java.io.Serializable;

public class LoginLogBean implements Serializable {
	private String yearTime;
	// ajax 传的json 首字母小写
	private String LoginNumber;

	public String getYearTime() {
		return yearTime;
	}

	public void setYearTime(String yearTime) {
		this.yearTime = yearTime;
	}

	public String getLoginNumber() {
		return LoginNumber;
	}

	public void setLoginNumber(String loginNumber) {
		LoginNumber = loginNumber;
	}

}
