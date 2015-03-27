package com.lynxspa.microportal.initializers;

import javax.servlet.ServletContext;

import org.w3c.dom.Element;

import com.lynxit.utils.configuration.ConfigurationException;
import com.lynxit.xweb.initialization.Initializable;
import com.lynxspa.microportal.MicroportalManager;

public class MicroportalInitializer implements Initializable {

	@Override
	public void init(Element element, ServletContext servletContext) throws ConfigurationException {
		MicroportalManager.getInstance();
	}

	@Override
	public void terminate(ServletContext servletContext) throws Exception {
		MicroportalManager.getInstance().initialize();
	}
}
