package com.lanou.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.lanou.service.HelloService;

@Service
@WebService(endpointInterface = "com.lanou.service.HelloService", serviceName = "HelloService")
public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHi(String text) {
		System.out.println("hello," + text);
	}

	@Override
	public void selectAllContractHis() {
		System.out.println("6666");
	}

}
