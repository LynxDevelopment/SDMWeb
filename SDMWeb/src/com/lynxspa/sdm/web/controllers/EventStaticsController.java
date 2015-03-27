package com.lynxspa.sdm.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lynxspa.entities.securities.assets.Asset;
import com.lynxspa.sdm.core.services.PersistenceService;
import com.lynxspa.sdm.web.beans.EventStaticsBean;

@Controller
@RequestMapping("/event/securities/")
public class EventStaticsController {

	private @Autowired
	PersistenceService		persistenceService;
	
	@RequestMapping("listStaticEvents.sdo")
	public String messageDetails(HttpServletRequest request, ModelMap model) {
		int resultsPerPage= 10;
		int totalElements = totalElements();
		
		
		EventStaticsBean eventStatic = new EventStaticsBean();
		eventStatic.setAssetList( getStaticEvents(0,10));
		eventStatic.setListSize(totalElements);
		eventStatic.setResultsPerPage(resultsPerPage);
		eventStatic.setAvailablePages(totalElements%resultsPerPage==0?totalElements%resultsPerPage:(totalElements%resultsPerPage)+1);
		eventStatic.setCurrentPage(1);
		model.put("staticEventList",eventStatic);

		return "event.static.list";
	}
	
	private int totalElements (){
		int reply=0;
		
		String query="select count(asset) from SecurityAsset as asset where asset.auditor.deleted=0 and asset.operationStatus.state.id.code='NORM' and asset.class='security'";
		long total = (Long) persistenceService.executeQueryForObject(query);
		reply = (int)(total);
		
		return reply;
	}
	
	private List<Asset> getStaticEvents (Integer first, Integer count){
		List<Asset> reply= new ArrayList<Asset>();
		String query="from SecurityAsset as asset where asset.auditor.deleted=0 and asset.operationStatus.state.id.code='NORM' and asset.class='security'";
		
		reply = (List<Asset>) persistenceService.executeQueryForList(query, first, count,null);

		return reply;
	}
}
