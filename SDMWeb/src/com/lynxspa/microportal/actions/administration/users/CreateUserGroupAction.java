package com.lynxspa.microportal.actions.administration.users;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.hibernateentities.groups.Group;
import com.lynxit.xweb.hibernateentities.groups.UserGroup;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.exception.FPMException;
import com.lynxspa.microportal.actions.MicroportalBasicAction;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class CreateUserGroupAction extends MicroportalBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("groupId", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("groupName", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		Group group = new UserGroup();
		group.setId((String) _parameters.get("groupId"));
		group.setDescription((String) _parameters.get("groupName"));
		_session.save(group);
	}
}
