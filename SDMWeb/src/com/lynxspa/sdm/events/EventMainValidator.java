package com.lynxspa.sdm.events;

import org.hibernate.Session;
import com.lynxspa.sdm.dictionaries.CAType;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.entities.securities.SPSecurity;

public class EventMainValidator extends EventValidator{

	public EventMainValidator(Session session){
		super(session);
	}

	/**
	 * @return true if all 3 types passed as params are valid.
	 */
	public boolean isValidEvent(String eventTypeCode, Long securityId, Object executionDate){
		return isValidEventTypeCode(eventTypeCode) && isValidSecurityId(securityId) && isValidExecutionDate(executionDate); 
	}
	
	public boolean isValidEventTypeCode(String code){
		for(CAType caType: CAType.values()){
			if(caType.getCode().equals(code)){
				return true;
			}
		}
		return false;
	}

	public boolean isValidSecurityId(Long id){
		SPSecurity spSecurity = null;
		spSecurity = (SPSecurity)this.session.get(SPSecurity.class, id);
		if(null != spSecurity){
			return true;
		}
		return false;
	}

	public boolean isValidEventGroupId(Long id){
		CAEventGroup eventGroup = null;
		eventGroup = (CAEventGroup)this.session.get(CAEventGroup.class, id);
		if(null != eventGroup){
			return true;
		}
		return false;
	}

	/**
	 * Accepts any date 
	 */
	public boolean isValidExecutionDate(Object cand){
		return isValidTimestamp(cand);
	}
}
