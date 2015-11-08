package com.tns.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tns.provider.rate.RateProviderCBR;
import com.tns.provider.rate.RateProviderInterface;

@Configuration
public class ProviderConfig {
	
	@Bean
	public RateProviderInterface rateProviderCBRIntegration(){
		return new RateProviderCBR();
	}

}
