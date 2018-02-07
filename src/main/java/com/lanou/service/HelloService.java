package com.lanou.service;

import javax.jws.WebService;

@WebService
public interface HelloService {
	public void sayHi(String text);

	// 1:查询所有历史合同
	public void selectAllContractHis();
	// 2：根据合同id ，查询合同详情
	// 3：根据厂家，查询该厂家下所有的合同信息
	// 4：求出总金额最大的合同信息
	// 5：求出近三月以来，合同列表
	// 6：求出近半年以来，合同列表
	// 7：根据年份，合同列表
}
