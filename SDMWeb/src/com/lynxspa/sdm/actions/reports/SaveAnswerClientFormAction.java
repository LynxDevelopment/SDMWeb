package com.lynxspa.sdm.actions.reports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.answers.CAEventHoldingAnswer;
import com.lynxspa.sdm.entities.events.answers.CAQuestions;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.dictionaries.ClassType;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.SPSecurityPortfolio;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.validation.utils.ValidationsDict;

public class SaveAnswerClientFormAction extends CoacEnhancedBasicAction {

	public static final ClassType[] TYPES={ClassType.BOOLEAN,ClassType.LONG,ClassType.DOUBLE,ClassType.TIMESTAMP,ClassType.SHORTSTRING,ClassType.MIDDLESTRING,ClassType.LONGSTRING,ClassType.VERYLONGSTRING,ClassType.CHAR};
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final Logger logger = Logger.getLogger(SaveAnswerClientFormAction.class);
	
	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		HashMap<String, CAQuestions> answers = null;
		HashMap<String,Object> parameterValues = null;
		Session session= null;
		CAQuestions question=null;
		String path=null;
		Object value = null;
		try{
			session = getCurrentSession();
			answers = getAnswers(session, (String)_parameters.get("eventId"));
			parameterValues = getParameterValues(_parameters);
			
			Iterator iteAnswers = answers.entrySet().iterator();
			while (iteAnswers.hasNext()) {
				Map.Entry<String,Object> e = (Map.Entry)iteAnswers.next();
				question = (CAQuestions)e.getValue();
				path = question.getAnswerType().getId()+question.getPosition();
				value = parameterValues.get(e.getKey());
				if (!question.isOptional() && value==null){
					_errors.add(new ValidationException("error.blank.field",path , ""));
				}else{
					if (value!=null){
						if (path.contentEquals("STRING") && !ValidationsDict.ISSTRING.validate(new Object[]{value})){
							_errors.add(new ValidationException("error.invalid_string", path, value.toString()));
						}else if (path.startsWith(ClassType.DOUBLE.getCode())&& !ValidationsDict.ISDOUBLE.validate(new Object[]{value})){
							_errors.add(new ValidationException("error.invalid_double", path, value.toString()));
						}else if (path.startsWith(ClassType.LONG.getCode()) && !ValidationsDict.ISLONG.validate(new Object[]{value})){
							_errors.add(new ValidationException("error.invalid_long", path, value.toString()));
						}
					}
				}
			}
			
		}catch(Exception e){
			logger.error("Error during validation routine. ", e);
			throw new FPMException(e);
		}
	}
	
	@Override
	public void performAction(HttpServletRequest _request, Session _session,
			LoggedUser _user, Map<String, Object> _parameters)
			throws FPMException {
		HashMap<String,Object> answerValues=null;
		CAEventHoldingAnswer eventHoldinganswer = null;
		CAQuestions question = null;
		SPSecurityPortfolio holding=null;
		Query query=null;
		
		try{
			question=(CAQuestions)_session.get(CAQuestions.class, Long.valueOf(String.valueOf(_parameters.get("questionId"))));
			holding=(SPSecurityPortfolio)_session.get(SPSecurityPortfolio.class, Long.valueOf(String.valueOf(_parameters.get("holdingId"))));
			
			query=_session.createQuery("from CAEventHoldingAnswer where auditor.deleted=:isDeleted and question.id =:questionId and holding.id =:holdingId");
			
			query.setBoolean("isDeleted", false);
			query.setLong("questionId",question.getId());
			query.setLong("holdingId",holding.getId());
			
			answerValues = getAnswerValues(_parameters);
			if ((eventHoldinganswer=(CAEventHoldingAnswer)query.uniqueResult()) == null) {
				eventHoldinganswer=new CAEventHoldingAnswer(_user.getId(), question, holding );
				eventHoldinganswer.setDynamicTable(answerValues);
				HibernateUtils.customSave(_session,eventHoldinganswer,_user.getId());
			}else{
				eventHoldinganswer.setDynamicTable(answerValues);
				HibernateUtils.customUpdate(_session,eventHoldinganswer,_user.getId());
			}
			
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
	
	private HashMap<String,Object> getParameterValues(Map<String, Object> _parameters) throws Exception{
		HashMap<String,Object> reply=null;
		reply = new HashMap<String,Object>(10);
		String type = null;
		String key	= null;
		String value = null;
		String [] values = null;
		
		for(int ic1=0;ic1<SaveAnswerClientFormAction.TYPES.length;ic1++){
			type = SaveAnswerClientFormAction.TYPES[ic1].getCode();
			for (int i=1;i<=10;i++){
				key = type+i;
				if (_parameters.get(key)!=null){
					value = String.valueOf(_parameters.get(key));
					reply.put(key, value);
				}
			}
		}
		
		return reply;
	}
	
	
	private HashMap<String,Object> getAnswerValues(Map<String, Object> _parameters) throws Exception{
		HashMap<String,Object> reply=null;
		reply = new HashMap<String,Object>(10);
		String type = null;
		String key	= null;
		String value = null;
		String [] values = null;
		
			
		for(int ic1=0;ic1<SaveAnswerClientFormAction.TYPES.length;ic1++){
			type = SaveAnswerClientFormAction.TYPES[ic1].getCode();
			for (int i=1;i<=10;i++){
				key = type+i;
				if (_parameters.get(key)!=null){
					value = String.valueOf(_parameters.get(key));
					if (type.equals(ClassType.SHORTSTRING.getCode()) || type.equals(ClassType.MIDDLESTRING.getCode()) || type.equals(ClassType.LONGSTRING.getCode()) || type.equals(ClassType.VERYLONGSTRING.getCode())){
						reply.put(key, value);
					}else if (type.equals(ClassType.DOUBLE.getCode())){
						if (!value.trim().equals(""))
							reply.put(key,Double.valueOf(value));
						else
							reply.put(key, null);
					}else if (type.equals(ClassType.TIMESTAMP.getCode())){
						reply.put(key,toDate(_parameters.get(key)));
					}else if (type.equals(ClassType.LONG.getCode())){
						if (!value.trim().equals(""))
							reply.put(key, Long.valueOf(value));
						else
							reply.put(key, null);
					}else if (type.equals(ClassType.CHAR.getCode())){
						if (_parameters.get(key) instanceof java.lang.String[]){
							values=(String[])(_parameters.get(key));
							reply.put(key,values.length>0?values[0]:"");
						}else{
							reply.put(key,value.charAt(0));
						}
					}else if (type.equals(ClassType.BOOLEAN.getCode())){
						values=(String[])(_parameters.get(key));
						if(values.length>0 && values[0].equals("S")){
							reply.put(key, true);
						}else if (values.length>0 && values[0].equals("N")){
							reply.put(key, false);
						} 
					}
				}else{
						reply.put(key, null);
				}
			}
		}
		
		return reply;
	}
	
	public Date toDate(Object obj){
		Date date = null;

		if(obj instanceof Date){
			date = (Date)obj;
		}

		else if(obj instanceof String){			
			try{
				DateFormat df = new SimpleDateFormat(SaveAnswerClientFormAction.DATE_FORMAT);
				date = df.parse((String)obj);
			}
			catch(Exception e){
				return null;
			}
		}
		return date;
	}
	
	public HashMap<String, CAQuestions> getAnswers (Session _session, String _eventId) throws Exception {
		HashMap<String, CAQuestions> reply = null;
		reply = new HashMap<String,CAQuestions>();
		CAQuestions question = null;
		Iterator<CAQuestions> questions=null;
		Query query=null;
		
		query=_session.createQuery("from CAQuestions where event.id=:eventId and hidden=:isHidden");
		query.setBoolean("isHidden", false);
		query.setLong("eventId", Long.valueOf(_eventId));
		questions = query.iterate();
		while(questions.hasNext()){
			question = questions.next();
			if (!question.isHeader())
				reply.put(question.getAnswerType().getId()+question.getPosition(), question);
		}
		
		return reply;
	}
}
