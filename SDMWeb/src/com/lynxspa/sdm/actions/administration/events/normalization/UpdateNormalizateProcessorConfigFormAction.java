package com.lynxspa.sdm.actions.administration.events.normalization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lynxit.webcomp.dataset.DataSet;
import com.lynxit.webcomp.exceptions.ComponentStateBeanNotFoundException;
import com.lynxspa.xweb.actions.UpdateFormAction;

public class UpdateNormalizateProcessorConfigFormAction extends UpdateFormAction {

	protected void perform(HttpServletRequest _request, HttpServletResponse _response) throws IOException, ServletException {

		try{
			try {
				((DataSet)this.getStateBean("varEventFieldConfig")).setCurrentRow((String)this.getParameter("currentEventFieldRowId"));
			} catch (ComponentStateBeanNotFoundException e) {}
			try {
				((DataSet)this.getStateBean("varEventMessageDataset")).setCurrentRow((String)this.getParameter("currentMessageRowId"));
			} catch (ComponentStateBeanNotFoundException e) {}
		}catch(Exception e){
			e.printStackTrace();
		}
		super.perform(_request,_response);
	}
}
