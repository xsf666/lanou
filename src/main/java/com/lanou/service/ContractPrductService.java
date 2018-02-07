package com.lanou.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanou.bean.ContractProductBean;
import com.lanou.entity.ContractProduct;

public interface ContractPrductService {
	/**
	 * 产品销售排行，前10名
	 * 
	 * @return
	 */
	List<ContractProductBean> selectByTen();

	/**
	 * 添加货物
	 * 
	 * @param record
	 * @return
	 */
	int insert(ContractProduct record);

	/**
	 * 查询所有货物
	 * 
	 * @return
	 */
	List<ContractProduct> selectAll();

	/**
	 * 根据合同id 查询货物列表
	 * 
	 * @param contractId
	 * @return
	 */
	List<ContractProduct> selectProductByContractID(String contractId);

	/**
	 * 根据货物id查询货物
	 * 
	 * @param contractProductId
	 * @return
	 */
	ContractProduct selectContractProductByProductId(String contractProductId);

	/**
	 * 根据货物id修改货物
	 * 
	 * @param record
	 * @return
	 */
	int updateContractProductByProductId(ContractProduct record);

	/**
	 * 根据货物id删除货物
	 * 
	 * @param contractProductId
	 * @return
	 */
	int deleteContract_Product(String contractProductId);
}
