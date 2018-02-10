package com.yonyou.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
	 private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  

	 private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	 /**
	  * 日期转换
	  * @param str 传入日期
	  * @param format 日期格式
	  * @return
	  * @throws Exception
	  */
	 public static Date convertStrToDate(String str,String format) throws Exception
	 {
		 SimpleDateFormat sdf = new SimpleDateFormat(format);
		 return sdf.parse(str);
	 }

	 /**
	  * 获取一年中的第一天
	  * @return
	  */
	 public static Date getYearFirstDay()
	 {
		Calendar calendar = Calendar.getInstance();  
		int year = calendar.get(Calendar.YEAR);
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
	 }
	 
	 public static Date getToDay() {     
	     Calendar calendar = Calendar.getInstance();    
	     calendar.set(Calendar.HOUR, 0);
	     calendar.set(Calendar.HOUR_OF_DAY, 0);
	     calendar.set(Calendar.MINUTE, 0);
	     calendar.set(Calendar.SECOND, 0);
	     calendar.set(Calendar.MILLISECOND, 0);
	     
	     return calendar.getTime();   
	 }  
	 
	 public static int dayForWeek(Date date)
	 {
		 Calendar c = Calendar.getInstance();  
		 c.setTime(date);  
		 int dayForWeek = 0;  
		 if(c.get(Calendar.DAY_OF_WEEK) == 1){  
		  dayForWeek = 7;  
		 }else{  
		  dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
		 }  
		 return dayForWeek;  
	 }
	 
	public static int dayForWeek(String pTime) throws Exception {  
		 Calendar c = Calendar.getInstance();  
		 c.setTime(format.parse(pTime));  
		 int dayForWeek = 0;  
		 if(c.get(Calendar.DAY_OF_WEEK) == 1){  
		  dayForWeek = 7;  
		 }else{  
		  dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
		 }  
		 return dayForWeek;  
		}

	/**
	 * 获取指定天数所在周的周一日期
	 * 星期天属于上周
	 * @param curTime
	 * @return
	 */
	public static Date getMonday(Date curTime) throws Exception
	{
		Calendar nt = Calendar.getInstance();
	
		nt.setTime(curTime);
		nt.set(Calendar.HOUR, 0);
		nt.set(Calendar.HOUR_OF_DAY, 0);
		nt.set(Calendar.MINUTE, 0);
		nt.set(Calendar.SECOND, 0);
		nt.set(Calendar.MILLISECOND, 0);
		int day_week = nt.get(Calendar.DAY_OF_WEEK);
		if (day_week==1)
		{
			nt.setTime(addTimeByDay(nt.getTime(),-6));
		}
		else if (day_week>2)
		{
			nt.setTime(addTimeByDay(nt.getTime(),-1*(day_week-1)));
		}
		return nt.getTime();
	}
	
	/**
	 * 获取周日
	 * @param monday 周一的日期
	 * @return
	 * @throws Exception
	 */
	public static Date getWeekSunDay(Date monday) throws Exception
	{
		return addTimeByDay(monday,6);
	}
	/**
	 * 获取一周的时间
	 * @param monday 周一的时间
	 * @return
	 */
	public static Date[] getWeekDays(Date monday) throws Exception
	{
		Date[] dates = new Date[7];
		dates[0]=monday;
		for (int i=1;i<7;i++)
		{
			dates[i] = addTimeByDay(monday, i);
		}
		return dates;
	}
	
	/**
	 * 获取下一周的星期一
	 * @param monday 本周星期一
	 * @return
	 * @throws Exception
	 */
	public static Date getNextWeekMonday(Date monday) throws Exception
	{
		return  addTimeByDay(monday, 7);
	}
	
	/**
	 * 获取上一周的星期一
	 * @param monday 本周星期一
	 * @return
	 * @throws Exception
	 */
	public static Date getPrevWeekMonday(Date monday) throws Exception
	{
		return  addTimeByDay(monday, -7);
	}
	
	public static String convertNumberDateToString(Long datenum)
	{
		return convertNumberDateToString(datenum,null);
	}
	
	public static String convertNumberDateToString(Long datenum,String format)
	{
		Date date = new Date(datenum);
		if (format==null)
		{
			return sdf.format(date);
		}
		else
		{
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.format(date);
		}
	}
	
	
	/**
	 * 获取一个月的最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	 public static  int getLastDayOfMonth(int year, int month) {  
		Calendar cal = Calendar.getInstance();  
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		   cal.set(Calendar.YEAR, year);  
		   cal.set(Calendar.MONTH, month);  
		       // 某年某月的最后一天  
		   return cal.getActualMaximum(Calendar.DATE);  
	  }  
	 
	/*
	 * 得到制定日期的第一天
	 * @param year 年
	 * @param month 月   从0到11
	 * @return   
	  */    
	 public static Date getMonthFirstDay() {     
	     Calendar calendar = Calendar.getInstance();    
	     calendar.set(Calendar.HOUR, 0);
	     calendar.set(Calendar.HOUR_OF_DAY, 0);
	     calendar.set(Calendar.MINUTE, 0);
	     calendar.set(Calendar.SECOND, 0);
	     calendar.set(Calendar.MILLISECOND, 0);
	     calendar.set(Calendar.DAY_OF_MONTH, calendar     
	             .getActualMinimum(Calendar.DAY_OF_MONTH));     
	     
	     return calendar.getTime(); 
	 }     
	 
	 
		/*
		 * 得到制定日期的第一天
		 * @param year 年
		 * @param month 月   从0到11
		 * @return   
		  */    
		 public static Date getWeekFirstDay() {     
		     Calendar calendar = Calendar.getInstance();    
		     calendar.set(Calendar.HOUR, 0);
		     calendar.set(Calendar.HOUR_OF_DAY, 0);
		     calendar.set(Calendar.MINUTE, 0);
		     calendar.set(Calendar.SECOND, 0);
		     calendar.set(Calendar.MILLISECOND, 0);
		     calendar.set(Calendar.DAY_OF_WEEK, calendar     
		             .getActualMinimum(Calendar.DAY_OF_WEEK));     
		     
		     return calendar.getTime(); 
		 }  
		     
		 /**   
		  * 得到本月的最后一天   
		  *    
		  * @return   
		  */    
		 public static Date getMonthLastDay() {     
		     Calendar calendar = Calendar.getInstance();    
		     calendar.set(Calendar.HOUR, 0);
		     calendar.set(Calendar.HOUR_OF_DAY, 0);
		     calendar.set(Calendar.MINUTE, 0);
		     calendar.set(Calendar.SECOND, 0);
		     calendar.set(Calendar.MILLISECOND, 0);
		    calendar.set(Calendar.DAY_OF_MONTH, calendar     
		             .getActualMaximum(Calendar.DAY_OF_MONTH));     
		     return calendar.getTime();    
		 }  

	/*
	 * 得到制定日期的第一天
	 * @param year 年
	 * @param month 月   从0到11
	 * @return   
	  */    
	 public static Date getMonthFirstDay(Integer year,Integer month) {     
	     Calendar calendar = Calendar.getInstance();    
	     calendar.set(Calendar.HOUR, 0);
	     calendar.set(Calendar.HOUR_OF_DAY, 0);
	     calendar.set(Calendar.MINUTE, 0);
	     calendar.set(Calendar.SECOND, 0);
	     calendar.set(Calendar.MILLISECOND, 0);
	     calendar.set(Calendar.YEAR, year);
	     calendar.set(Calendar.MONTH, month);
	     calendar.set(Calendar.DAY_OF_MONTH, calendar     
	             .getActualMinimum(Calendar.DAY_OF_MONTH));     
	     
	     return calendar.getTime(); 
	 }     
	     
	 /**   
	  * 得到本月的最后一天   
	  *    
	  * @return   
	  */    
	 public static Date getMonthLastDay(Integer year,Integer month) {     
	     Calendar calendar = Calendar.getInstance();    
	     calendar.set(Calendar.HOUR, 0);
	     calendar.set(Calendar.HOUR_OF_DAY, 0);
	     calendar.set(Calendar.MINUTE, 0);
	     calendar.set(Calendar.SECOND, 0);
	     calendar.set(Calendar.MILLISECOND, 0);
	     calendar.set(Calendar.YEAR, year);
	     calendar.set(Calendar.MONTH, month);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar     
	             .getActualMaximum(Calendar.DAY_OF_MONTH));     
	     return calendar.getTime();    
	 }     

	 /**
	  * 获取一周的周日
	  * 周日为一周的第一天
	  * @param curTime
	  * @return
	  * @throws Exception
	  */
	 public static Date getCurSunDay(Date curTime) throws Exception
	 {
		 Calendar calendar = Calendar.getInstance(); 
		 calendar.setTime(curTime);
		 if (calendar.get(Calendar.DAY_OF_WEEK)==1)
			 return curTime;
		 else
			 return addTimeByDay(curTime, -1*(calendar.get(Calendar.DAY_OF_WEEK)-1));
	 }
	 
	 public static Date getCurSaturday(Date curTime) throws Exception
	 {
		 Calendar calendar = Calendar.getInstance(); 
		 calendar.setTime(curTime);
		 if (calendar.get(Calendar.DAY_OF_WEEK)==7)
			 return curTime;
		 else
			 return addTimeByDay(curTime, 7-calendar.get(Calendar.DAY_OF_WEEK));
	 }
	 public static Calendar getCurrentCal()
	 {
	     Calendar calendar = Calendar.getInstance();    
	     calendar.set(Calendar.HOUR, 0);
	     calendar.set(Calendar.HOUR_OF_DAY, 0);
	     calendar.set(Calendar.MINUTE, 0);
	     calendar.set(Calendar.SECOND, 0);
	     calendar.set(Calendar.MILLISECOND, 0);
	     return calendar; 
	 }
	 public static Date getCurrentDay()
	 {
	     Calendar calendar = Calendar.getInstance();    
	     calendar.set(Calendar.HOUR, 0);
	     calendar.set(Calendar.HOUR_OF_DAY, 0);
	     calendar.set(Calendar.MINUTE, 0);
	     calendar.set(Calendar.SECOND, 0);
	     calendar.set(Calendar.MILLISECOND, 0);
	     return calendar.getTime(); 
	 }
	 
	 public static Date getCurrentDay(Date date)
	 {
	     Calendar calendar = Calendar.getInstance(); 
	     calendar.setTime(date);
	     calendar.set(Calendar.HOUR, 0);
	     calendar.set(Calendar.HOUR_OF_DAY, 0);
	     calendar.set(Calendar.MINUTE, 0);
	     calendar.set(Calendar.SECOND, 0);
	     calendar.set(Calendar.MILLISECOND, 0);
	     return calendar.getTime(); 
	 }
	 
		/**
		 * 日期加天数
		 * @param date
		 * @param days 
		 * @return
		 * @throws Exception
		 */
		public static java.util.Date addTimeByDay(java.util.Date date,int days) throws Exception
		{
			Calendar calendar=Calendar.getInstance();   
			 calendar.setTime(date);
			calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+days);
			return calendar.getTime();
		}
		
		/**
		 * 日期加分钟数
		 * @param date
		 * @param minutes
		 * @return
		 * @throws Exception
		 */
		public static java.util.Date addTimeByMinutes(java.util.Date date,int minutes) throws Exception
		{
			Calendar calendar=Calendar.getInstance();   
			 calendar.setTime(date);
			 calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+minutes);
			return calendar.getTime();
		}
		
		/**
		 * 日期加分秒数
		 * @param date
		 * @param minutes
		 * @return
		 * @throws Exception
		 */
		public static java.util.Date addTimeBySeconds(java.util.Date date,int seconds) throws Exception
		{
			Calendar calendar=Calendar.getInstance();   
			 calendar.setTime(date);
			 calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND)+seconds);
			return calendar.getTime();
		}
		
		
		public static java.util.Date formatDateStr(String datestr) throws Exception
		{
			return formatDateStr(datestr,"yyyy-MM-dd");
		}
		
		public static java.util.Date formatDateStr(String datestr,String format) throws Exception
		{
			java.util.Date result = null;
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try
			{
				result = sdf.parse(datestr);
			}
			catch (Exception ex)
			{
				 sdf = new SimpleDateFormat("yyyy-MM-dd");
				result = sdf.parse(datestr);
			}
			return result;
		}
}
