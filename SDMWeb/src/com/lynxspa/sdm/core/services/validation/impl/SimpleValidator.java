package com.lynxspa.sdm.core.services.validation.impl;

import org.springframework.validation.Errors;

import com.lynxspa.sdm.core.services.validation.Validator;

/**
 * Clase final de utilidad para llamar a los métodos públicos de la clase
 * abstracta Validator.<br /><br />
 * 
 * No soporta validar ningún tipo de objeto y llamar a validate lanza
 * una UnsupportedOperationException.<br /><br />
 *  
 * Básicamente sirve para llamar a métodos para validar strings, URLs...<br />
 *  
 * @author kevin.mas
 *
 */
public final class SimpleValidator extends Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		throw new UnsupportedOperationException();
	}

}
