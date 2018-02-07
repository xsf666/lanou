package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanou.bean.LoginLogBean;
import com.lanou.mapper.LoginLogMapper;
import com.lanou.service.LoginLogService;

@Service
public class LoginLogServiceImpl implements LoginLogService {
	@Resource
	private LoginLogMapper loginLogMapper;

	@Override
	public List<LoginLogBean> selectLoginYearTime(String yearTime) {
		// TODO Auto-generated method stub
		return loginLogMapper.selectLoginYearTime(yearTime);
	}

	@Override
	public List<LoginLogBean> selectLoginYear() {
		// TODO Auto-generated method stub
		return loginLogMapper.selectLoginYear();
	}

	

}
