package com.lynxspa.sdm.core.services.validation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.lynxspa.sdm.core.services.utils.SpringContextService;
import com.lynxspa.sdm.core.services.validation.impl.SimpleValidator;

@Service
public class ValidationService {
	private static Logger			logger	= Logger.getLogger(ValidationService.class);

	@Autowired
	private SpringContextService	ctxService;

	/**
	 * Valida un objeto a partir de un validador concreto.
	 * <br />
	 * El validador debe extender la clase Validator y soportar (verificado mediante el método supports) el objeto a validar. Si no, lanza una IllegalArgumentException.
	 * <br />
	 * El validador puede tener anotaciones de dependencias de Spring.
	 * 
	 * @see com.lynxspa.sdm.web.services.validation.Validator
	 * @see BindingResult#hasFieldErrors()
	 * 
	 * @param object El objeto a validar
	 * @param validator El validador a utilizar.
	 * @param BindingResult El resultado de la validación, es un objeto BindingResult de Spring. Para verificar que no hay errores de validación hay que llamar al método hasFieldErrors. 
	 * @param result
	 */
	public void validate(Object object, Class<? extends Validator> validator, BindingResult result) {
		try {
			Validator v = validator.newInstance();
			ctxService.autowire(v);

			validate(object, v, result);
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (Throwable ex) {
			logger.fatal(ex.getMessage(), ex);
			result.reject("unknown.validation.error");
		}
	}
	
	/**
	 * Valida un objeto a partir de un validador concreto.
	 * <br />
	 * El validador debe extender la clase Validator y soportar (verificado mediante el método supports) el objeto a validar. Si no, lanza una IllegalArgumentException.
	 * <br />
	 * El validador puede tener anotaciones de dependencias de Spring.
	 * 
	 * @see com.lynxspa.sdm.web.services.validation.Validator
	 * @see BindingResult#hasFieldErrors()
	 * 
	 * @param object El objeto a validar
	 * @param validator El validador a utilizar.
	 * @param BindingResult El resultado de la validación, es un objeto BindingResult de Spring. Para verificar que no hay errores de validación hay que llamar al método hasFieldErrors. 
	 * @param result
	 */
	public void validate(Object object, Validator v, BindingResult result) {
		if (!v.supports(object.getClass())) {
			throw new IllegalArgumentException("Validator " + v.getClass().toString() + " does not support " + object.getClass());
		}
		v.validate(object, result);
	}
	
	public <T extends Validator> T getValidatorInstance(Class<T> validator) throws InstantiationException {
		T v = null;
		try {
			v = validator.newInstance();
		} catch (Exception ex) {
			throw new InstantiationException();
		}
		
		ctxService.autowire(v);		
		return v;
	}
	
	/**
	 * Devuelve un validador simple, es una instancia de utilidad, ya que sólo
	 * sirve para llamar a los métodos públicos que no sean ni validate ni
	 * supports.
	 * 
	 * @see SimpleValidator
	 * @return un SimpleValidator
	 */
	public Validator getSimpleValidatorInstance() {
		try {
			Validator v = new SimpleValidator();
			ctxService.autowire(v);
			return v;
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (Throwable ex) {
			logger.fatal(ex.getMessage(), ex);
			throw new IllegalStateException(ex);
		}
	}
}
