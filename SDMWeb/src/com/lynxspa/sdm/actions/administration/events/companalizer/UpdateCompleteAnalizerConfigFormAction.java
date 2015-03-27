package com.lynxspa.sdm.actions.administration.events.companalizer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.configuration.CAEventConfig;
import com.lynxspa.sdm.entities.events.configuration.CAEventFieldConfig;
import com.lynxspa.sdm.entities.events.types.CAEventType;
import com.lynxspa.sdm.entities.events.types.CAEventTypeDetail;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class UpdateCompleteAnalizerConfigFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("paramEventType", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		Query query = null;
		Query query2 = null;
		CAEventType eventType = null;
		CAEventConfig eventConfig = null;
		CAEventFieldConfig eventFieldConfig = null;

		try{
			// First, we have to check if the CAEventConfig exists
			// We use the eventType in session to check the eventType Object
			eventType = (CAEventType) _session.load(CAEventType.class,(String) _parameters.get("paramEventType"));
			// With eventType object check CAEventConfig
			query = _session.createQuery("from CAEventConfig eventConfig where eventConfig.eventType=:eventType");
			query.setParameter("eventType", eventType);
			if ((eventConfig=(CAEventConfig)query.uniqueResult()) == null) {
				eventConfig=new CAEventConfig(_user.getId(), eventType);
				HibernateUtils.customSave(_session,eventConfig,_user.getId());
			}
			// iterate over fields to activate or disactivate the required flag
			query=_session.createQuery("from CAEventTypeDetail where eventType=:eventType and auditor.deleted=:isDeleted");
			query.setParameter("eventType", eventType);
			query.setParameter("isDeleted",false);
			for (CAEventTypeDetail eventTypeDetail : (List<CAEventTypeDetail>) query.list()) {
				// We are now sure that CAEventConfig exists so we check for
				// CAEventFieldConfig
				query2=_session.createQuery("from CAEventFieldConfig where eventConfig=:eventConfig and eventTypeDetail=:eventTypeDetail");
				query2.setParameter("eventConfig", eventConfig);
				query2.setParameter("eventTypeDetail", eventTypeDetail);
				if ((eventFieldConfig = (CAEventFieldConfig) query2.uniqueResult()) == null) {
					eventFieldConfig = new CAEventFieldConfig(_user.getId(),eventConfig, eventTypeDetail);
				}
				// We test if is required or not required looking up parameters
				// existent or not
				eventFieldConfig.setRequired((_parameters.get("cbox"+ eventTypeDetail.getId())!= null));
				HibernateUtils.customSaveOrUpdate(_session, eventFieldConfig,_user.getId());
			}
			clearCache(_session,_user.getId());
		} catch (HibernateException e) {
			throw new FPMException(ErrorDict.HIBERNATE_ERROR, e);
		} catch (Exception e) {
			throw new FPMException(ErrorDict.UNEXPECTED_ERROR, e);
		}
	}
}
