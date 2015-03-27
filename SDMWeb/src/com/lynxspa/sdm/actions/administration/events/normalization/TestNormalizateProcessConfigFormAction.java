package com.lynxspa.sdm.actions.administration.events.normalization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtended;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtensionId;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.providers.CAExternalEventProvider;
import com.lynxspa.sdm.entities.events.types.CAEventType;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.normalize.NormalizeProcessorAdapter;
import com.lynxspa.sdm.processors.normalize.NormalizeResultBean;
import com.lynxspa.sdm.processors.normalize.NormalizeScriptConfigBean;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.exceptions.WarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class TestNormalizateProcessConfigFormAction extends CoacEnhancedBasicAction{

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		String fieldId=null;

		for(String name:_parameters.keySet()){
			if(name.startsWith("fieldScript")){
				fieldId=name.substring(11);
				ValidationUtils.validateField("fieldPath"+fieldId, _parameters, _errors, ValidationsDict.ISNOTEMPTY);
				ValidationUtils.validateField("fieldExtension"+fieldId, _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISBOOLEAN);
			}
		}
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		NormalizeProcessorAdapter normalizer=null;
		NormalizeResultBean normalizationResult=null;
		CAEventMessage message=null;
		List<NormalizeScriptConfigBean> scripts=null;
		NormalizeScriptConfigBean scriptConfig=null;
		String fieldId=null;
		CAEventCollected result=null;
		CAEventDetailExtended extension=null;
		CAEventType eventType=null;
		CAExternalEventProvider externalProvider=null;
		SPSecurity security=null;
		
		try{
			_request.getSession().removeAttribute("normalization.config.test.normalizationResult");
			_request.getSession().removeAttribute("normalization.config.test.eventCollected");
			_request.getSession().removeAttribute("normalization.config.test.exception");
			//Normalizations scripts
			scripts=new ArrayList<NormalizeScriptConfigBean>();
			for(String name:_parameters.keySet()){
				if(name.startsWith("fieldScript")){
					fieldId=name.substring(11);
					scriptConfig=new NormalizeScriptConfigBean((String)_parameters.get("fieldPath"+fieldId),(String)_parameters.get(name),(Boolean)_parameters.get("fieldExtension"+fieldId));
					scripts.add(scriptConfig);
				}
			}
			//Process scripts
			try{
				message=(CAEventMessage)_session.get(CAEventMessage.class,new Long((String)_parameters.get("currentMessageId")));
				if(message==null)
					throw new FPMException(WarningDict.IMPOSIBLE_NORMALIZATION_TEST_WITHOUT_EXAMPLES);
			}catch (Exception e) {
				throw new FPMException(WarningDict.IMPOSIBLE_NORMALIZATION_TEST_WITHOUT_EXAMPLES,e);
			}
			normalizer=(NormalizeProcessorAdapter)(SDMConfigManager.getInstance().getProcessor(_session, CAConfiguration.NORMALIZEPROCESSOR));
			normalizationResult=normalizer.test(_session, scripts, message);
			_request.getSession().setAttribute("normalization.config.test.normalizationResult",normalizationResult);
			if(normalizationResult.getException()==null){
				result=new CAEventCollected();
				for(CAEventDetailExtensionId extensionId:normalizationResult.getExtendedDetails().keySet()){
					extension=normalizationResult.getExtendedDetails().get(extensionId);
					extension.setMainDetail(normalizationResult.getDetail());
					normalizationResult.getDetail().addExtension(extension);
				}
				result.setEventDetail(normalizationResult.getDetail());
				externalProvider=(CAExternalEventProvider)_session.get(CAExternalEventProvider.class, message.getEventProvider().getId());
				_session.evict(externalProvider);
				result.setEventProvider(externalProvider);
				eventType=(CAEventType)_session.get(CAEventType.class, message.getNormalizedEventType().getId());
				_session.evict(eventType);
				result.setEventType(eventType);
				result.setExecutionDate(normalizationResult.getDetail().getExecutionDate());
				result.setExpirationDate(normalizationResult.getDetail().getExpirationDate());
				result.setSubscriptionDate(normalizationResult.getDetail().getSubscriptionDate());
				security=(SPSecurity)_session.get(SPSecurity.class, message.getNormalizedSecurity().getId());
				_session.evict(security);
				result.setSecurity(security);
				_request.getSession().setAttribute("normalization.config.test.eventCollected",result);
			}else{
				_request.getSession().setAttribute("normalization.config.test.exception",normalizationResult.getException());
			}
		}catch(FPMException e){
			_request.getSession().setAttribute("normalization.config.test.exception",e);
			throw e;
		}catch(Exception e){
			_request.getSession().setAttribute("normalization.config.test.exception",e);
			throw new FPMException(ErrorDict.UNEXPECTED_ERROR,e);
		}
	}
}
