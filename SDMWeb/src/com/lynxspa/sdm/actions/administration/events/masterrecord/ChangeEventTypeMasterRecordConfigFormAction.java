package com.lynxspa.sdm.actions.administration.events.masterrecord;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.StateBeansManager;
import com.lynxit.webcomp.commands.form.FormStateBean;
import com.lynxit.webcomp.exceptions.ComponentStateBeanNotFoundException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;

public class ChangeEventTypeMasterRecordConfigFormAction extends CoacEnhancedBasicAction {

	FormStateBean form=null;

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		_request.getSession().removeAttribute("varMasterRecordRulesConfigInstance");
		try {
			form=(FormStateBean)this.getStateBean("masterRecordConfigForm");
			StateBeansManager.getInstance(_request.getSession()).remove(form.getId());
		} catch (ComponentStateBeanNotFoundException e) {
		}
	}
}
