package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanou.bean.jOutProductBean;
import com.lanou.mapper.ContractMapper;
import com.lanou.service.jOutProductService;
@Service
public class jOutProductServiceImpl implements jOutProductService {
	@Resource
	private ContractMapper contractMapper;
	@Override
	public List<jOutProductBean> selectjOutProductByst(String shipTime) {
		return contractMapper.selectjOutProductByst(shipTime);
	}

}
