package com.lynxspa.sdm.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;

public final class TimeUtils {
	private TimeUtils() {
	}

	/**
	 * Devuelve el tiempo que ha pasado entre dos timestamp
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static final Date getEllapsedTime(Long start, Long end) {
		Long timeElapsed = end - start;
		Calendar elapsedCalendar = Calendar.getInstance();
		elapsedCalendar.setTimeInMillis(timeElapsed);
		elapsedCalendar.add(Calendar.HOUR, -1);
		Date elapsedDate = elapsedCalendar.getTime();

		return elapsedDate;
	}

	/**
	 * Devuelve la fecha actual pero a final del dia: dd/MM/yyyy 23:59:59.999
	 * 
	 * @param date
	 * @return
	 */
	public static final Date asEndOfDay(Date date) {
		return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
	}

	/**
	 * Devuelve la fecha actual al inicio de día: dd/MM/yyyy 00:00:00.0000
	 * 
	 * @param date
	 * @return
	 */
	public static final Date asStartOfDay(Date date) {
		return DateUtils.truncate(date, Calendar.DATE);
	}

	/**
	 * Formatea una fecha en el formato especificado y en un locale. Si no es posible
	 * utiliza una fecha por defecto.
	 * 
	 * @param value
	 * @param defaultValue
	 * @param format
	 * @param locale
	 * @return
	 */
	public static final String formatDefault(Date value, Date defaultValue, String format, Locale locale) {
		if (value == null) {
			if (defaultValue == null) {
				return "";
			}
			return format(defaultValue, format, locale);
		} else {
			return format(value, format, locale);
		}
	}

	/**
	 * Formatea una fecha y devuelve el resultado.
	 * 
	 * @param value
	 * @param format
	 * @param locale
	 * @return
	 */
	public static final String format(Date value, String format, Locale locale) {
		SimpleDateFormat fmt = new SimpleDateFormat(format, locale);
		return fmt.format(value);
	}

	/**
	 * Formatea de forma EXACTA una fecha intentando todos los formatos válidos. Si el formato
	 * de fecha es dd/MM/yyyy y una persona pone 05/20/2013 lanza una excepción porque el mes
	 * 20 es imposible.
	 * 
	 * @param value
	 * @param validFormats
	 * @param locale
	 * @return
	 * @throws ParseException
	 */
	public static final Date parseExact(String value, String[] validFormats, Locale locale) throws ParseException {
		for (String format : validFormats) {
			SimpleDateFormat dateFmt = new SimpleDateFormat(format, locale);
			dateFmt.setLenient(false);
			try {
				Date parsedDate = dateFmt.parse(value);
				return parsedDate;
			} catch (ParseException ex) {
				// continue
			}
		}

		throw new ParseException(value + " does not match any supplied formats.", 0);
	}

	/**
	 * 
	 * @see TimeUtils#parseExact(String, String[], Locale)
	 */
	public static final Date parseExact(String value, String format, Locale locale) throws ParseException {
		return TimeUtils.parseExact(value, new String[] { format }, locale);
	}

	/**
	 * Parsea una fecha o null si no es posible.
	 * 
	 * @param value
	 * @param validFormats
	 * @param locale
	 * @return
	 */
	public static final Date parseExactSafe(String value, String[] validFormats, Locale locale) {
		try {
			return parseExact(value, validFormats, locale);
		} catch (Throwable ex) {
			return null;
		}
	}

	/**
	 * 
	 * @see TimeUtils#parseExactSafe(String, String[], Locale)
	 */
	public static final Date parseExactSafe(String value, String format, Locale locale) {
		return parseExactSafe(value, new String[] { format }, locale);
	}

	/**
	 * Devuelve la diferencia de días entre dos fechas.
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static final int getDaysBetween(Date first, Date second) {
		return (int) (Math.abs(second.getTime() - first.getTime()) / (1000 * 60 * 60 * 24L));
	}

	/**
	 * @return La fecha actual.
	 */
	public static final Date Now() {
		return Calendar.getInstance().getTime();
	}
}
