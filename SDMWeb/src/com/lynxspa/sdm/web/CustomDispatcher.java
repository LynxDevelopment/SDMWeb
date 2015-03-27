package com.lynxspa.sdm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.DispatcherServlet;

public class CustomDispatcher extends DispatcherServlet {
	private static final long	serialVersionUID	= -8306178704572961233L;
	private static Logger		logger				= Logger.getLogger(CustomDispatcher.class);

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			super.service(req, resp);
		} catch (Exception e) {
			logger.error("CustomDispatcher registered an exception: " + e.getMessage(), e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
