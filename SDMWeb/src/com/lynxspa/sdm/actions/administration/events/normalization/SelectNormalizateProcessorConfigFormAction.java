package com.lynxspa.sdm.actions.administration.events.normalization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lynxit.webcomp.StateBeansManager;
import com.lynxit.webcomp.commands.form.FormStateBean;
import com.lynxit.webcomp.exceptions.ComponentStateBeanNotFoundException;
import com.lynxit.webcomp.exceptions.ComponentStateBeanPropertyNotFoundException;
import com.lynxspa.xweb.actions.UpdateFormAction;

public class SelectNormalizateProcessorConfigFormAction extends UpdateFormAction {

	protected void perform(HttpServletRequest _request, HttpServletResponse _response) throws IOException, ServletException {
		
		FormStateBean form=null;
		
		try {
			if("true".equals(this.getForm().getComponent("selectedNormalizationProcessorConfig").getProperty("value"))){
				try {
					form=(FormStateBean)this.getStateBean("normalizateProcessorConfigForm");
					StateBeansManager.getInstance(_request.getSession()).remove(form.getId());
				} catch (ComponentStateBeanNotFoundException e) {
				}
				_request.getSession().setAttribute("normalization.config.select.messageType",this.getForm().getComponent("messageType").getProperty("value"));
				_request.getSession().setAttribute("normalization.config.select.externalProvider",this.getForm().getComponent("externalProvider").getProperty("value"));
				_request.getSession().setAttribute("normalization.config.select.eventType",this.getForm().getComponent("eventType").getProperty("value"));
			}
		} catch (ComponentStateBeanNotFoundException e) {
			e.printStackTrace();
		} catch (ComponentStateBeanPropertyNotFoundException e) {
			e.printStackTrace();
		}

		super.perform(_request,_response);
	}
}
