package com.lynxspa.sdm.actions.administration.events.providers;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.providers.CACustodianEventProvider;
import com.lynxspa.sdm.entities.events.providers.CAEventProvider;
import com.lynxspa.sdm.entities.events.providers.CAExternalEventProvider;
import com.lynxspa.sdm.entities.events.providers.CAInfoEventProvider;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.WarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class InsertUpdateEventProviderFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		long count=0;
		
		ValidationUtils.validateField("operationType", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
		ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		Session session=getCurrentSession();
		Query query=session.createQuery("select count(*) from CAEventProvider where id=:providerId and auditor.deleted=:deleted");
		query.setString("providerId",(String)_parameters.get("id"));
		query.setBoolean("deleted",false);
		query.setFetchSize(1);
		count=(Long)query.uniqueResult();
		if(("UPDATE".equals(_parameters.get("operationType")))&&(count==0)) {
			_errors.add(new ValidationException(WarningDict.EVENTPROVIDERNOTFOUND.getMessageKey(),"id",(String)_parameters.get("id")));
		}else if(("INSERT".equals(_parameters.get("operationType")))&&(count>0)) {
			_errors.add(new ValidationException(WarningDict.EVENTPROVIDERIDALREADYEXIST.getMessageKey(),"id",(String)_parameters.get("id")));
		}
		ValidationUtils.validateField("name", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
		ValidationUtils.validateField("providerType", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		CAEventProvider eventProvider=null;
		PreparedStatement statement=null;

		try {
			if ("UPDATE".equals(_parameters.get("operationType"))) {
				eventProvider = (CAExternalEventProvider) _session.load(CAExternalEventProvider.class, String.valueOf(_parameters.get("id")));
			} else {
				final String providerType=((String[])_parameters.get("providerType"))[0];
				statement = _session.connection().prepareStatement("update TB_CA_EVENTPROVIDERS set EVENTPROVCLASS=?,ISDELETED=? where id=?");
				statement.setString(1,providerType);
				statement.setInt(2, 0);
				statement.setString(3, String.valueOf(_parameters.get("id")));
				statement.executeUpdate();
				eventProvider = (CAEventProvider) _session.get(CAEventProvider.class, String.valueOf(_parameters.get("id")));
				if (eventProvider == null) {
					if ("external.cust".equals(providerType)) {
						eventProvider = new CACustodianEventProvider();
					} else {
						eventProvider = new CAInfoEventProvider();
					}
					eventProvider.setId(String.valueOf(_parameters.get("id")));
				}
			}
			eventProvider.setName(String.valueOf(_parameters.get("name")));
			HibernateUtils.customSaveOrUpdate(_session, eventProvider, _user.getId());
			clearCache(_session,_user.getId());
		} catch (Exception e) {
			throw new FPMException(e);
		}
	}
}
