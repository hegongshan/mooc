package com.hegongshan.mooc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateUtils implements Converter<String,Date>{
	
	private static SimpleDateFormat sdf;
	static {
		sdf = new SimpleDateFormat();
	}
	public static String format(Date date,String pattern) {
		if (StringUtils.isEmpty(pattern) || date == null) {
			return null;
		} else {
			sdf.applyPattern(pattern);
			return sdf.format(date);
		}
	}
	public static Date parse(String source,String pattern) throws ParseException {
		if (StringUtils.isEmpty(source) || StringUtils.isEmpty(pattern)) {
			return null;
		} else {
			sdf.applyPattern(pattern);
			return sdf.parse(source);
		}
	}
	/**
	 * 对java.util.Date类型进行日期的增减
	 * @param date 要增减的java.util.Date实例
	 * @param amount 增减的天数
	 * @return 增减后的java.util.Date实例
	 */
	public static Date operateDate(Date date,int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		Date resultDate = calendar.getTime();
		return resultDate;
	}
	
	@Override
	public Date convert(String source) {
		try {
			return parse(source,"yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
