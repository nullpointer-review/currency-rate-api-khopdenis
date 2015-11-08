package com.tns.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Rate {

    private String code;
    private String rate;
    private String date;
    
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    
	public Rate(String code, String rate, String date) {
		super();
		this.code = code;
		this.rate = rate;
		this.date = date;
	}
	
	public Rate(String code, String rate, Date date) {
		super();
		this.code = code;
		this.rate = rate;
		SimpleDateFormat resultDateFormat = new SimpleDateFormat(DATE_FORMAT);
		this.date = resultDateFormat.format(date);
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
    
	
    
   
}