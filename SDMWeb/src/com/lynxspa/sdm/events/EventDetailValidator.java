package com.lynxspa.sdm.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxspa.sdm.entities.events.configuration.CAEventConfig;
import com.lynxspa.sdm.entities.events.configuration.CAEventFieldConfig;
import com.lynxspa.dictionaries.*;
import com.lynxspa.exception.FPMException;

public class EventDetailValidator extends EventValidator{
	private final static String EVENT_TYPE_DETAIL_ENTITY = "CAEventTypeDetail";
	Session session;
	Map<Long, String> fieldTypeMap;

	@SuppressWarnings("unchecked")
	public EventDetailValidator(Session session) throws Exception{
		super(session);
		this.fieldTypeMap = new HashMap<Long, String>();
		Query query = session.createQuery("select etd.id, etd.fieldType from " + EventDetailValidator.EVENT_TYPE_DETAIL_ENTITY + " as etd");
		for(Iterator<Object[]> it = query.iterate(); it.hasNext();){
			Object[] row = it.next();
			this.fieldTypeMap.put((Long)row[0], (String)row[1]);
		}
	}

	public boolean isValidField(Long id, Object cand) throws Exception{
		String fieldType = fieldTypeMap.get(id);
		for(ClassType classType : ClassType.values()){
			if(classType.getCode().equals(fieldType)){
				switch(classType){
					case BOOLEAN:   return isValidBoolean(cand);
					case LONG:      return isValidLong(cand);
					case DOUBLE:    return isValidDouble(cand);
					case TIMESTAMP: return isValidTimestamp(cand);
					case CLOB:		return isValidClob(cand);
					default:        return Hibernate.STRING == classType.getType() && isValidString(cand, classType.getMaxLength());
				}
			}
		}
		throw new FPMException(new Exception("The validator does not recognise the event detail field format type \"" + fieldType + "\" found in " + EventDetailValidator.EVENT_TYPE_DETAIL_ENTITY + ", id " + id + ". ")); 
	}

	public Object convertFieldValue(Long id, Object cand) throws Exception{
		String fieldType = fieldTypeMap.get(id);
		for(ClassType classType : ClassType.values()){
			if(classType.getCode().equals(fieldType)){
				switch(classType){
					case BOOLEAN:   return toBoolean(cand);
					case LONG:      return toLong(cand);
					case DOUBLE:    return toDouble(cand);
					case TIMESTAMP: return toDate(cand);
					case CLOB:		return toClob(cand);		
					default:		return (Hibernate.STRING == classType.getType())? toString(cand, classType.getMaxLength()) : null;
				}
			}
		}
		throw new FPMException(new Exception("The validator could not convert the event detail field format type \"" + fieldType + "\" found in " + EventDetailValidator.EVENT_TYPE_DETAIL_ENTITY + ", id " + id + ". ")); 
	}
	
	public boolean isRequiredField(Session _session, CAEventConfig _eventConfig, long _eventTypeDetailId) throws Exception{
		boolean reply=false;
		Query query = null;
		CAEventFieldConfig eventFieldConfig = null;
		if (_eventConfig == null)
			System.out.println("_eventConfig is null");
		
		query=_session.createQuery("from CAEventFieldConfig where eventConfig=:eventConfig and eventTypeDetail.id=:eventTypeDetailId");
		query.setParameter("eventConfig", _eventConfig);
		query.setParameter("eventTypeDetailId", _eventTypeDetailId);
		
		eventFieldConfig = (CAEventFieldConfig) query.uniqueResult();
		
		if (eventFieldConfig == null){
			System.out.println("eventFieldConfig is null for "+_eventConfig.getId() + " and _eventTypeDetailId "+_eventTypeDetailId);
		}
		
		reply = eventFieldConfig.isRequired();
		
		return reply;
	}
}
