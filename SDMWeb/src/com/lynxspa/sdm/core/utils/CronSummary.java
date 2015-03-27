package com.lynxspa.sdm.core.utils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class CronSummary implements Serializable {

	private static final long	serialVersionUID	= 5201422200361834490L;

	private Map<String, String>	monthMap;
	private Map<String, String>	dayOfMonthMap;
	private Map<String, String>	dayOfWeekMap;
	private Map<String, String>	hourMap;
	private Map<String, String>	minuteMap;
	private String				stopHour;
	private String				repeatMinutes;

	public CronSummary() {
		super();
		monthMap = new LinkedHashMap<String, String>();
		dayOfMonthMap = new LinkedHashMap<String, String>();
		dayOfWeekMap = new LinkedHashMap<String, String>();
		hourMap = new LinkedHashMap<String, String>();
		minuteMap = new LinkedHashMap<String, String>();
	}

	public Map<String, String> getMonthMap() {
		return monthMap;
	}

	public void setMonthMap(Map<String, String> monthMap) {
		this.monthMap = monthMap;
	}

	public Map<String, String> getDayOfMonthMap() {
		return dayOfMonthMap;
	}

	public void setDayOfMonthMap(Map<String, String> dayOfMonthMap) {
		this.dayOfMonthMap = dayOfMonthMap;
	}

	public Map<String, String> getDayOfWeekMap() {
		return dayOfWeekMap;
	}

	public void setDayOfWeekMap(Map<String, String> dayOfWeekMap) {
		this.dayOfWeekMap = dayOfWeekMap;
	}

	public Map<String, String> getHourMap() {
		return hourMap;
	}

	public void setHourMap(Map<String, String> hourMap) {
		this.hourMap = hourMap;
	}

	public Map<String, String> getMinuteMap() {
		return minuteMap;
	}

	public void setMinuteMap(Map<String, String> minuteMap) {
		this.minuteMap = minuteMap;
	}

	public String getStopHour() {
		return stopHour;
	}

	public void setStopHour(String stopHour) {
		this.stopHour = stopHour;
	}

	public String getRepeatMinutes() {
		return repeatMinutes;
	}

	public void setRepeatMinutes(String repeatMinutes) {
		this.repeatMinutes = repeatMinutes;
	}
}
