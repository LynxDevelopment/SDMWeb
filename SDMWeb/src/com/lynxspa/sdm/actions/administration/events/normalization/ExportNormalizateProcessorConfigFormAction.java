package com.lynxspa.sdm.actions.administration.events.normalization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.configuration.CAEventFieldMessageConfig;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.utils.ReflectionUtils;
import com.lynxspa.utils.StringUtils;
import com.lynxspa.xweb.actions.DownloadBasicAction;


public class ExportNormalizateProcessorConfigFormAction extends DownloadBasicAction {

	private static final Logger logger = Logger.getLogger(ExportNormalizateProcessorConfigFormAction.class);
    
	private static final String NAME_REPLACER="\\$\\{enumNameHere\\}";
	private static final String CONTENT_REPLACER="${contentHere}";
	private static final String TEMPLATE_CONFIG="com/lynxspa/sdm/actions/administration/events/normalization/templates/EventFieldMessageConfigAdapter.tmpl";
	private static final String TEMPLATE_PROVIDER_CONFIG="com/lynxspa/sdm/actions/administration/events/normalization/templates/EventFieldMessageProviderConfigAdapter.tmpl";
	private static final String FILE_PATH="com/lynxspa/sdm/dictionaries/events/configuration/";
	
	
	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
	}
	
	protected StringBuffer processEventFieldMessageConfig(int _counter,String _messageFormatId,String _messageTypeId,String _providerId,CAEventFieldMessageConfig _config) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append("CONFIG");
			reply.append(_counter+1);
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_config.getEventFieldConfig().getEventTypeDetail().getFieldPath()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageFormatId));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageTypeId));
			if(_providerId!=null){
				reply.append(',');
				reply.append(StringUtils.stringToLiteral(_providerId));
			}
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_config.getNormalizationScript()));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.NORMALIZATION_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	protected boolean fileMustChange(String _eventTypeId,String _messageFormatId,String _messageTypeId,String _providerId,CAEventFieldMessageConfig _config){
		
		boolean reply=false;
		String configProviderId=null;
		String configMessageFormatId=null;
		String configMessageTypeId=null;
		String configEventTypeId=null;

		configEventTypeId=_config.getEventFieldConfig().getEventConfig().getEventType().getId();
		configProviderId=(_config.getProvider()!=null)? _config.getProvider().getId() : null;
		configMessageTypeId=_config.getMessageType().getId().getCode();
		configMessageFormatId=_config.getMessageType().getId().getFormat().getId();
		if(!configEventTypeId.equals(_eventTypeId)){
			reply=true;
		}else{
			if((!configMessageFormatId.equals(_messageFormatId))||(!configMessageTypeId.equals(_messageTypeId))){
				reply=true;
			}else{
				if(((_providerId==null)&&(configProviderId!=null))||((_providerId!=null)&&(!_providerId.equals(configProviderId))))
					reply=true;
			}
		}
		
		return reply;
	}

	protected String generateNewFile(String _template,String _fileName,StringBuffer _content){
		
		String reply=null;
		int it1=0;
		
		reply=_template.replaceAll(ExportNormalizateProcessorConfigFormAction.NAME_REPLACER,_fileName);
		it1=reply.indexOf(ExportNormalizateProcessorConfigFormAction.CONTENT_REPLACER);
		reply=reply.substring(0,it1)+_content.toString()+reply.substring(it1+ExportNormalizateProcessorConfigFormAction.CONTENT_REPLACER.length());

		return reply;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> downloadFiles(HttpServletRequest _request,Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		Map<String,String> reply=null;
		String templateConfig=null;
		String templateProviderConfig=null;
		String newFile=null;
		String fileName=null;
		String fullPathName=null;
		StringBuffer fileContent=null;
		String eventTypeId=null;
		String providerId=null;
		String messageFormatId=null;
		String messageTypeId=null;
		boolean hasChanged=false;
		Query query=null;
		List<CAEventFieldMessageConfig> configs=null;
		int ic1=0;
		
		try{
			reply=new HashMap<String, String>();
			//Recover template
			templateConfig=getTemplate(ExportNormalizateProcessorConfigFormAction.TEMPLATE_CONFIG);
			logger.debug("Template:\n"+templateConfig);
			templateProviderConfig=getTemplate(ExportNormalizateProcessorConfigFormAction.TEMPLATE_PROVIDER_CONFIG);
			logger.debug("TemplateProviderConfig:\n"+templateProviderConfig);
			//Mount Files
			query=_session.createQuery("" +
					"select " +
					"	eventFieldMessageConfig " +
					"from " +
					"	CAEventFieldMessageConfig eventFieldMessageConfig " +
					"	join " +
					"		eventFieldMessageConfig.eventFieldConfig as eventFieldConfig " +
					"	join " +
					"		eventFieldConfig.eventConfig as eventConfig " +
					"where " +
					"	eventFieldMessageConfig.auditor.deleted=:isDeleted " +
					"order by " +
					"	eventConfig.eventType.id," +
					"	eventFieldMessageConfig.messageType.id.format.id," +
					"	eventFieldMessageConfig.messageType.id.code," +
					"	eventFieldMessageConfig.provider.id");
			query.setBoolean("isDeleted",false);
			configs=query.list();
			for(CAEventFieldMessageConfig config:configs){
				hasChanged=fileMustChange(eventTypeId,messageFormatId,messageTypeId,providerId,config);
				if(hasChanged){
					if(fileContent!=null){
						fileContent.append(';');
						newFile=generateNewFile(((providerId!=null)? templateProviderConfig : templateConfig),fileName,fileContent);
						logger.debug("Final file ["+fullPathName+"]:\n"+newFile);
						reply.put(fullPathName+".java",newFile);
					}
					eventTypeId=config.getEventFieldConfig().getEventConfig().getEventType().getId();
					providerId=(config.getProvider()!=null)? config.getProvider().getId() : null;
					messageTypeId=config.getMessageType().getId().getCode();
					messageFormatId=config.getMessageType().getId().getFormat().getId();
					fileName=eventTypeId+"Event"+messageFormatId+"Format"+messageTypeId+"Type";
					fileName+=(providerId!=null)? providerId+"ProviderConfig" : "Config";
					fileName=ReflectionUtils.ensureJavaComplianceName(fileName);
					fullPathName=ExportNormalizateProcessorConfigFormAction.FILE_PATH+fileName;
					fileContent=new StringBuffer();
					ic1=0;
				}else{
					fileContent.append(',');
					fileContent.append('\n');
				}
				fileContent.append(processEventFieldMessageConfig(ic1,messageFormatId,messageTypeId,providerId,config));
				ic1++;
			}
			if(fileContent!=null){
				fileContent.append(';');
				newFile=generateNewFile(((providerId!=null)? templateProviderConfig : templateConfig),fileName,fileContent);
				logger.debug("Final file ["+fullPathName+"]:\n"+newFile);
				reply.put(fullPathName+".java",newFile);
			}
		}catch (FPMException e) {
			throw e;
		}catch (Exception e) {
			throw new FPMException(ErrorDict.NORMALIZATION_DOWNLOAD_ERROR,e);
		}
		
		return reply;
	}
}
