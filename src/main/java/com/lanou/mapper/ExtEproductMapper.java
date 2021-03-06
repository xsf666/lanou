package com.lanou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.lanou.entity.ExtEproduct;

@MapperScan
public interface ExtEproductMapper {
	/**
	 * 根据和货物id查询附件
	 * 
	 * @param ExtEproductId
	 * @return
	 */
	List<ExtEproduct> selectExteproductByProductId(@Param("EXPORT_PRODUCT_ID") String ExportProductId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table EXT_EPRODUCT_C
	 *
	 * @mbg.generated Mon Jan 08 14:47:56 CST 2018
	 */
	int deleteByPrimaryKey(String EXT_EPRODUCT_ID);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table EXT_EPRODUCT_C
	 *
	 * @mbg.generated Mon Jan 08 14:47:56 CST 2018
	 */
	int insert(ExtEproduct record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table EXT_EPRODUCT_C
	 *
	 * @mbg.generated Mon Jan 08 14:47:56 CST 2018
	 */
	int insertSelective(ExtEproduct record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table EXT_EPRODUCT_C
	 *
	 * @mbg.generated Mon Jan 08 14:47:56 CST 2018
	 */
	ExtEproduct selectByPrimaryKey(String EXT_EPRODUCT_ID);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table EXT_EPRODUCT_C
	 *
	 * @mbg.generated Mon Jan 08 14:47:56 CST 2018
	 */
	int updateByPrimaryKeySelective(ExtEproduct record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table EXT_EPRODUCT_C
	 *
	 * @mbg.generated Mon Jan 08 14:47:56 CST 2018
	 */
	int updateByPrimaryKey(ExtEproduct record);
}