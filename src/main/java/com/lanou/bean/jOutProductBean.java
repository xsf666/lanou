package com.lanou.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class jOutProductBean implements Serializable{
	//货物号
	private String CONTRACT_PRODUCT_ID;
	//客户名称
	private String CUSTOM_NAME;
	//合同号
	private String CONTRACT_NO;
	//交货日期
	//@DateTimeFormat(pattern="yyyy-MM-dd")
    private String DELIVERY_PERIOD;
	//船期
	 //@DateTimeFormat(pattern="yyyy-MM-dd")
	    private String SHIP_TIME;
	//贸易条款
	 private String TRADE_TERMS;
	//厂家名称
	 private String FACTORY_NAME;
	//货号
	 private String PRODUCT_NO;
	//数量
	 private Short CNUMBER;
	public String getCONTRACT_PRODUCT_ID() {
		return CONTRACT_PRODUCT_ID;
	}
	public void setCONTRACT_PRODUCT_ID(String cONTRACT_PRODUCT_ID) {
		CONTRACT_PRODUCT_ID = cONTRACT_PRODUCT_ID;
	}
	public String getCUSTOM_NAME() {
		return CUSTOM_NAME;
	}
	public void setCUSTOM_NAME(String cUSTOM_NAME) {
		CUSTOM_NAME = cUSTOM_NAME;
	}
	public String getCONTRACT_NO() {
		return CONTRACT_NO;
	}
	public void setCONTRACT_NO(String cONTRACT_NO) {
		CONTRACT_NO = cONTRACT_NO;
	}
	
	public String getDELIVERY_PERIOD() {
		return DELIVERY_PERIOD;
	}
	public void setDELIVERY_PERIOD(String dELIVERY_PERIOD) {
		DELIVERY_PERIOD = dELIVERY_PERIOD;
	}
	public String getSHIP_TIME() {
		return SHIP_TIME;
	}
	public void setSHIP_TIME(String sHIP_TIME) {
		SHIP_TIME = sHIP_TIME;
	}
	public String getTRADE_TERMS() {
		return TRADE_TERMS;
	}
	public void setTRADE_TERMS(String tRADE_TERMS) {
		TRADE_TERMS = tRADE_TERMS;
	}
	public String getFACTORY_NAME() {
		return FACTORY_NAME;
	}
	public void setFACTORY_NAME(String fACTORY_NAME) {
		FACTORY_NAME = fACTORY_NAME;
	}
	public String getPRODUCT_NO() {
		return PRODUCT_NO;
	}
	public void setPRODUCT_NO(String pRODUCT_NO) {
		PRODUCT_NO = pRODUCT_NO;
	}
	public Short getCNUMBER() {
		return CNUMBER;
	}
	public void setCNUMBER(Short cNUMBER) {
		CNUMBER = cNUMBER;
	}
	 
}
