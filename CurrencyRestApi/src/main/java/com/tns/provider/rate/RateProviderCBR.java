package com.tns.provider.rate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.tns.entity.Rate;
import com.tns.rest.request.RateRequest;
import ru.cbr.web.DailyInfoSoap;
import ru.cbr.web.GetCursOnDateXMLResponse.GetCursOnDateXMLResult;


public class RateProviderCBR implements RateProviderInterface {

	private static String ARG_DATE_ERROR = "Rate code is incorrect.";

	@Resource
	private DailyInfoSoap dailyInfoSoap;

	@Override
	public Rate getRate(RateRequest request) throws IllegalArgumentException {
		
		if( request.getCode() == null || "".equals(request.getCode())) {
			throw new IllegalArgumentException(ARG_DATE_ERROR);
		}
		
		// 24 hours + if date is null
		XMLGregorianCalendar xmlGregorianDateParam;
		GregorianCalendar calendar =  new GregorianCalendar();
		Date dateParam;
		if(request.getDate() == null){
			dateParam = new Date();
			calendar.setTime(dateParam);
			calendar.add(Calendar.HOUR, 24);
		} else {
			dateParam = request.getDate();
			calendar.setTime(dateParam);
		}		
		try {
			xmlGregorianDateParam = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		} catch (DatatypeConfigurationException e) {
			throw new IllegalArgumentException(ARG_DATE_ERROR);
		}	
		
		// get rate from CBR
		String resultRateValue = null;
		resultRateValue = findRateByCode(request.getCode(), dailyInfoSoap.getCursOnDateXML(xmlGregorianDateParam));
		if(resultRateValue == null){
			return null;
		}
		
		Rate resultRate = new Rate(request.getCode(), resultRateValue, calendar.getTime());
		return resultRate;		
	}
	
	//TODO use XPath fabric for access to element 
	private String findRateByCode(String code, GetCursOnDateXMLResult responseResult) {
		ElementNSImpl content = (ElementNSImpl) responseResult.getContent().get(0);
		NodeList rateNodeList = content.getChildNodes();
		Integer j = rateNodeList.getLength();
		for (Integer i = 0; i < j; i++) {
			Node rateNode = rateNodeList.item(i);
			String rateCode = rateNode.getLastChild().getTextContent();
			if(code.equalsIgnoreCase(rateCode)){
				return rateNode.getFirstChild().getNextSibling().getNextSibling().getTextContent();
			}
		}
		return null;
	}

}
