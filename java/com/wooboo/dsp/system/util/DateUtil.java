package com.wooboo.dsp.system.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.wooboo.dsp.util.StringUtil;



public class DateUtil {
	private static Long DAY_TIMES=24*60*60*1000l;
	private static Long HOUR_TIMES=60*60*1000l;
	private static Long MIN_TIMES=60*1000l;
	private static Long SECOND_TIMES=1000l;

	public static String FORMAT_YYMMDD="yyyy-MM-dd";
	public static String FORMAT_YYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
	public static String FORMAT_YYMMDDHHMMSSSSS="yyyy-MM-dd HH:mm:ss:sss";
	private static final DateFormat[] ACCEPT_DATE_FORMATS = {
		 new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS"),
		 new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
		 new SimpleDateFormat("yyyy-MM-dd"),
		 new SimpleDateFormat("yyyy/MM/dd"),
		 new SimpleDateFormat("yyyy.MM.dd"),
		 new SimpleDateFormat("yyyyMMdd"),
		 new SimpleDateFormat("dd/MM/yyyy"),
		//new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss+0800")
		};// 支持转换的日期格式
	public static Date getDate(Long date){
		 return null==date?null:new Date(date);
	}


	public static Date unixToDate(Long unixTime){
		 return null==unixTime?null:new Date(unixTime*1000);
	}
	public static Long dateToUnix(Date date){
		return null==date?null:date.getTime()/1000;
	}



