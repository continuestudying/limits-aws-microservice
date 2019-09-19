package com.nlgroup.currencyexchangeservice.services;

import com.nlgroup.currencyexchangeservice.domains.ExchangeValue;

public interface CurrencyExchangeService {

	ExchangeValue getByFromAndTo(String from, String to);
	
}
