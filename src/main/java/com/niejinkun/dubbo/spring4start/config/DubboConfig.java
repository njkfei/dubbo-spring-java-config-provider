package com.niejinkun.dubbo.spring4start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.niejinkun.dubbo.spring4start.service.DemoService;
import com.niejinkun.dubbo.spring4start.serviceImpl.DemoServiceImpl;

@Configuration
public class DubboConfig {

	@Bean
	public DemoService demoService(){
		DemoService demoService = new DemoServiceImpl();
		return demoService;
	}
	
	// 当前应用配置
	@Bean
	public ApplicationConfig application() {
		ApplicationConfig application = new ApplicationConfig();
		application.setName("demoService");

		return application;
	}

	// 连接注册中心
	@Bean
	public RegistryConfig registry() {
		RegistryConfig registry = new RegistryConfig();
		registry.setProtocol("zookeeper");
		registry.setAddress("zookeeper://127.0.0.1:2181");
/*		registry.setUsername("aaa");
		registry.setPassword("bbb");*/
		return registry;
	}
	
	// 服务提供者协议配置
	@Bean
	public ProtocolConfig protocol(){
	ProtocolConfig protocol = new ProtocolConfig();
	protocol.setName("dubbo");
	protocol.setPort(20880);
	protocol.setThreads(200);
	
	return protocol;
	}
	// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
	 
	// 服务提供者暴露服务配置
	@Bean
	public ServiceConfig<DemoService> service(){
	ServiceConfig<DemoService> service = new ServiceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
	service.setApplication(application());
	service.setRegistry(registry()); // 多个注册中心可以用setRegistries()
	service.setProtocol(protocol()); // 多个协议可以用setProtocols()
	service.setInterface(DemoService.class);
	service.setVersion("1.0.0");
	service.setRef(demoService());
	service.export();
	
	return service;
	}
	

	 

}
