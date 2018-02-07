package com.lanou.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanou.bean.jOutProductBean;

public interface jOutProductService {
	/**
	 * 出货表
	 * @param shipTime
	 * @return
	 */
	List<jOutProductBean> selectjOutProductByst(String shipTime);
}
