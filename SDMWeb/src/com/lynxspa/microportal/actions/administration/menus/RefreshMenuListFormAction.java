package com.lynxspa.microportal.actions.administration.menus;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.exception.FPMException;
import com.lynxspa.microportal.actions.MicroportalBasicAction;

public class RefreshMenuListFormAction extends MicroportalBasicAction {

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String menuIdLinkParameter = (String) _parameters.get("menuIdLinkParameter");
		_request.getSession().setAttribute("menuIdLinkParameter", menuIdLinkParameter);

	}

}
