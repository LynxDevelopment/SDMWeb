package com.lynxspa.sdm.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.lynxspa.sdm.core.services.FileStateService;

/**
 * Filtro que se ocupa de gestionar el sistema de ficheros temporales por
 * sesión. Este filtro no se usa directamente ya que requiere del sistema
 * de dependencias de Spring, así que se usa mediante un DelegatingFilterProxy.
 * 
 * @author kevin.mas
 */
public class SessionLifecycleFilter implements Filter {
	@Autowired
	private FileStateService	fileStateService;

	@Override
	public void destroy() {
		// Nothing TO DO
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		fileStateService.clearAll();
	}
	
	@Override
	public void doFilter(ServletRequest abstractRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (abstractRequest instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) abstractRequest;
			if (request.getSession().isNew()) {
				fileStateService.createSession(request.getSession().getId());
			}
			
			if (request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid()) {
				fileStateService.clearSession(request.getRequestedSessionId());
			}
		}
		chain.doFilter(abstractRequest, response);
	}
}
