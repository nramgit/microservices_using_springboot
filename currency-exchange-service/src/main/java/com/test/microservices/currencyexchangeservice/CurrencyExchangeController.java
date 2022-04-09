package com.test.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	Environment env;
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@GetMapping("/currency-exchange/{from}/{to}")
	public CurrencyExchange getCurrencyExchangeValue(@PathVariable String from,@PathVariable String to) {
		//CurrencyExchange exchange = new  CurrencyExchange(100,from,to,new BigDecimal(75));
		CurrencyExchange exchange = repository.findByFromAndTo(from, to);
		//System.out.println("EXchange object: "+exchange);
		//if(exchange==null)
		//	throw new RuntimeException("Exchange data not found for the currency "+from+" "+to);
		//exchange.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return exchange;
	}
}
