package com.lanou.service;

import java.util.List;

import javax.jws.WebService;

import org.apache.ibatis.annotations.Param;

import com.lanou.bean.ContractBean;
import com.lanou.entity.Contract;
import com.lanou.entity.ContractHis;
@WebService
public interface ContractHisService {
	/**
	 * 1.查询合同历史
	 * 
	 * @return
	 */
	List<ContractBean> selectAll();

	/**
	 * 
	 * @param record
	 * @return
	 */
	//int insertSelective(Contract record);
	// 2：根据合同id ，查询合同详情
	ContractHis selectByPrimaryKey(String CONTRACT_ID);
	// 3：根据厂家，查询该厂家下所有的合同信息
	List<ContractHis> selectByFactoryName(String factory_name);
	// 4：求出总金额最大的合同信息
	ContractHis selectContractHisByTotalAmount();
	// 5：求出近三月以来，合同列表
	// 6：求出近半年以来，合同列表
	List<ContractHis> selectContractHisByTime(@Param("CREATE_TIME")String CREATE_TIME);
	// 7：根据年份，合同列表
	List<ContractHis> selectAllByYear(String CREATE_TIME);
}
