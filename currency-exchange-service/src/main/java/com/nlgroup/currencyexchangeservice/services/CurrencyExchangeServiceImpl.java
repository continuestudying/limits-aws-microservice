package com.nlgroup.currencyexchangeservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlgroup.currencyexchangeservice.domains.ExchangeValue;
import com.nlgroup.currencyexchangeservice.repositories.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@Override
	public ExchangeValue getByFromAndTo(String from, String to) {		
		return currencyExchangeRepository.findByFromAndTo(from, to);
	}
	
}
