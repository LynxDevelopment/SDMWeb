package com.lynxspa.sdm.events;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import org.hibernate.Query;
import org.hibernate.Session;
import com.lynxspa.sdm.entities.events.details.CAEventDetail;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtended;
import com.lynxspa.entities.VersionAuditor;
import com.lynxspa.hbt.utils.HibernateUtils;

/**
 * @author robert.kirkpatrick
 */
public class EventDetailBuilder {
	
	private static final int DEFAULT_VERSION = 0;
	
	
	private String user;
	private Date executionDate;
	private Date subscriptionDate;
	private Date expirationDate;
	private Date operationalDate;
	private Session session;
	private Map<String, Object> detailFieldsMap;
	private Map<String, Map<String, Object>> extensionsMap;
	private CAEventDetail caEventDetail;
	
	
	public EventDetailBuilder(Session session, String user){
		this.session = session;
		this.user = user;
		this.detailFieldsMap = new HashMap<String, Object>();
		this.extensionsMap = new HashMap<String, Map<String, Object>>();
		this.caEventDetail = new CAEventDetail();
		this.executionDate = null;
		this.subscriptionDate = null;
		this.expirationDate = null;
		this.operationalDate = null;
	}

	
	public void addField(Long fieldTypeId, Object value) throws Exception{
		
		try{
			Query query = this.session.createQuery("select etd.fieldPath, etd.extension from CAEventTypeDetail as etd where etd.id = " + fieldTypeId + "");
			query.setFetchSize(1);
			query.setReadOnly(true);
			Object[] array = (Object[])(query.list().get(0));
			String path = array[0].toString();
			Boolean isExtension = (Boolean)(array[1]);
			if(!isExtension){
				this.detailFieldsMap.put(path, value);
			}else{
				EventDetailFieldPath fieldPath = new EventDetailFieldPath(path); 
				if(!this.extensionsMap.containsKey(fieldPath.getExtensionId())){
					this.extensionsMap.put(fieldPath.getExtensionId(), new HashMap<String, Object>());
				}
				this.extensionsMap.get(fieldPath.getExtensionId()).put(fieldPath.getFieldName(), value);
			}
		}catch(Exception e){
			throw e;
		}
	}

	public void save() throws Exception{
		
		try{
			this.caEventDetail.setVersion(EventDetailBuilder.DEFAULT_VERSION);											// TODO implement version properly
			this.caEventDetail.setAuditor(new VersionAuditor(this.user));	
			this.caEventDetail.setExecutionDate(this.executionDate);
			this.caEventDetail.setSubscriptionDate(this.subscriptionDate);
			this.caEventDetail.setExpirationDate(this.expirationDate);
			this.caEventDetail.setOperationalDate(this.operationalDate);
			for(Entry<String, Object> e: this.detailFieldsMap.entrySet()){
				String key = e.getKey();
				Object value = e.getValue();
				try{
					EventDetailFieldPath path = new EventDetailFieldPath(key);
					String fieldName = path.getFieldName();
					this.caEventDetail.set(fieldName, value);
				}
				catch(Exception exception){
					throw exception;
				}
			}
			HibernateUtils.customSave(this.session, caEventDetail, this.user);
			for(Entry<String, Map<String, Object>> extensionEntry: this.extensionsMap.entrySet()){
				String extensionKey = extensionEntry.getKey();
				Map<String, Object> extendedFieldsMap = extensionEntry.getValue();
				try{
					CAEventDetailExtended extension = new CAEventDetailExtended(this.user, EventDetailFieldPath.getNameFromId(extensionKey), EventDetailFieldPath.getNumberFromId(extensionKey));
					for(Entry<String, Object> detailEntry: extendedFieldsMap.entrySet()){
						extension.set(detailEntry.getKey(), detailEntry.getValue());
					}
					extension.setMainDetail(this.caEventDetail);
					HibernateUtils.customSave(this.session, extension, this.user);
				}
				catch(Exception e){
					throw e;
				}
			}
		}catch(Exception e){
			throw e;
		}
	}
	
	public CAEventDetail getCaEventDetail() {
		return caEventDetail;
	}

	public void setCaEventDetail(CAEventDetail caEventDetail) {
		this.caEventDetail = caEventDetail;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getOperationalDate() {
		return operationalDate;
	}

	public void setOperationalDate(Date operationalDate) {
		this.operationalDate = operationalDate;
	}
}

class EventDetailFieldPath{
	protected static final String BODY_PATH_PREFIX = "BODY";
	private String extensionName = null;
	private String extensionId = null;
	private String fieldName = null;
	private int extensionNumber;
	
	protected EventDetailFieldPath(String path) throws Exception{
		StringTokenizer st = new StringTokenizer(path, ":");
		String extensionTypeFlag = st.nextToken();
		if(!EventDetailFieldPath.BODY_PATH_PREFIX.equals(extensionTypeFlag)){
			this.extensionName = extensionTypeFlag;
			this.extensionNumber = Integer.parseInt(st.nextToken());
			this.extensionId = this.extensionName + "|" + this.extensionNumber;
		}
		this.fieldName = st.nextToken(" ").replace(":", "");						// (gets the rest of the path)
	}

	protected String getExtensionName() {
		return extensionName;
	}

	protected void setExtensionName(String extensionName) {
		this.extensionName = extensionName;
	}

	protected int getExtensionNumber() {
		return extensionNumber;
	}

	protected void setExtensionNumber(int extensionNumber) {
		this.extensionNumber = extensionNumber;
	}

	protected String getFieldName(){
		return this.fieldName;
	}

	protected void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	protected String getExtensionId() {
		return extensionId;
	}

	protected void setExtensionId(String extensionId) {
		this.extensionId = extensionId;
	}

	protected static String getNameFromId(String extensionId) throws Exception{
		if(extensionId.indexOf('|') ==-1){
			throw new Exception("extension ID \"" + extensionId + "\"does not contain a pipe char");
		}
		return extensionId.substring(0, extensionId.indexOf('|'));
	}

	protected static int getNumberFromId(String extensionId) throws Exception{
		int n = -1;
		if(extensionId.indexOf('|') ==-1){
			throw new Exception("extension ID \"" + extensionId + "\"does not contain a pipe char");
		}
		try{
			n = Integer.parseInt(extensionId.substring(extensionId.indexOf('|')+1));
		}
		catch(NumberFormatException e){
			throw new Exception("extension ID \"" + extensionId + "\"'s number is incorrect or missing. ", e);
		}
		return n;
	}
}