package com.nlgroup.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient("currency-exchange-service")
@FeignClient("netflix-zuul-api-gateway-server")
@RibbonClient("currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	// @GetMapping("/currency-exchange/from/{from}/to/{to}")
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(
			@PathVariable("from") String from, @PathVariable("to") String to);
	
}
