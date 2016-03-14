package com.niejinkun.dubbo.spring4start.serviceImpl;

import com.niejinkun.dubbo.spring4start.service.DemoService;

public class DemoServiceImpl implements DemoService {
	 
    public String sayHello(String name){
    	return "hello " + name;
    }
 
}