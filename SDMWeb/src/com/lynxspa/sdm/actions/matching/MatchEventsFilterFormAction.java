package com.lynxspa.sdm.actions.matching;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lynxit.webcomp.commands.form.FormStateBean;
import com.lynxspa.xweb.actions.UpdateFormAction;

public class MatchEventsFilterFormAction extends UpdateFormAction {

	private static final String MATCH_EVENTS_FORM_NAME = "matchEventsForm";
	
	@Override
	protected void perform(HttpServletRequest _request, HttpServletResponse _response) throws IOException, ServletException {
		try{
			if(null != this.getStateBean(MatchEventsFilterFormAction.MATCH_EVENTS_FORM_NAME)){
				((FormStateBean)this.getStateBean(MatchEventsFilterFormAction.MATCH_EVENTS_FORM_NAME)).clearErrors();
			}
		}
		catch(Exception e){
			throw new ServletException("Error cleaning error properties from FormStateBean object for form name \"" + MatchEventsFilterFormAction.MATCH_EVENTS_FORM_NAME + "\"");
		}
		super.perform(_request, _response);
	}
}
