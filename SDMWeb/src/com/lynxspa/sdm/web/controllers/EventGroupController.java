package com.lynxspa.sdm.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lynxspa.entities.securities.assets.SecurityAsset;
import com.lynxspa.sdm.core.services.PersistenceService;
import com.lynxspa.sdm.web.beans.EventGroupBean;
import com.lynxspa.sdm.web.beans.GroupBean;

@Controller
@RequestMapping("/event/securities/")
public class EventGroupController {

	private @Autowired
	PersistenceService		persistenceService;
	
	@RequestMapping("listEventsGroup.sdo")
	public String messageDetails(HttpServletRequest request, ModelMap model, @ModelAttribute GroupBean gb) {
		/*int resultsPerPage= 10;
		int totalElements = totalElements();
		
		
		EventGroupBean eventGroup = new EventGroupBean();
		eventGroup.setSecurityAssetList(getEventsGroup(0,10));
		eventGroup.setListSize(totalElements);
		eventGroup.setResultsPerPage(resultsPerPage);
		eventGroup.setAvailablePages(totalElements%resultsPerPage==0?totalElements%resultsPerPage:(totalElements%resultsPerPage)+1);
		eventGroup.setCurrentPage(1);
		model.put("eventGroupList",eventGroup);*/
		
		List<SecurityAsset> saList = new ArrayList<SecurityAsset>();
		saList = getEventsGroup(0,10, gb);
		
		System.out.println("saList.size():"+saList.size());
		model.put("pageSize",8);
		model.put("sPageSize","8");
		if(gb!=null){
			model.put("GroupBean", gb);
		}else{
			model.put("GroupBean", new GroupBean());
		}
		model.put("eventGroupList",saList);

		return "event.group.list";
	}
	
	private int totalElements (){
		int reply=0;
		
		String query="select count(asset) from SecurityAsset as asset where asset.auditor.deleted=0 and asset.operationStatus.state.id.code='NORM' and asset.class='security' and asset.concatField is not null";
		long total = (Long) persistenceService.executeQueryForObject(query);
		reply = (int)(total);
		
		return reply;
	}
	
	private List<SecurityAsset> getEventsGroup (Integer first, Integer count, GroupBean gb){
		List<SecurityAsset> reply= new ArrayList<SecurityAsset>();
		String query="from SecurityAsset as asset where asset.auditor.deleted=0 and asset.operationStatus.state.id.code='NORM' and asset.class='security' and asset.concatField is not null";
		if(gb!=null){
			if(gb.getIsin()!=null && gb.getIsin().compareToIgnoreCase("")!=0){
				query += " and asset.isin= '" + gb.getIsin() + "'";
			}
			if(gb.getName()!=null && gb.getName().compareToIgnoreCase("")!=0){
				query += " and asset.name= '" + gb.getName() + "'";
			}
			if(gb.getCusip()!=null && gb.getCusip().compareToIgnoreCase("")!=0){
				query += " and asset.cusip= '" + gb.getCusip() + "'";
			}
			if(gb.getProvider()!=null && gb.getProvider().compareToIgnoreCase("")!=0){
				query += " and asset.provider.name= '" + gb.getProvider() + "'";
			}
		}
		System.out.println("query:"+query.toString());
		//reply = (List<SecurityAsset>) persistenceService.executeQueryForList(query, first, count,null);
		reply = (List<SecurityAsset>) persistenceService.executeQueryForList(query);

		return reply;
	}
}

