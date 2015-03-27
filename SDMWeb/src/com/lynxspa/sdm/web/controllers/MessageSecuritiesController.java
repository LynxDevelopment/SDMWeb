package com.lynxspa.sdm.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lynxspa.entities.jobs.SDMStaticRow;
import com.lynxspa.sdm.core.services.PersistenceService;

@Controller
@RequestMapping("/message/securities/")
public class MessageSecuritiesController {

	private @Autowired
	PersistenceService		persistenceService;
	
	@RequestMapping("details.sdo")
	public String messageDetails(HttpServletRequest request, ModelMap model) {
		
		System.out.println("Pasando");
		long idMessage = Long.parseLong(request.getParameter("messageSecurityId"));
		System.out.println("Pasando:"+idMessage);
		SDMStaticRow row = persistenceService.get(SDMStaticRow.class, idMessage);
		
		model.put("messageSecurity", row);
		model.put("messageValues", row.getSdmValues());

		return "message.security.details";
	}
	
	@RequestMapping("originalMessagePopUp.sdo")
	public String originalMessage(HttpServletRequest request, ModelMap model) {
		
		System.out.println("Pasando");
		long idMessage = Long.parseLong(request.getParameter("messageId"));
		System.out.println("Pasando:"+idMessage);
		SDMStaticRow row = persistenceService.get(SDMStaticRow.class, idMessage);
		
		
		model.put("originalMessage", row.getValue());

		return "message.security.original";
	}
}
