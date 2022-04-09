package com.test.microservices.currencyconversionservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CurencyConversion {
	
	@Id
	private int id;
	
	@Column(name="currency_from")
	private String from;
	
	@Column(name="currency_to")
	private String to;
	
	@Column(name="qnty")
	private int quantity;
	
	@Column(name="conversion_value")
	private BigDecimal conversionValue;
	
	@Column(name="total_amt")
	private BigDecimal totAmount;
	
	private int environment;
	
	public CurencyConversion() {
		super();
	}

	public CurencyConversion(int id, String from, String to, int quantity, BigDecimal conversionValue,
			BigDecimal totAmount,int environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.quantity = quantity;
		this.conversionValue = conversionValue;
		this.totAmount = totAmount;
		this.environment = environment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getConversionValue() {
		return conversionValue;
	}

	public void setConversionValue(BigDecimal conversionValue) {
		this.conversionValue = conversionValue;
	}

	public BigDecimal getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(BigDecimal totAmount) {
		this.totAmount = totAmount;
	}

	public int getEnvironment() {
		return environment;
	}

	public void setEnvironment(int environment) {
		this.environment = environment;
	}
	
}
