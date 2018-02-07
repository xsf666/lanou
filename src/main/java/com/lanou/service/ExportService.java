package com.lanou.service;

import java.util.List;

import com.lanou.entity.Export;

public interface ExportService {
	/**
	 * 报运单列表
	 * 
	 * @return
	 */
	List<Export> selectAll();

	/**
	 * 根据id查看报运单信息
	 * 
	 * @param EXPORT_ID
	 * @return
	 */
	Export selectByPrimaryKey(String EXPORT_ID);

	/**
	 * 修改
	 * 
	 * @param parameter
	 * @return
	 */
	int updateByPrimaryKeySelective(Export record);

	/**
	 * 删除
	 * 
	 * @param EXPORT_ID
	 * @return
	 */
	int deleteByPrimaryKey(String EXPORT_ID);

	/**
	 * 多个合同报运成一个报运单
	 * 
	 * @param record
	 * @return
	 */
	public void baoyun(String[] ids);

	/**
	 * 装箱
	 * 
	 * @param ids
	 * @return
	 */
	public List<Export> BoxUp(String[] ids);
	
}
