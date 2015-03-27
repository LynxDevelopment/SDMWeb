package com.lynxspa.sdm.actions.administration.events.normalization;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.configuration.CAEventConfig;
import com.lynxspa.sdm.entities.events.configuration.CAEventFieldConfig;
import com.lynxspa.sdm.entities.events.configuration.CAEventFieldMessageConfig;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFormat;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageType;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageTypeId;
import com.lynxspa.sdm.entities.events.providers.CAExternalEventProvider;
import com.lynxspa.sdm.entities.events.types.CAEventType;
import com.lynxspa.sdm.entities.events.types.CAEventTypeDetail;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.utils.StringUtils;

public class SaveNormalizateProcessorConfigFormAction extends CoacEnhancedBasicAction {

	
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		Query query=null;
		CAEventType eventType=null;
		CAEventTypeDetail eventTypeDetail=null;
		CAMessageFormat messageFormat=null;
		CAMessageType messageType=null;
		CAExternalEventProvider provider=null;
		CAEventConfig eventConfig=null;
		CAEventFieldConfig eventFieldConfig=null;
		CAEventFieldMessageConfig eventFieldMessageConfig=null;
		String script=null;
		boolean isConfigNew=false;
		
		try {
			//Load CAEventType
			eventType=(CAEventType)_session.load(CAEventType.class,(String)_request.getSession().getAttribute("normalization.config.select.eventType"));
			//Load CAEventConfig
			query=_session.createQuery("from CAEventConfig eventConfig where eventConfig.eventType=:eventType");
			query.setParameter("eventType",eventType);
			if((eventConfig=(CAEventConfig)query.uniqueResult())==null){
				eventConfig=new CAEventConfig(_user.getId(),eventType);
				HibernateUtils.customSave(_session, eventConfig, _user.getId());
			}
			//Load CAEventFieldConfigs
			for(String name:_parameters.keySet()){
				if(name.startsWith("fieldScript")){
					script=(String)_parameters.get(name);
					//Load CAEventTypeDetail
					eventTypeDetail=(CAEventTypeDetail)_session.load(CAEventTypeDetail.class, Long.valueOf(name.substring(11)));
					//Load CAEventFieldConfig
					query=_session.createQuery("from CAEventFieldConfig eventFieldConfig where eventFieldConfig.eventConfig=:eventConfig and eventFieldConfig.eventTypeDetail=:eventTypeDetail");
					query.setParameter("eventConfig",eventConfig);
					query.setParameter("eventTypeDetail",eventTypeDetail);
					if((eventFieldConfig=(CAEventFieldConfig)query.uniqueResult())==null){
						eventFieldConfig=new CAEventFieldConfig(_user.getId(),eventConfig,eventTypeDetail);
						HibernateUtils.customSave(_session, eventFieldConfig, _user.getId());
					}
					//Load CAMessageType
					String[] messageTypeIds=((String)_request.getSession().getAttribute("normalization.config.select.messageType")).split("\\|");
					messageFormat=(CAMessageFormat)_session.load(CAMessageFormat.class, messageTypeIds[0]);
					messageType=(CAMessageType)_session.load(CAMessageType.class,new CAMessageTypeId(messageFormat,messageTypeIds[1]));
					if(_request.getSession().getAttribute("normalization.config.select.externalProvider")!=null){
						String[] providerIds=((String)_request.getSession().getAttribute("normalization.config.select.externalProvider")).split("\\|");
						provider=(CAExternalEventProvider)_session.load(CAExternalEventProvider.class,providerIds[0]);
					}
					//Save or update CAEventFieldMessageConfig
					isConfigNew=false;
					if(provider==null){
						query=_session.createQuery("from CAEventFieldMessageConfig eventFieldMessageConfig where eventFieldMessageConfig.eventFieldConfig.id=:eventFieldConfigId and eventFieldMessageConfig.messageType.id.format.id=:messageFormatId and eventFieldMessageConfig.messageType.id.code=:messageTypeId and eventFieldMessageConfig.provider is null");
					}else{
						query=_session.createQuery("from CAEventFieldMessageConfig eventFieldMessageConfig where eventFieldMessageConfig.eventFieldConfig.id=:eventFieldConfigId and eventFieldMessageConfig.messageType.id.format.id=:messageFormatId and eventFieldMessageConfig.messageType.id.code=:messageTypeId and eventFieldMessageConfig.provider.id=:providerId");
					}
					query.setParameter("eventFieldConfigId",eventFieldConfig.getId());
					query.setParameter("messageFormatId",messageType.getId().getFormat().getId());
					query.setParameter("messageTypeId",messageType.getId().getCode());
					if(provider!=null)
						query.setParameter("providerId",provider.getId());
					if((eventFieldMessageConfig=(CAEventFieldMessageConfig)query.uniqueResult())==null){
						if(provider==null){
							eventFieldMessageConfig=new CAEventFieldMessageConfig(_user.getId(),eventFieldConfig,messageType);
						}else{
							eventFieldMessageConfig=new CAEventFieldMessageConfig(_user.getId(),eventFieldConfig,messageType,provider);
						}
						isConfigNew=true;
					}
					eventFieldMessageConfig.setNormalizationScript(script);
					if((!isConfigNew)||((isConfigNew)&&(StringUtils.checkNotExist(script,null)!=null))){
						HibernateUtils.customSaveOrUpdate(_session, eventFieldMessageConfig,_user.getId());
					}
				}
			}
			if(messageType!=null)
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.NORMALIZATION_CONFIG_CHANGED,new Object[]{eventConfig.getEventType().getId(),messageType.getId().getFormat().getName(),messageType.getName(),((provider!=null)? provider.getName() : "all")},null,false);
			clearCache(_session,_user.getId());
		}catch(FPMException e){
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),e,true);
			throw e;
		}catch(HibernateException e) {
			FPMException newException=new FPMException(ErrorDict.HIBERNATE_ERROR,e);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw newException;
		}catch(Throwable e) {
			FPMException newException=new FPMException(e);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw newException;
		}
	}
}
