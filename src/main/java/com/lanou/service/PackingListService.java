package com.lanou.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanou.entity.PackingList;

public interface PackingListService {
	/**
	 * pagelist显示页面
	 * 查询所有装箱单
	 * @return
	 */
	List<PackingList> selectAll();
	/**
	 * 根据id查看装箱单
	 * @param packingListId
	 * @return
	 */
	PackingList selectById(String packingListId);
	/**
	 * 查询所有根据id
	 * @param PACKING_LIST_ID
	 * @return
	 */
	PackingList selectByPrimaryKey(String PACKING_LIST_ID);
	/**
	 * 动态修改
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(PackingList record);
	/**
	 * 删除
	 * @param PACKING_LIST_ID
	 * @return
	 */
	int deleteByPrimaryKey(String PACKING_LIST_ID);
	/**
	 * 删除所有
	 * 
	 * @param id
	 * @return
	 */
	int deleteAll(@Param("ids") String[] id);
	/**
	 * 装箱添加
	 * @param record
	 * @return
	 */
	int insertSelective(PackingList record);
}
