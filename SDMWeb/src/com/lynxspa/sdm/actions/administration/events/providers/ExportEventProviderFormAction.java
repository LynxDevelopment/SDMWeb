package com.lynxspa.sdm.actions.administration.events.providers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.providers.CAExternalEventProvider;
import com.lynxspa.sdm.entities.events.providers.CAFormatProvider;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.utils.ReflectionUtils;
import com.lynxspa.utils.StringUtils;
import com.lynxspa.xweb.actions.DownloadBasicAction;


public class ExportEventProviderFormAction extends DownloadBasicAction {
	
	private static final Logger logger = Logger.getLogger(ExportEventProviderFormAction.class);
    
	private static final String CONTENT_REPLACER="${contentHere}";
	private static final String TEMPLATE_EVENT_PROVIDER="com/lynxspa/sdm/actions/administration/events/providers/templates/ProviderAdapter.tmpl";
	private static final String FILE_PATH="com/lynxspa/sdm/dictionaries/providers/EventProviders.java";

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
	}
	
	protected StringBuffer processEventProvider(String _code,Class<? extends CAExternalEventProvider> _providerClass,String _name,String _format,String _idAtMessage) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append(ReflectionUtils.ensureJavaComplianceName(_code.toUpperCase()));
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_code));
			reply.append(',');
			reply.append(_providerClass.getName()+".class");
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_name));
			reply.append(',');
			reply.append("CAFormat."+ReflectionUtils.ensureJavaComplianceName(_format));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_idAtMessage));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTPROVIDER_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> downloadFiles(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		Map<String,String> reply=null;
		Query query=null;
		List<CAExternalEventProvider> eventProviders=null;
		String fileContent=null;
		StringBuffer contentBuffer=null;
		String template=null;
		int it1=0;
		
		try{
			reply=new HashMap<String, String>();
			//Recover template
			template=getTemplate(ExportEventProviderFormAction.TEMPLATE_EVENT_PROVIDER);
			logger.debug("Template:\n"+template);
			//Mount files
			query=_session.createQuery("from CAExternalEventProvider where auditor.deleted=:isDeleted");
			query.setBoolean("isDeleted",false);
			eventProviders=(List<CAExternalEventProvider>)query.list();
			contentBuffer=new StringBuffer();
			for(CAExternalEventProvider eventProvider:eventProviders){
				for(CAFormatProvider formatProvider:eventProvider.getFormats()){
					if(it1>0){
						contentBuffer.append(',');
						contentBuffer.append('\n');
					}
					contentBuffer.append(processEventProvider(eventProvider.getId(),eventProvider.getClass(),eventProvider.getName(),formatProvider.getMessageFormat().getId(),formatProvider.getIdAtMessage()));
					it1++;
				}
			}
			contentBuffer.append(';');
			fileContent=template;
			it1=fileContent.indexOf(ExportEventProviderFormAction.CONTENT_REPLACER);
			fileContent=fileContent.substring(0,it1)+contentBuffer.toString()+fileContent.substring(it1+ExportEventProviderFormAction.CONTENT_REPLACER.length());
			reply.put(ExportEventProviderFormAction.FILE_PATH, fileContent);
		}catch (FPMException e) {
			throw e;
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTPROVIDER_DOWNLOAD_ERROR,e);
		}
		
		return reply;
	}
}
