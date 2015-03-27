package com.lynxspa.sdm.actions.administration.events.masterrecord;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;

public class UpdateMasterRecordConfigFormAction extends CoacEnhancedBasicAction {

	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
	}
}