	public static Date getDate(String dateStr){
		if(dateStr==null||"".equals(dateStr)) return null;
		for (DateFormat format : ACCEPT_DATE_FORMATS) {
			try {
			return format.parse(dateStr);
			} catch(Exception e) {
			continue;
			}
		}
		return null;
	}
	public static Date getDate(String dateStr,String format){
		if(StringUtil.isEmpty(dateStr)){
			return null;
		}
		format=StringUtil.isEmpty(format)?FORMAT_YYMMDD:format;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = null;
	    try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	public static Date getDate(Date cdate, String strFormat) {
		String date = getFormatDate(cdate, strFormat);
		return getDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	public static String getFormatDate(Date d,String format){
		format=StringUtil.isEmpty(format)?FORMAT_YYMMDD:format;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return (d==null)?"":sdf.format(d);
	}
	public static Date get00HourDate(Date d){
		return d!=null?DateUtil.getDate(DateUtil.getFormatDate(d, "yyyy-MM-dd 00:00:00"), FORMAT_YYMMDDHHMMSS):null;
	}
	public static Date get00MinuteDate(Date d){
		return d!=null?DateUtil.getDate(DateUtil.getFormatDate(d, "yyyy-MM-dd HH:00:00"), FORMAT_YYMMDDHHMMSS):null;
	}

	public static int getWeek(Date date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	public static Date absoluteDate(Date date, int day) {
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}
	public static Date absoluteHour(Date date, int hour) {
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);
		return cal.getTime();
	}
	public static Date absoluteMonth(Date date, int Month) {
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, Month);
        return cal.getTime();

	}
	public static Date absoluteYear(Date date, int Year) {
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, Year);
        return cal.getTime();

	}
	public static int getSeansonNum(Date date) {
		int month = Integer.valueOf(getFormatDate(date, "MM"));
		int[] seansonArr1 = {1,2,3};
		int[] seansonArr2 = {4,5,6};
		int[] seansonArr3 = {7,8,9};
		int[] seansonArr4 = {10,11,12};
		for(int i=0;i<seansonArr1.length;i++){
			if(seansonArr1[i]==month){
				return 1;
			}
		}
		for(int i=0;i<seansonArr2.length;i++){
			if(seansonArr2[i]==month){
				return 2;
			}
		}
		for(int i=0;i<seansonArr3.length;i++){
			if(seansonArr3[i]==month){
				return 3;
			}
		}
		for(int i=0;i<seansonArr4.length;i++){
			if(seansonArr4[i]==month){
				return 4;
			}
		}
		return 0;
	}
	public static Date absoluteWeek(Date date, int Week) {
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.WEEK_OF_YEAR, Week);
        return cal.getTime();

	}
	public static boolean beforeDate(Date currentDate,Date comDate){
		if(comDate==null || currentDate==null){
			return false;
		}else{
			return currentDate.before(comDate);
		}
	}
	public static Date get24HourDate(Date d){
		return d!=null?DateUtil.getDate(DateUtil.getFormatDate(d, "yyyy-MM-dd 23:59:59:999"), FORMAT_YYMMDDHHMMSSSSS):null;
	}
	public static Long getNumbersByTwoDate(Date bDate,Date eDate,Long parameter){
		parameter=StringUtil.isEmpty(parameter)?DAY_TIMES:parameter;

		return (eDate.getTime()-bDate.getTime())/parameter;
	}
	public static int getMonthSpace(Date date1, Date date2)
            {

        int result = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(date1);
        c2.setTime(date2);

        System.out.println(c2.get(Calendar.MONTH)+"   "+c1.get(Calendar.MONTH));

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);

    }

	public static Long getDayBytwoDate(Date bDate,Date eDate){
	    return getNumbersByTwoDate(bDate,eDate,DAY_TIMES);
	}
	public static Long getHourByTwoDate(Date bDate,Date eDate){
		return getNumbersByTwoDate(bDate,eDate,HOUR_TIMES);
	}
	public static Long getMinByTwoDate(Date bDate,Date eDate){
		return getNumbersByTwoDate(bDate, eDate, MIN_TIMES);
	}
	public static Long getSecondByTwoDate(Date bDate,Date eDate){
		return getNumbersByTwoDate(bDate,eDate,SECOND_TIMES);
	}
	public static Date getFristDayOfWeek(){
		Calendar c  = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, 1);
		return c.getTime();
	}
	public static Date getFistDayOfMonth(){
		Calendar c = Calendar.getInstance();
		//c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1,0, 0, 0);  //0时0分0秒
		return c.getTime();
	}
	public static Date getFistDayOfYear(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 1);
		return c.getTime();
	}
	public static Date getAddNumbersDay(Date date,int i,Long parameter){
		if(date==null){
			return null;
		}else{
			parameter = StringUtil.isEmpty(parameter)?DAY_TIMES:parameter;
			date.setTime(date.getTime()+i*parameter);
			return date;
		}
	}
	public static Date getAddDayByDay(Date date,int i){
		return getAddNumbersDay(date, i,DAY_TIMES);
	}
	public static Date getAddHourByDate(Date date,int i){
		return getAddNumbersDay(date, i,HOUR_TIMES);
	}
	public static Date getAddMinByDate(Date date,int i){
		return getAddNumbersDay(date,i,MIN_TIMES);
	}
	public static String timeStampString(){
		return DateUtil.getFormatDate(new Date(), "yyyyMMddHHmmss");
	}
	public static int getMinute(){
		Calendar cal = Calendar.getInstance();
	    return cal.get(Calendar.MINUTE);
	}

    public static String time2China(Date endDate){
    	StringBuffer sbuf = new StringBuffer();
    	if(endDate!=null){
    		Long minutes = DateUtil.getMinByTwoDate(new Date(), endDate);
    		if(minutes>0){
    			Long hour = minutes/60;
	    		Long minute = minutes%60;
	    		sbuf.append("距配送还有");
	    		if(hour>0){
	    			sbuf.append(hour+"小时");
	    		}
	    		sbuf.append(minute+"分钟");
    		}else{
    			sbuf.append("<span style='color:#ff6666;'>配送时间已到</span>");
    		}
    	}else{
    		sbuf.append("<span style='color:#ff6666;'>配送时间已到</span>");
    	}
    	return sbuf.toString();
	}
    public static int getMonth(Date start, Date end) {
    	if(null==start||null==end){
    		return -1;
    	}
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }



	public static void main(String[] args){
		//System.out.println(new Date().getTime());
		//System.out.println(DateUtil.getDate("2014-11-12 23:23:12").getTime());
		//Date eDate =  DateUtil.getDate("2015-01-22 23:00:00");
		//Date bDate = DateUtil.unixToDate(1293l);//new Date();

		
		System.out.println(DateUtil.getFormatDate(DateUtil.getFistDayOfMonth(), "yyyy-MM-dd HH:mm:ss"));
		
		
		System.out.println(new Date().getTime());

		long t = 1437187396190l;
		System.out.println(DateUtil.getDate(t));


		Date date1 = DateUtil.getDate("2015-11-01");
		Date date2 = DateUtil.getDate("2014-01-01");
		System.out.println(DateUtil.getMonth(date1, date2)+" ");



		System.out.println(DateUtil.getFristDayOfWeek());
		//1437187396190
		//Long d = (eDate.getTime()-bDate.getTime())/60*60*1000;
		//Long d = DateUtil.getMinByTwoDate(new Date(), DateUtil.getDate("2015-01-22 25:00:00"));
		//System.out.println(d);

		//System.out.println(d%60);


		//Date d = new Date();
		//d.setTime(1382949707000l);
		//System.out.println(DateUtil.getFormatDate(d,"yyyy-MM-dd HH:MM:ss"));
	//	Date d = DateUtil.getDate("2013/2/12");

	//	System.out.println(DateUtil.gethourMinute());

		// System.out.println(DateUtil.getFormatDate(DateUtil.getDate("2014-11-12 23:23:12"),"yyyy-MM-dd HH:mm:ss"));


	}

}
