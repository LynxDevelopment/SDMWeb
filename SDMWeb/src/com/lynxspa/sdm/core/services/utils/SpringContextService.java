package com.lynxspa.sdm.core.services.utils;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class SpringContextService implements ApplicationContextAware {
	private ApplicationContext	applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext apctx) throws BeansException {
		applicationContext = apctx;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * Devuelve un bean por tipo del contenedor de beans de Spring
	 * 
	 * @param arg0
	 *            El tipo del bean
	 * @return
	 * @throws BeansException
	 */
	public <T> T getBean(Class<T> arg0) throws BeansException {
		return applicationContext.getBean(arg0);
	}

	/**
	 * Devuelve un bean por nombre del contenedor de beans de Spring
	 * 
	 * @param arg0
	 *            El nombre del bean
	 * @return
	 * @throws BeansException
	 */
	public Object getBean(String arg0) throws BeansException {
		return applicationContext.getBean(arg0);
	}

	/**
	 * Inyecta las dependencias para un bean.
	 * 
	 * @param o
	 *            El bean que requiere la inyección de dependencias.
	 */
	public void autowire(Object o) {
		applicationContext.getAutowireCapableBeanFactory().autowireBean(o);
	}

	/**
	 * Hace lo mismo que autowire (inyectar las dependencias del bean) pero
	 * puede ser, dependiendo de la terminología a la que esté acostumbrada
	 * el programador, más descriptivo.
	 * 
	 * @see SpringContextService#autowire(Object)
	 * 
	 * @param o
	 */
	public void inject(Object o) {
		applicationContext.getAutowireCapableBeanFactory().autowireBean(o);
	}

	/**
	 * Recupera un mensaje de I18N de Spring.
	 * 
	 * @param msg
	 * @param params
	 * @param locale
	 * @return
	 */
	public String getMessage(String msg, Object[] params, Locale locale) {
		return applicationContext.getMessage(msg, params, locale);
	}

	public String getMessage(String msg, Object[] params) {
		return this.getMessage(msg, params, LocaleContextHolder.getLocale());
	}

	public String getMessage(String msg) {
		return this.getMessage(msg, new Object[] {}, LocaleContextHolder.getLocale());
	}

	public String getMessage(String msg, Locale locale) {
		return this.getMessage(msg, new Object[] {}, locale);
	}
}
