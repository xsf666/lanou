package com.lanou.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanou.bean.LoginLogBean;

public interface LoginLogService {
	/**
	 * 
	 * 根据年份查询登录记录
	 * @return
	 */
	List<LoginLogBean> selectLoginYearTime(@Param("yearTime") String yearTime);

	/**
	 * 
	 * 查询年份
	 * @return
	 */
	List<LoginLogBean> selectLoginYear();
}
