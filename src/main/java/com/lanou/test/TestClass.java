package com.lanou.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lanou.bean.ContractDetail;
import com.lanou.bean.ContractProductBean;
import com.lanou.entity.Contract;
import com.lanou.entity.ContractProduct;
import com.lanou.entity.ExtCproduct;
import com.lanou.entity.Factory;
import com.lanou.mapper.ContractMapper;
import com.lanou.mapper.ContractProductMapper;
import com.lanou.mapper.ExtCproductMapper;
import com.lanou.mapper.FactoryMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class TestClass {
	@Resource
	private FactoryMapper factoryMapper;
	
	
	@Resource
	private  ContractMapper  contractMapper ;
	
	@Resource
	private  ContractProductMapper  contractProductMapper ;
	@Resource
	private  ExtCproductMapper  extCproductMapper ;
	
	
	
	
	 
	@Test
	public void String() {
		
		Map<String,Object> map = new HashMap<>();
		map.put("FULL_NAME", "祁县精艺厂");
		map.put("start", 1);
		map.put("end", 5);
		
		List<Factory> list = factoryMapper.selestPageByFactory(map);
		System.out.println(list.size());
	}
	
	
	@Test
	public void Count() {
		Map<String,Object> map = new HashMap<>();
		map.put("FULL_NAME", "祁县宏艺厂");
		int row = factoryMapper.selectPageCount(map);
		System.out.println(row);
	}
	
	
	@Test
	public void showView() {
		
		ContractDetail d = new ContractDetail();
		//根据合同id  查询 合同的基本信息
		Contract c = contractMapper.selectByPrimaryKey("4028817a33fc4e280133fd9f8b4e002f");
		d.setC(c);
	
		ArrayList<ContractProductBean> cpb = new ArrayList();
		List<ContractProduct> cp = contractProductMapper.selectProductByContractID(c.getCONTRACT_ID());
		for (ContractProduct cpt : cp) {
			 
			ContractProductBean bean = new ContractProductBean();
			bean.setCp(cpt);
			List<ExtCproduct> list = extCproductMapper.selectExtByProductId(cpt.getCONTRACT_PRODUCT_ID());
			bean.setList(list);
			cpb.add(bean);
		}
	    d.setCpb(cpb);
	String json =JSON.toJSONString(d);
	System.out.println(json);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
