package com.tns.provider.rate;

import com.tns.entity.Rate;
import com.tns.rest.request.RateRequest;

public interface RateProviderInterface {
	public Rate getRate(RateRequest request) throws IllegalArgumentException;
}
