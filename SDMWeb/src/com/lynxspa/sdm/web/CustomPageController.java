package com.lynxspa.sdm.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lynxspa.sdm.core.services.PersistenceService;
import com.lynxspa.sdm.core.services.utils.LogService;


@Controller
public class CustomPageController {
	private static final Logger	logger	= Logger.getLogger(CustomPageController.class);

	private @Autowired
	PersistenceService			persistenceService;

	private @Autowired
	LogService					logService;

	@RequestMapping("/error/500/show.sdo")
	public String internalServerError() {
		System.out.println("Error 500");
		return "error.500";
	}
}
