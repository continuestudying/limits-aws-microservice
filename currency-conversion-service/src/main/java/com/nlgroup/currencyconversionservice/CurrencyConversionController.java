package com.nlgroup.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
	) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";		
		
		CurrencyConversionBean response = new RestTemplate()
				.getForEntity(url, CurrencyConversionBean.class, uriVariables)
				.getBody();
		
		response.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
		
		return response;
	
	}
	
	/**
	 * Call to CurrencyExchangeServiceApplication that will call to CurrencyExchangeService
	 */
	@GetMapping("currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
	) {
		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
		response.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
		
		logger.info("{}", response);
		
		return response;
	}
}
