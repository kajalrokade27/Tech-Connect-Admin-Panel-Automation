package com.TechConnect.JavaUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateClass 
{
	public static String dates(int day)
	{
		
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
	    sim.format(date);
	
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, day);
		String select_date = sim.format(cal.getTime());
	
	   return select_date;
	}
	
	public static String date1(String format, int day)
	{
		
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat(format);
	    sim.format(date);
	
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, day);
		String select_date = sim.format(cal.getTime());
	
	   return select_date;
	}

}
