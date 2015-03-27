package com.lynxspa.sdm.logs;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;

public enum WebLogInfoDict implements LogDictAdapter {

	//CONFIGURATION
	CONFIGURATION_CHANGED("info.configuration.change","Configuration {0} has changed from ''{1}'' to ''{2}''."),
	//	Event detail
	EVENTDETAIL_CONFIG_ADDED("info.event.detail.added","New event detail {0} for event {1} has added."),
	EVENTDETAIL_CONFIG_CHANGED("info.event.detail.config.changed","Event detail configuration for field {0} of event {1} has changed."),
	EVENTDETAIL_CONFIG_DELETED("info.event.detail.deleted","Event detail {0} of event {1} has deleted."),
	//	Normalization
	NORMALIZATION_CONFIG_CHANGED("info.normalization.config.changed","Normalization script configuration of {0} event has changed for {1} format and {2} type with {3} provider."),
	//	Master record
	MASTERRECORD_CONFIG_CHANGED("info.masterrecord.config.changed","Master record selector configuration of {0} event has changed."),
	//	Domains
	DOMAIN_INSERTED("info.domain.inserted","Domain {0} has been inserted with name {1}."),
	DOMAIN_UPDATED("info.domain.updated","Domain {0} has been updated with name {1}."),
	DOMAIN_DELETED("info.domain.deleted","Domain {0} has been removed and deleting in cascade {1} values, {2} normals, {3} clusters and {4} format relations."),
	DOMAIN_NORM_INSERTED("info.domain.norm.inserted","Norm {0} has been inserted into domain {1}."),
	DOMAIN_NORM_UPDATED("info.domain.norm.updated","Norm {0} from domain {1} has been updated."),
	DOMAIN_NORM_DELETED("info.domain.norm.deleted","Norm {0} has been deleted from domain {1} deleting in cascade {2} values."),
	DOMAIN_CLUSTER_INSERTED("info.domain.cluster.inserted","Cluster {0} has been inserted into domain {1}."),
	DOMAIN_CLUSTER_UPDATED("info.domain.cluster.updated","Cluster {0} from domain {1} has been updated."),
	DOMAIN_CLUSTER_DELETED("info.domain.cluster.deleted","Cluster {0} has been deleted from domain {1} deleting in cascade {2} values and {3} format relations."),
	DOMAIN_VALUES_UPDATED("info.domain.values.updated","Domain {0} has been updated their values for cluster {1} and normal {2} from ''{3}'' values to ''{4}''."),
	//	Messages Formats
	MESSAGEFORMAT_INSERTED("info.message.format.inserted","Message format {0} has been inserted with name {1}, path {2} and pattern {3}."),
	MESSAGEFORMAT_UPDATED("info.message.format.updated","Message format {0} has been updated with name {1}}, path {2} and pattern {3}."),
	MESSAGEFORMAT_DELETED("info.message.format.deleted","Message format {0} has been removed and deleting in cascade {1} message types, {2} message details and {3} cluster assignations."),
	MESSAGEFORMAT_ASSIGN_DOMAINCLUSTER("info.message.format.assign.domain.cluster","Has been assigned {0} cluster of {1} domain to format {2}."),
	MESSAGEFORMAT_REMOVE_DOMAINCLUSTER("info.message.format.remove.domain.cluster","{0} cluster of {1} domain has been removed from format {2}."),
	MESSAGEFORMAT_ADD_MESSAGETYPE("info.message.format.add.message.type","Has been added type {0} to format {1}."),
	MESSAGEFORMAT_REMOVE_MESSAGETYPE("info.message.format.remove.message.type","Has been removed type {0} from format {1}."),
	//	Message format fields
	MESSAGEFIELDCONFIG_INSERTED("info.message.field.config.inserted","Message field config {0} has been inserted into message type {1} of format {2}."),
	MESSAGEFIELDCONFIG_UPDATED("info.message.field.config.updated","Message field config {0} has been updated into message type {1} of format {2}."),
	MESSAGEFIELDCONFIG_DELETED("info.message.field.config.deleted","Message field config {0} has been removed from message type {1} of format {2}."),
	//SECURITIES
	SECURITY_CHANGED("info.security.changed","Security {0} has changed."),
	SECURITY_INSERTED("info.security.inserted","Security {0} has created."),
	VIRTUAL_SECURITY_INSERTED("info.virtual.security.inserted","Security {0} has created from virtual security {1} modifying {2} event messages."),
	VIRTUAL_SECURITY_REPLACED("info.virtual.security.replaced","Virtual security {0} has been replaced by {1} modifying {2} event messages."),
	//	Markets
	MARKET_INSERTED("info.market.inserted","Market {0} has created."),
	MARKET_CHANGED("info.market.changed","Market {0} has changed."),
	MARKET_DELETED("info.market.deleted","Market {0} has been deleted."),
	//	Planifications
	SECURITYPLANIFICATIONASSOCIATED("info.security.planification.associated","Security planification {0} has been associated to {1} securities."),
	SECURITYPLANIFICATIONDELETED("info.security.planification.deleted","Security planification {0} has been deleted and disassociated from {1} securities."),
	SECURITYPLANIFICATIONPROCESSDELETED("info.security.planification.process.deleted","Security planification process {0} has been deleted and disassociated from {1} planifications."),
	//MANAGERS
	MANAGER_INSERTED("info.manager.inserted","Manager {0} has created."),
	MANAGER_CHANGED("info.manager.changed","Manager {0} has changed."),
	MANAGER_DELETED("info.manager.deleted","Manager {0} has been deleted."),
	//	Manager groups
	MANAGERGROUP_INSERTED("info.manager.group.inserted","Manager group {0} has created."),
	MANAGERGROUP_CHANGED("info.manager.group.changed","Manager group {0} has changed."),
	MANAGERGROUP_DELETED("info.manager.group.deleted","Manager group {0} has been deleted.");

	
	private String messageKey=null;
	private String defaultMessage=null;
	
	
	WebLogInfoDict(String _key,String _defaultMessage){
		this.messageKey=_key;
		this.defaultMessage=_defaultMessage;
	}

	
	public String getMessageKey() {
		return this.messageKey;
	}
	public String getDefaultMessage() {
		return this.defaultMessage;
	}
	public LogLevel getLevel() {
		return LogLevel.INFO;
	}
}
