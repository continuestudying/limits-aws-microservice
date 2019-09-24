package com.nlgroup.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitConfigurationController {
	
	@Autowired
	private Configuration configuration;
	
	@RequestMapping("/test")
	public String test() {
		return "Test deploying on aws";
	}

	@RequestMapping("/limits")
	@HystrixCommand(fallbackMethod = "fallbackGetLimits")
	public LimitConfiguration getLimits() {
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
	
	public LimitConfiguration fallbackGetLimits() {
		return new LimitConfiguration(999, 1);		
	}
}
