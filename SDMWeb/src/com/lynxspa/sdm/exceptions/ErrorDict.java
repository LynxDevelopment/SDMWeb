package com.lynxspa.sdm.exceptions;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;
import com.lynxspa.exception.FPMExceptionMessage;
import com.lynxspa.exception.FPMExceptionType;

public enum ErrorDict implements LogDictAdapter,FPMExceptionMessage {
	
	
	DOMAIN_EXPORT_ERROR("domain.action.export.fail","Domain data export fail."),
	DOMAIN_NORMAL_EXPORT_ERROR("domain.normal.action.export.fail","Domain normal data export fail."),
	DOMAIN_CLUSTER_EXPORT_ERROR("domain.cluster.action.export.fail","Domain cluster data export fail."),
	DOMAIN_VALUE_EXPORT_ERROR("domain.value.action.export.fail","Domain value data export fail."),
	DOMAIN_DOWNLOAD_ERROR("domain.action.download.fail","Domain download fail."),
	EVENTDETAIL_EXPORT_ERROR("event.detail.action.export.fail","Event type details data export fail."),
	EVENTDETAIL_DOWNLOAD_ERROR("event.detail.action.download.fail","Event type details download fail."),
	NORMALIZATION_EXPORT_ERROR("normalization.action.export.fail","Normalization config data export fail."),
	NORMALIZATION_DOWNLOAD_ERROR("normalization.action.download.fail","Normalization config download fail."),
	EVENT_EXPORT_ERROR("event.action.export.fail","Event config data export fail."),
	MASTERRECORD_EXPORT_ERROR("master.record.action.export.fail","Master record config data export fail."),
	MASTERRECORD_DOWNLOAD_ERROR("master.record.action.download.fail","Master record config download fail."),
	EVENTPROVIDER_EXPORT_ERROR("event.provider.action.export.fail","Event providers data export fail."),
	EVENTPROVIDER_DOWNLOAD_ERROR("event.provider.action.download.fail","Event providers download fail."),
	UNEXPECTED_ERROR("unexpected.error","Unexpected error"),
	HIBERNATE_ERROR("database.error","Database error"),
	INSERT_EVENT_ERROR("events.insert_event.error","Insert event error "),
	INSERTED_EVENT_NOT_SAVED_ERROR("events.insert_event.not_inserted","Event could not be saved. "),
	BLANK_SECURITY_ID_ERROR("events.insert_event.blank_security_id","Security ID is blank. "),
	INVALID_SECURITY_ID_ERROR("events.insert_event.invalid_security_id","\"{0}\" is not a valid security ID. "),
	BLANK_EVENT_TYPE_ERROR("events.insert_event.blank_event_type","Event type is blank. "),
	INVALID_EVENT_TYPE_CODE_ERROR("events.insert_event.invalid_eventType_code","\"{0}\" is not a valid event type code. "),
	BLANK_EXECUTION_DATE_ERROR("events.insert_event.blank_executionDate","Execution date is blank. "),
	BLANK_EXECUTION_DATE_KEY_ERROR("events.insert_event.blank_executionDate_key","Unexpected error. Could not determine the execution date text field. "),
	INVALID_EXECUTION_DATE_ERROR("events.insert_event.invalid_executionDate","{0} is not a valid execution date (not a date or cannot be converted into a date). "),
	EVENT_DETAIL_SAVE_ERROR("events.insert_event.event_detail_not_saved","Could not save Event Detail entity"),
	EVENT_NOT_COMPLETE_ERROR("events.insert_event.event_not_complete","Event not complete (insufficient details provided)"),
	INVALID_EVENT_DETAIL_FIELDS_ERROR("events.insert_event.invalid_detail_fields","Invalid fields"),
	NO_EVENTS_CHECKBOX_ARRAY_ERROR("events.matching.no_events_checkbox_array", "No events checkbox array found (HTML form field \"{0}\")"),
	NO_EVENTGROUP_FIELD_ERROR("events.matching.no_eventGroup_field", "No eventGroup field found (HTML form field \"{0}\")"),
	MATCH_EVENTS_SAVE_ERROR("events.matching.match_events_save_error", "Could not save matched events"),
	MATCH_EVENTS_FAILED_TO_MATCH_EVENT("events.matching.failed_to_match_event", "Failed to match event with ID {0}"),
	MATCH_EVENTS_UNEXPECTED_ERROR("events.matching.unexpected_match_events_error", "Unexpected match events error"),
	MATCH_EVENT_CANT_READ_FIELD_ERROR("events.matching.unexpected_match_events_error", "Error reading submitted form field \"{0}\". "),
	MATCH_EVENT_CANT_CREATE_GROUP_ERROR("events.matching.create_group_error", "Error creating event group. "),
	NO_MATCH_EVENT_NEW_GROUP_CHECKBOX_ERROR("events.matching.no_create_group_checkbox_array", "No new group flag checkbox array found (HTML form field \"{0}\")"),
	SECURITY_EXIST("security.exist","Isin \"{0}\" already exist.");
	
	private String key=null;
	private String defaultMessage=null;
	
	
	ErrorDict(String _key,String _defaultMessage){
		this.key=_key;
		this.defaultMessage=_defaultMessage;
	}
	
	
	public String getMessageKey() {
		return this.key;
	}
	public String getDefaultMessage() {
		return this.defaultMessage;
	}
	public FPMExceptionType getType() {
		return FPMExceptionType.ERROR;
	}
	public LogLevel getLevel() {
		return LogLevel.ERROR;
	}
}
