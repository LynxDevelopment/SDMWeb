package com.lynxspa.sdm.actions.administration.reports.questions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.answers.CAAnswerType;
import com.lynxspa.sdm.entities.events.answers.CAQuestions;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;

public class InsertUpdateQuestionDetailFormAction extends CoacEnhancedBasicAction {

	public final String YES = "S";
	
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		
	}
	@Override
	public void performAction(HttpServletRequest _request, Session _session,
			LoggedUser _user, Map<String, Object> _parameters)
			throws FPMException {
		CAQuestions question = null;
		CAAnswerType answerType = null;
		String text = null;
		boolean isHeader = false;
		boolean isHidden = false;
		boolean isOptional = false;
		String format=null;
		long headerGroup = 0l;
		int tablePosition = 0;
		int groupPosition = 0;
		String [] values = null;
		CAEventCollected event=null;
		String description=null;
		String defaultValue=null;
		
		try{
			text = String.valueOf(_parameters.get("text"));
			values = (String[])(_parameters.get("isheader"));
			isHeader = values[0].equals(YES)?true:false;
			values = (String[])(_parameters.get("ishidden"));
			isHidden = values[0].equals(YES)?true:false;
			values = (String[])(_parameters.get("isoptional"));
			isOptional = values[0].equals(YES)?true:false;
			format = String.valueOf(_parameters.get("format"));
			values = (String[])(_parameters.get("answerGroup"));
			headerGroup = Long.valueOf(values[0]);
			values = (String[])(_parameters.get("answerTypePosition"));
			tablePosition = Integer.parseInt(values[0]);
			values = (String[])(_parameters.get("groupPosition"));
			groupPosition = Integer.parseInt(values[0]);
			description = String.valueOf(_parameters.get("description"));
			defaultValue = String.valueOf(_parameters.get("defaultValue"));
			if (_parameters.get("answerId")!=null && !_parameters.get("answerId").equals("")){
				question=(CAQuestions)_session.get(CAQuestions.class, Long.valueOf(String.valueOf(_parameters.get("answerId"))));
				values = (String[])(_parameters.get("answerType"));
				answerType =(CAAnswerType)_session.get(CAAnswerType.class,values[0]);
				question.setText(text);
				question.setHeader(isHeader);
				question.setHidden(isHidden);
				question.setOptional(isOptional);
				question.setAnswerType(answerType);
				question.setHeaderGroup(headerGroup);
				question.setFormat(format);
				question.setPosition(tablePosition);
				question.setHeaderPosition(groupPosition);
				question.setDescription(description);
				question.setDefaultValue(defaultValue);
				HibernateUtils.customUpdate(_session,question,_user.getId());
			}else{
				question = new CAQuestions();
				event = (CAEventCollected)_session.get(CAEventCollected.class,Long.valueOf(String.valueOf(_parameters.get("eventId"))));
				if (!isHeader){
					values = (String[])(_parameters.get("answerType"));
					answerType =(CAAnswerType)_session.get(CAAnswerType.class,values[0]);
					question.setAnswerType(answerType);
					question.setFormat(format);
					question.setHeaderGroup(headerGroup);
					question.setHeaderPosition(groupPosition);
					question.setDefaultValue(defaultValue);
				}else{
					question.setHeaderPosition(0);
				}
				question.setText(text);
				question.setHeader(isHeader);
				question.setHidden(isHidden);
				question.setOptional(isOptional);
				question.setPosition(tablePosition);
				question.setEvent(event);
				question.setDescription(description);
				HibernateUtils.customSave(_session,question,_user.getId());
				if (isHeader){
					question.setHeaderGroup(question.getId());
					HibernateUtils.customUpdate(_session,question,_user.getId());
				}
			}
			this.appendParamToUrl("eventId", ""+question.getEvent().getId());			
						
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
