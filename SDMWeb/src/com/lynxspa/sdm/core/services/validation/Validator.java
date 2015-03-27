package com.lynxspa.sdm.core.services.validation;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lynxspa.sdm.core.managers.configuration.adapters.ConfigurationManagerAdapter;

public abstract class Validator implements org.springframework.validation.Validator {
	private static Logger					logger		= Logger.getLogger(Validator.class);

	@Autowired
	protected ConfigurationManagerAdapter	cfgService;


	/**
	 * Devuelve true si la cadena es menor a la longitud.
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public final boolean isStringSmallerThan(String str, Integer length) {
		return str == null || str.length() <= length;
	}


	/**
	 * @param str
	 * @return true si se puede convertir a un Integer de Java
	 */
	public final boolean isInteger(final String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * @param str
	 * @return true si se puede convertir a un Double de Java
	 */
	public final boolean isDouble(final String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * @param a
	 * @param min
	 * @param max
	 * @return true si min < a < max
	 */
	public final boolean isInRange(final Integer a, final Integer min, final Integer max) {
		return min.compareTo(a) == -1 && max.compareTo(a) == 1;
	}

	/**
	 * @param a
	 * @param min
	 * @param max
	 * @return true si min < a < max
	 */
	public final boolean isInRange(final Double a, final Double min, final Double max) {
		return min.compareTo(a) == -1 && max.compareTo(a) == 1;
	}




	/**
	 * @param value
	 * @param base
	 * @param permittedError
	 * @return true si el valor es aproximadamente base con el margen de error permitido.
	 */
	public final boolean isApproximately(final Integer value, final Integer base, final Integer permittedError) {
		return isInRange(value, base - permittedError, base + permittedError);
	}

	/**
	 * @see Validator#isApproximately(Integer, Integer, Integer)
	 */
	public final boolean isApproximately(final Double value, final Double base, final Double permittedError) {
		return isInRange(value, base - permittedError, base + permittedError);
	}

	/**
	 * 
	 * @param value
	 * @param base
	 * @param permittedError
	 * @return true si es igual a la base (con un margen de error permitido) y mayor que 0.
	 */
	public final boolean isApproximatelyOrLess(final Double value, final Double base, final Double permittedError) {
		return isInRange(value, 0.0, base + permittedError);
	}


	/**
	 * @param email
	 * @return true si el email está bien formado
	 */
	public final boolean isValidEmail(final String email) {
		EmailValidator v = EmailValidator.getInstance();
		return v.isValid(email);
	}



}
