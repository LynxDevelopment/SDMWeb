package com.lynxspa.sdm.actions.administration.events.masterrecord;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.configuration.CAEventConfig;
import com.lynxspa.sdm.entities.events.configuration.CAEventMasterRecordConfig;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.utils.StringUtils;
import com.lynxspa.xweb.actions.DownloadBasicAction;


public class ExportMasterRecordConfigFormAction extends DownloadBasicAction {

	private static final Logger logger = Logger.getLogger(ExportMasterRecordConfigFormAction.class);
    
	private static final String NAME_REPLACER="\\$\\{enumNameHere\\}";
	private static final String CONTENT_REPLACER="${contentHere}";
	private static final String TEMPLATE_CONFIG="com/lynxspa/sdm/actions/administration/events/masterrecord/templates/EventConfigAdapter.tmpl";
	private static final String TEMPLATE_MASTERRECORD_CONFIG="com/lynxspa/sdm/actions/administration/events/masterrecord/templates/EventMRConfigAdapter.tmpl";
	private static final String FILE_PATH="com/lynxspa/sdm/dictionaries/events/configuration/masterrecord/";
	
	
	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
	}
	
	protected StringBuffer processEventConfig(CAEventConfig _eventConfig) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append(_eventConfig.getEventType().getId());
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_eventConfig.getEventType().getId()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral((_eventConfig.getPreferentProvider()!=null)? _eventConfig.getPreferentProvider().getId() : null));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral((_eventConfig.getSecondPreferentProvider()!=null)? _eventConfig.getSecondPreferentProvider().getId() : null));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_eventConfig.getOnPrimaryAndSecondaryNotFound()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_eventConfig.getOnCustodianAndManualNotFound()));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENT_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	protected StringBuffer processEventFieldMessageConfig(CAEventMasterRecordConfig _config) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append("RULE");
			reply.append(_config.getRuleOrder());
			reply.append('(');
			reply.append(_config.getRuleOrder());
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_config.getTarget()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_config.getDetail().getFieldPath()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_config.getCondition()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_config.getOnTrueResult()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_config.getOnFalseResult()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_config.getConditionParams()));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENT_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	protected String generateNewFile(String _template,String _enumName,StringBuffer _content){
		
		String reply=null;
		int it1=0;
		
		reply=_template.replaceAll(ExportMasterRecordConfigFormAction.NAME_REPLACER,_enumName);
		it1=reply.indexOf(ExportMasterRecordConfigFormAction.CONTENT_REPLACER);
		reply=reply.substring(0,it1)+_content.toString()+reply.substring(it1+ExportMasterRecordConfigFormAction.CONTENT_REPLACER.length());

		return reply;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> downloadFiles(HttpServletRequest _request,Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		Map<String,String> reply=null;
		List<CAEventMasterRecordConfig> masterRecordConfigs=null;
		StringBuffer masterRecordConfigContent=null;
		String templateMasterRecordConfig=null;
		Iterator<CAEventConfig> configs=null;
		StringBuffer eventConfigContent=null;
		CAEventConfig eventConfig=null;
		String templateConfig=null;
		String newFile=null;
		String enumName=null;
		Query query=null;
		
		try{
			reply=new HashMap<String, String>();
			//Recover template
			templateConfig=getTemplate(ExportMasterRecordConfigFormAction.TEMPLATE_CONFIG);
			logger.debug("Template:\n"+templateConfig);
			templateMasterRecordConfig=getTemplate(ExportMasterRecordConfigFormAction.TEMPLATE_MASTERRECORD_CONFIG);
			logger.debug("templateMasterRecordConfig:\n"+templateMasterRecordConfig);
			//Mount Files
			query=_session.createQuery("select eventConfig from CAEventConfig eventConfig");
			configs=query.iterate();
			while(configs.hasNext()){
				if(eventConfigContent!=null){
					eventConfigContent.append(',');
					eventConfigContent.append('\n');
				}else{
					eventConfigContent=new StringBuffer();
				}
				eventConfig=configs.next();
				eventConfigContent.append(processEventConfig(eventConfig));
				masterRecordConfigs=eventConfig.getMasterRecordConfigs();
				if((masterRecordConfigs!=null)&&(masterRecordConfigs.size()>0)){
					masterRecordConfigContent=null;
					for(CAEventMasterRecordConfig masterRecordConfig:masterRecordConfigs){
						if(masterRecordConfigContent!=null){
							masterRecordConfigContent.append(',');
							masterRecordConfigContent.append('\n');
						}else{
							masterRecordConfigContent=new StringBuffer();
						}
						masterRecordConfigContent.append(processEventFieldMessageConfig(masterRecordConfig));
					}
					masterRecordConfigContent.append(';');
					enumName=eventConfig.getEventType().getId()+"MRConfig";
					newFile=generateNewFile(templateMasterRecordConfig,enumName,masterRecordConfigContent);
					logger.debug("Final file ["+ExportMasterRecordConfigFormAction.FILE_PATH+enumName+"]:\n"+newFile);
					reply.put(ExportMasterRecordConfigFormAction.FILE_PATH+enumName+".java",newFile);
				}
			}
			if(eventConfigContent!=null){
				eventConfigContent.append(';');
				enumName="EventConfig";
				newFile=generateNewFile(templateConfig,enumName,eventConfigContent);
				logger.debug("Final file ["+ExportMasterRecordConfigFormAction.FILE_PATH+enumName+"]:\n"+newFile);
				reply.put(ExportMasterRecordConfigFormAction.FILE_PATH+enumName+".java",newFile);
			}
		}catch (FPMException e) {
			throw e;
		}catch (Throwable e) {
			throw new FPMException(ErrorDict.MASTERRECORD_DOWNLOAD_ERROR,e);
		}
		
		return reply;
	}
}
