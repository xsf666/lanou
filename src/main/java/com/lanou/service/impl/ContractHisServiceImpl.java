package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanou.bean.ContractBean;
import com.lanou.entity.Contract;
import com.lanou.entity.ContractHis;
import com.lanou.mapper.ContractHisMapper;
import com.lanou.service.ContractHisService;

@Service
@WebService(endpointInterface = "com.lanou.service.ContractHisService", serviceName = "ContractHisService")
public class ContractHisServiceImpl implements ContractHisService {
	@Resource
	private ContractHisMapper contractHisMapper;

//	@Transactional
//	@Override
//	public int insertSelective(Contract record) {
//		int row = 0;
//		try {
//			row = contractHisMapper.insertSelective(record);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return row;
//	}

	@Override
	public List<ContractBean> selectAll() {
		// TODO Auto-generated method stub
		return contractHisMapper.selectAll();
	}

	@Override
	public ContractHis selectByPrimaryKey(String CONTRACT_ID) {
		return contractHisMapper.selectByPrimaryKey(CONTRACT_ID);
	}

	@Override
	public List<ContractHis> selectByFactoryName(String factory_name) {
		// TODO Auto-generated method stub
		return contractHisMapper.selectByFactoryName(factory_name);
	}

	@Override
	public ContractHis selectContractHisByTotalAmount() {
		// TODO Auto-generated method stub
		return contractHisMapper.selectContractHisByTotalAmount();
	}

	@Override
	public List<ContractHis> selectContractHisByTime(String CREATE_TIME) {
		// TODO Auto-generated method stub
		return contractHisMapper.selectContractHisByTime(CREATE_TIME);
	}

	@Override
	public List<ContractHis> selectAllByYear(String CREATE_TIME) {
		// TODO Auto-generated method stub
		return contractHisMapper.selectAllByYear(CREATE_TIME);
	}

}
