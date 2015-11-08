package com.tns.rest.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tns.entity.Rate;
import com.tns.provider.rate.RateProviderInterface;
import com.tns.rest.request.RateRequest;

import java.util.Date;

import javax.annotation.Resource;

@RestController
public class RateController {
	
	private final static String PAR_CODE_ERROR = "Code is not exists!";
	
	@Resource
	RateProviderInterface provider;

	@RequestMapping("currency/api/rate/{code}/{date}")
	public Rate getRateByCodeDate(@PathVariable(value = "code") String code, @PathVariable(value = "date")  @DateTimeFormat(pattern=Rate.DATE_FORMAT) Date date) throws IllegalArgumentException {
		Rate result = provider.getRate(new RateRequest(code, date));
		if(result == null){
			throw new IllegalArgumentException(PAR_CODE_ERROR);
		} else {
			return result;
		}
	}
	
	@RequestMapping("currency/api/rate/{code}")
	public Rate getRateByCode(@PathVariable(value = "code") String code) throws IllegalArgumentException {
		Rate result = provider.getRate(new RateRequest(code, null));
		if(result == null){
			throw new IllegalArgumentException(PAR_CODE_ERROR);
		} else {
			return result;
		}
	}
	
}