package com.lynxspa.sdm.actions.masterrecord.manual;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.events.InsertEventFormAction;
import com.lynxspa.sdm.events.EventMainValidator;

public class InsertMasterRecordEventFormAction extends InsertEventFormAction{
	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException{
		super.performAction(_request, _session, _user, _parameters);
	}

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException{
		EventMainValidator eventValidator = new EventMainValidator(this.getCurrentSession());
		Object rawEventGroupId = _parameters.get(InsertEventFormAction.EVENT_GROUP_ID_PARAM);
		if(eventValidator.isBlank(rawEventGroupId)){
			_errors.add(new ValidationException("blank event group ID", InsertEventFormAction.EVENT_GROUP_ID_PARAM, ""));
		}
		super.validate(_parameters, _errors);
	}
}
