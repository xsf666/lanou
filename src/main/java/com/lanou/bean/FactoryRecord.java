package com.lanou.bean;

import java.io.Serializable;

public class FactoryRecord implements Serializable {
	private String factory_name;
	private Integer countnum;

	public String getFactory_name() {
		return factory_name;
	}

	public void setFactory_name(String factory_name) {
		this.factory_name = factory_name;
	}

	public Integer getCountnum() {
		return countnum;
	}

	public void setCountnum(Integer countnum) {
		this.countnum = countnum;
	}

}
