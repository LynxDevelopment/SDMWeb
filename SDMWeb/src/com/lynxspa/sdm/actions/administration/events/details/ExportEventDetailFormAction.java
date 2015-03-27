package com.lynxspa.sdm.actions.administration.events.details;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.types.CAEventType;
import com.lynxspa.sdm.entities.events.types.CAEventTypeDetail;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.utils.StringUtils;
import com.lynxspa.xweb.actions.DownloadBasicAction;


public class ExportEventDetailFormAction extends DownloadBasicAction {
	
	private static final Logger logger = Logger.getLogger(ExportEventDetailFormAction.class);
    
	private static final String NAME_REPLACER="\\$\\{enumNameHere\\}";
	private static final String CONTENT_REPLACER="${contentHere}";
	private static final String TEMPLATE_EVENT_DETAIL="com/lynxspa/sdm/actions/administration/events/details/templates/EventDetailAdapter.tmpl";
	private static final String FILE_PATH="com/lynxspa/sdm/dictionaries/events/";

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
	}
	
	protected StringBuffer processEventDetail(int _counter,CAEventTypeDetail _eventTypeDetail) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append("DETAIL");
			reply.append(_counter+1);
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_eventTypeDetail.getFieldPath()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_eventTypeDetail.getName()));
			reply.append(',');
			reply.append("ClassType.");
			reply.append(_eventTypeDetail.getFieldType());
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_eventTypeDetail.getFormat()));
			reply.append(',');
			reply.append(_eventTypeDetail.getMaxLength());
			reply.append(',');
			reply.append(_eventTypeDetail.isDisplayInTable());
			reply.append(',');
			reply.append(_eventTypeDetail.getDisplayInTableOrder());
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_eventTypeDetail.getDisplayGroup()));
			reply.append(',');
			reply.append(_eventTypeDetail.getDisplayGroupOrder());
			reply.append(',');
			reply.append(_eventTypeDetail.getDisplayInGroupOrder());
			reply.append(',');
			reply.append(_eventTypeDetail.getDisplayRow());
			reply.append(',');
			reply.append(_eventTypeDetail.getDisplayColumn());
			reply.append(',');
			reply.append(_eventTypeDetail.getDisplayTop());
			reply.append(',');
			reply.append(_eventTypeDetail.getDisplayLeft());
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_eventTypeDetail.getDescription()));
			reply.append(',');
			reply.append(String.valueOf(_eventTypeDetail.isExtension()));
			reply.append(',');
			reply.append(String.valueOf(_eventTypeDetail.isHidden()));
			reply.append(',');
			reply.append(String.valueOf(_eventTypeDetail.isBasic()));
			reply.append(',');
			reply.append(String.valueOf(_eventTypeDetail.isEditableNormalization()));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTDETAIL_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> downloadFiles(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		Map<String,String> reply=null;
		Query query=null;
		List<CAEventType> eventTypes=null;
		List<CAEventTypeDetail> eventTypeDetails=null;
		String fileName=null;
		String fileContent=null;
		StringBuffer contentBuffer=null;
		String template=null;
		int it1=0;
		
		try{
			reply=new HashMap<String, String>();
			//Recover template
			template=getTemplate(ExportEventDetailFormAction.TEMPLATE_EVENT_DETAIL);
			logger.debug("Template:\n"+template);
			//Mount files
			query=_session.createQuery("from CAEventType");
			eventTypes=(List<CAEventType>)query.list();
			for(CAEventType eventType:eventTypes){
				query=_session.createQuery("from CAEventTypeDetail where auditor.deleted=:isDeleted and eventType.id=:eventTypeId order by displayGroupOrder,displayInGroupOrder");
				query.setBoolean("isDeleted",false);
				query.setString("eventTypeId",eventType.getId());
				eventTypeDetails=(List<CAEventTypeDetail>)query.list();
				if(eventTypeDetails.size()>0){
					fileName=eventType.getId()+"EventDetail";
					contentBuffer=new StringBuffer();
					for(int ic1=0;ic1<eventTypeDetails.size();ic1++){
						if(ic1>0){
							contentBuffer.append(',');
							contentBuffer.append('\n');
						}
						CAEventTypeDetail eventTypeDetail=eventTypeDetails.get(ic1);
						contentBuffer.append(processEventDetail(ic1,eventTypeDetail));
					}
					contentBuffer.append(';');
					fileContent=template.replaceAll(ExportEventDetailFormAction.NAME_REPLACER,fileName);
					it1=fileContent.indexOf(ExportEventDetailFormAction.CONTENT_REPLACER);
					fileContent=fileContent.substring(0,it1)+contentBuffer.toString()+fileContent.substring(it1+ExportEventDetailFormAction.CONTENT_REPLACER.length());
					reply.put(ExportEventDetailFormAction.FILE_PATH+fileName+".java", fileContent);
				}
			}
		}catch (FPMException e) {
			throw e;
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTDETAIL_DOWNLOAD_ERROR,e);
		}
		
		return reply;
	}
}
