package com.lynxspa.sdm.events;

import java.util.Date;
import org.hibernate.Session;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTCOLLECTEDFlow;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.sdm.entities.events.details.CAEventDetail;
import com.lynxspa.sdm.entities.events.providers.CAEventProvider;
import com.lynxspa.sdm.entities.events.types.CAEventType;
import com.lynxspa.entities.VersionAuditor;
import com.lynxspa.entities.application.flow.Flow;
import com.lynxspa.entities.application.flow.State;
import com.lynxspa.entities.application.flow.StateId;
import com.lynxspa.entities.application.flow.operations.OperationStatus;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.hbt.utils.HibernateUtils;

/**
 * @author robert.kirkpatrick
 * Creates and saves a CAEventCollected object.
 */
public class EventInserter {
	
	private static final CAStatesEVENTCOLLECTEDFlow UNMATCHED_EVENT_FLOW_STATE=CAStatesEVENTCOLLECTEDFlow.UNMC;	
	private static final CAStatesEVENTCOLLECTEDFlow MANUAL_MATCH_EVENT_FLOW_STATE=CAStatesEVENTCOLLECTEDFlow.MNMC;
	private static final String FLOW = "EVENTCOLLECTED";
	
	private Session session;
	private Long eventId = 0L;
	private String user;
	private Date executionDate;
	private Date subscriptionDate;
	private Date expirationDate;
	private Date operationalDate;
	private CAEventProvider eventProvider;
	private CAEventType eventType;
	private SPSecurity security;
	private CAEventCollected event = null;
	private CAEventDetail eventDetail = null;
	private OperationStatus operationStatus;
	private CAEventGroup eventGroup;
	
	
	/**
	 * Constructor with a CAEventDetail object as a parameter.
	 */
	public EventInserter(Session session, String user, CAEventProvider eventProvider, String eventTypeId, long securityId, Date executionDate, CAEventDetail caEventDetail){
		this.session = session;
		this.user = user;
		this.eventProvider = eventProvider;
		this.eventType = (CAEventType)session.get(CAEventType.class, eventTypeId);
		this.security = (SPSecurity)session.get(SPSecurity.class, securityId);
		this.executionDate = executionDate;
		this.subscriptionDate = null;
		this.expirationDate = null;
		this.operationalDate = null;
		this.setEventDetail(caEventDetail);
		this.setOperationStatus(EventInserter.UNMATCHED_EVENT_FLOW_STATE);
		this.eventGroup = null;
	}

	/**
	 * Convenience constructor with an EventDetailBuilder object as a param.
	 */
	public EventInserter(Session session, String user, CAEventProvider eventProvider, String eventTypeCode, long securityId, Date executionDate, EventDetailBuilder eventDetailBuilder){
		this(session, user, eventProvider, eventTypeCode, securityId, executionDate, eventDetailBuilder.getCaEventDetail());
	}

	/*
	 * Note that save() method ignores following fields, which retain their default values:
	 *  CAEventCollected.version=0, 
	 *  CAEventCollected.providerUpdated=false, 
	 *  CAEventCollected.providerCancelled=false.
	 */
	public void save() throws Exception{
		this.event = null;
		try {
			this.event = new CAEventCollected(this.user);				
			this.event.setComplete(this.isComplete());
			this.event.setAuditor(new VersionAuditor(this.user));
			this.event.setEventProvider(this.eventProvider);
			this.event.setEventType(this.eventType);
			this.event.setSecurity(this.security);
			this.event.setExecutionDate(this.executionDate);
			this.event.setExpirationDate(this.expirationDate);
			this.event.setOperationalDate(this.operationalDate);
			this.event.setSubscriptionDate(this.subscriptionDate);
			this.event.setEventGroup(this.eventGroup);
			this.event.setOperationStatus(this.operationStatus);
			if(null != this.eventDetail){
				this.event.setEventDetail(this.eventDetail);
			}
			HibernateUtils.customSave(this.session, this.event, this.user);
			this.setEventId(this.event.getId());
		} catch (Exception e){
			throw e;
		}
	}

	public Session getSession(){
		return session;
	}

	public void setSession(Session session){
		this.session = session;
	}

	public Date getExecutionDate(){
		return executionDate;
	}

	public void setExecutionDate(Date executionDate){
		this.executionDate = executionDate;
	}

	public Date getSubscriptionDate(){
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate){
		this.subscriptionDate = subscriptionDate;
	}

	public Date getExpirationDate(){
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate){
		this.expirationDate = expirationDate;
	}

	public Date getOperationalDate(){
		return operationalDate;
	}

	public void setOperationalDate(Date operationalDate){
		this.operationalDate = operationalDate;
	}

	public CAEventType getEventType(){
		return eventType;
	}

	public void setEventType(CAEventType eventType){
		this.eventType = eventType;
	}

	public SPSecurity getSecurity(){
		return security;
	}

	public void setSecurity(SPSecurity security){
		this.security = security;
	}

	public CAEventCollected getEvent(){
		return event;
	}

	public void setEvent(CAEventCollected event){
		this.event = event;
	}

	public CAEventDetail getEventDetail(){
		return eventDetail;
	}

	public void setEventDetail(CAEventDetail eventDetail){
		this.eventDetail = eventDetail;
	}

	public OperationStatus getOperationStatus(){
		return operationStatus;
	}

	public void setOperationStatus(OperationStatus operationStatus){
		this.operationStatus = operationStatus;
	}

	public void setOperationStatus(CAStatesEVENTCOLLECTEDFlow state){
		Flow flow = (Flow)session.get(Flow.class, EventInserter.FLOW);
		String code = state.getId();
		StateId stateId = new StateId(flow, code);
		this.operationStatus = new OperationStatus((State)session.get(State.class, stateId));
	}

	public CAEventGroup getEventGroup() {
		return this.eventGroup;
	}

	public void setEventGroup(CAEventGroup eventGroup){
		this.eventGroup = eventGroup;
		this.setOperationStatus((null != eventGroup? MANUAL_MATCH_EVENT_FLOW_STATE: UNMATCHED_EVENT_FLOW_STATE));
	}

	public void setEventGroup(Long id) throws Exception{
		CAEventGroup eventGroup = (CAEventGroup) this.session.get(CAEventGroup.class, id);
		if(null == eventGroup){
			throw new Exception("Error setting event group. Could not get CAEventGroup entity with ID " + id + ". ");
		}
		this.setEventGroup(eventGroup);
	}

	public boolean isComplete(){
		return this.getExecutionDate() != null && this.getSecurity() != null && this.getEventType() != null;
	}

	public static String getFLOW_CODE(){
		return FLOW;
	}

	protected Long getEventId(){
		return eventId;
	}

	protected void setEventId(Long eventId){
		this.eventId = eventId;
	}

	protected static CAStatesEVENTCOLLECTEDFlow getFLOW(){
		return UNMATCHED_EVENT_FLOW_STATE;
	}
}
