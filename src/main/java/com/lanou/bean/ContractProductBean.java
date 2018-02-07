package com.lanou.bean;

import java.io.Serializable;
import java.util.List;

import com.lanou.entity.ContractProduct;
import com.lanou.entity.ExtCproduct;

public class ContractProductBean implements Serializable{
	private String product_no;
	
	public String getProduct_no() {
		return product_no;
	}

	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	private Integer countnum;
	public Integer getCountnum() {
		return countnum;
	}

	public void setCountnum(Integer countnum) {
		this.countnum = countnum;
	}
	private ContractProduct cp;
	
	private List<ExtCproduct> list;

	public ContractProduct getCp() {
		return cp;
	}

	public void setCp(ContractProduct cp) {
		this.cp = cp;
	}

	public List<ExtCproduct> getList() {
		return list;
	}

	public void setList(List<ExtCproduct> list) {
		this.list = list;
	}
	
	
}
