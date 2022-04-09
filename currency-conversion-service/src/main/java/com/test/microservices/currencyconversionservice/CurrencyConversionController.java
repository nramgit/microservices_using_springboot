package com.test.microservices.currencyconversionservice;

import java.math.BigDecimal;
//import brave.sampler.Sampler;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableFeignClients
public class CurrencyConversionController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("currency-conversion/{from}/{to}/{quantity}")
	public CurencyConversion convertCurrencyValue(@PathVariable String from,
											@PathVariable String to, @PathVariable int quantity) {
		
		HashMap<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurencyConversion> resp= new RestTemplate()
				.getForEntity("http://localhost:8001/currency-exchange/{from}/{to}", CurencyConversion.class,uriVariables);
		CurencyConversion conversion=resp.getBody();
		
		return new CurencyConversion(conversion.getId(),from,to,quantity,
				conversion.getConversionValue(),conversion.getConversionValue().multiply(new BigDecimal(quantity)),Integer.parseInt(env.getProperty("local.server.port"))); 
	}
	
	@GetMapping("currency-conversion-feign/{from}/{to}/{quantity}")
	public CurencyConversion convertCurrencyValueFeign(@PathVariable String from,
											@PathVariable String to, @PathVariable int quantity) {
		
		/*HashMap<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurencyConversion> resp= new RestTemplate()
				.getForEntity("http://localhost:8002/currency-exchange/{from}/{to}", CurencyConversion.class,uriVariables);*/
		CurencyConversion conversion=proxy.getCurrencyExchangeValue(from, to);
		
		//CurencyConversion conversion=resp.getBody();
		
		return new CurencyConversion(conversion.getId(),from,to,quantity,
				conversion.getConversionValue(),conversion.getConversionValue().multiply(new BigDecimal(quantity)),conversion.getEnvironment()); 
	}
	
	/*
	 * @Bean public Sampler alwaysSampler() { return Sampler.ALWAYS_SAMPLE; }
	 */
}
