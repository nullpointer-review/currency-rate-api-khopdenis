package com.tns.integration;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.cbr.web.DailyInfoSoap;

@Configuration
public class IntegrationConfig {

	@Bean
	public DailyInfoSoap dailyInfoSoap() throws Exception {
		URL url = new URL("http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx?wsdl");
		QName qname = new QName("http://web.cbr.ru/", "DailyInfo");
		Service service = Service.create(url, qname);
		DailyInfoSoap soapClient = service.getPort(DailyInfoSoap.class);
		return soapClient;
	}

}
