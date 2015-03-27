package com.lynxspa.sdm.core.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.lynxspa.sdm.core.ContextPathHolder;

public class ConfigurationListener implements ServletContextListener {

	private static final Logger	logger	= Logger.getLogger(ConfigurationListener.class);

	@Override
	public void contextInitialized(ServletContextEvent ctxEv) {
		ContextPathHolder cph = ContextPathHolder.getInstance();
		ServletContext ctx = ctxEv.getServletContext();

		cph.setContextPath(ctx.getServletContextName() + "/");
		cph.setAbsolutePath(ctx.getInitParameter("GPM_CTX_PATH"));
		cph.setBaseUrl(ctx.getInitParameter("GPM_BASE_URL"));

		logger.info("+     ConfigurationListener   ");
		logger.info("+ --------------------------- ");
		logger.info("+ Context Path:             ");
		logger.info("+ " + cph.getContextPath());

		if (cph.getAbsolutePath() == "") {
			logger.error("+ GPM_CTX_PATH init parameter is empty. You must set it in the web.xml");
			logger.error("+ Could not load the application properly.");
			return;
		}
		if (cph.getBaseUrl() == "") {
			logger.error("+ GPM_BASE_URL init parameter is empty. You must set it in the web.xml");
			logger.error("+ Could not load the application properly.");
			return;
		}
		logger.info("+ Absolute Path:             ");
		logger.info("+ " + cph.getAbsolutePath());
		logger.info("+ Base URL:             ");
		logger.info("+ " + cph.getBaseUrl());
		logger.info(" ------Load Successful------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent ctxEv) {
		// Nothing to do
	}

}
