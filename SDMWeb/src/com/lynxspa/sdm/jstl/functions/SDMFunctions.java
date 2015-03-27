package com.lynxspa.sdm.jstl.functions;

import java.io.BufferedReader;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.xweb.i18n.I18nSystem;
import com.lynxit.xweb.initialization.initializers.DataSourcesManager;
import com.lynxit.xweb.initialization.initializers.DataSourcesManager.LocalInstance;
import com.lynxspa.sdm.dictionaries.CAOperation;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.entities.events.adapters.CAEventCollectedAdapter;
import com.lynxspa.sdm.entities.events.answers.CAEventHoldingAnswer;
import com.lynxspa.sdm.entities.events.answers.CAQuestions;
import com.lynxspa.sdm.entities.events.configuration.CAEventFieldConfig;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtensionId;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.messages.CAEventMessageHistoric;
import com.lynxspa.sdm.entities.events.messages.adapters.CAEventMessageAdapter;
import com.lynxspa.sdm.entities.events.messages.adapters.CAEventMessageFieldAdapter;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFieldConfig;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageType;
import com.lynxspa.sdm.entities.events.providers.CAExternalEventProvider;
import com.lynxspa.sdm.entities.events.types.CAEventType;
import com.lynxspa.sdm.entities.events.types.CAEventTypeDetail;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.utils.EventDetailUtils;
import com.lynxspa.dictionaries.ClassType;
import com.lynxspa.entities.UpdatableAdapter;
import com.lynxspa.entities.application.Application;
import com.lynxspa.entities.securities.SPVirtualSecurity;
import com.lynxspa.entities.securities.details.SPSecurityDetail;
import com.lynxspa.entities.securities.financialassets.SPSecurityFinancialAssetsDetails;
import com.lynxspa.entities.securities.providers.SPProviderAccount;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.jstl.beans.EventFieldBean;
import com.lynxspa.sdm.jstl.beans.MessageFieldBean;
import com.lynxspa.sdm.jstl.views.TabView;
import com.lynxspa.sdm.jstl.views.TableCellView;
import com.lynxspa.sdm.jstl.views.TableView;
import com.lynxspa.sdm.managers.SDMConfigManager;

   
public class SDMFunctions {

	@SuppressWarnings("unchecked")	
    public static final Application getApplication(final String _dataSource) throws Exception{
    	
		LocalInstance localInstance=null;
		Session session=null;

		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);

    	return SDMConfigManager.getInstance().getApplication(session);
    }

	@SuppressWarnings("unchecked")	
    public static final Object getConfigurationValue(final String _dataSource,final String _configValue) throws Exception{
    	
		LocalInstance localInstance=null;
		Session session=null;

		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);

    	return SDMConfigManager.getInstance().getConfiguration(session, CAConfiguration.valueOf(_configValue));
    }
	
	public static String getLocalizedCountryName (final String _countryISO,final String _outputLanguageISO){
		
		String reply="";
		Locale locale=null;
		
		if((_countryISO!=null)&&(_outputLanguageISO!=null)){
			locale=new Locale("",_countryISO);
			reply=locale.getDisplayCountry(new Locale(_outputLanguageISO));
		}
		
		return reply;
	}
	
	public static String getLocalizedLanguageName (final String _languageISO,final String _outputLanguageISO){
		
		String reply="";
		Locale locale=null;
		
		if((_languageISO!=null)&&(_outputLanguageISO!=null)){
			locale=new Locale(_languageISO,"");
			reply=locale.getDisplayLanguage(new Locale(_outputLanguageISO));
		}
		
		return reply;
	}
    
    public static final Object[] getEnumValues(final String _className){

    	Object[] reply=null;

        try {
              reply=(Class.forName(_className).getEnumConstants());
        } catch (ClassNotFoundException e){
              e.printStackTrace();
        }

        return reply;
    }

	@SuppressWarnings("unchecked")	
    public static final List getEventRelatedMessages(final String _dataSource,final Long _eventId) throws Exception{
    	
		List reply=null;
		Session session=null;
		LocalInstance localInstance=null;
		Query query=null; 
		
		final class UpdateDateComparer implements Comparator<UpdatableAdapter>{
			public int compare(UpdatableAdapter o1, UpdatableAdapter o2) {
				return o1.getAuditor().getUpdateDate().compareTo(o2.getAuditor().getUpdateDate());
			}
		}
		
		reply=new ArrayList<UpdatableAdapter>();
		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);
		query=session.createQuery("select message from CAEventMessage as message where message.normalizedEvent=:eventId");
		query.setLong("eventId", _eventId);
		reply.addAll(query.list());
		query=session.createQuery("select message from CAEventMessageHistoric as message where message.normalizedEvent=:eventId");
		query.setLong("eventId", _eventId);
		reply.addAll(query.list());
		Collections.sort(reply,new UpdateDateComparer());
		
		return reply;
    }
	
	/**
	 * Función de ordenación de descripciones por orden alfabético para el combo EventType 
	 * @param _dataSource
	 * @return java.util.List<SortSelectBean>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")	
    public static final List getOrderSelect(final String _dataSource) throws Exception{
    	

		List<SortSelectBean> reply=null;
		Session session=null;
		LocalInstance localInstance=null;
		Query query=null; 
		ResourceBundle bundle = I18nSystem.getInstance().getBundle("sdm");
		String idSelect=null;
		String description=null;
		
		reply=new ArrayList<SortSelectBean>();
		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);
		query=session.createQuery("select id from CAEventType");
		
		for(Object id: query.list()){
			idSelect = (String)id;
			description=bundle.getString("event.type."+(String)id);
			SortSelectBean ssb= new SortSelectBean(idSelect,description);
			reply.add(ssb); 
		}
		
		Collections.sort(reply);
		
		return reply;
    }
	
	
	/**
	 * Función de ordenación de descripciones por orden alfabético para el combo AssetType 
	 * @param _dataSource
	 * @return java.util.List<SortSelectBean>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")	
    public static final List getOrderSelectAssetType(final String _dataSource) throws Exception{
    	

		List<SortSelectBean> reply=null;
		Session session=null;
		LocalInstance localInstance=null;
		Query query=null; 
		ResourceBundle bundle = I18nSystem.getInstance().getBundle("sdm");
		String idSelect=null;
		String description=null;
		
		reply=new ArrayList<SortSelectBean>();
		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);
		query=session.createQuery("select id from AssetType");
		
		for(Object id: query.list()){
			idSelect = (String)id;
			description=bundle.getString("asset.type."+(String)id);
			SortSelectBean ssb= new SortSelectBean(idSelect,description);
			reply.add(ssb); 
		}
		
		Collections.sort(reply);
		
		return reply;
    }


	@SuppressWarnings("unchecked")
	public static final List<Map<String,Object>> getEventFieldMessageConfig (final String _dataSource,final String _eventTypeId,final String _formatId,final String _messageTypeId,final String _providerId) throws Exception{
		
		List<Map<String,Object>> reply=null;
		Map<String,Object> replyMap=null;
		LocalInstance localInstance=null;
		Session session=null;
		Query eventTypeDetailQuery=null;
		Query eventFieldMessageConfigQuery=null;
		List<CAEventTypeDetail> details=null;

		reply=new ArrayList<Map<String,Object>>();
		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);
		eventTypeDetailQuery=session.createQuery("from CAEventTypeDetail as detail where detail.eventType.id=:eventTypeId and auditor.deleted=:isDeleted and editableNormalization=:isEditableNormalization order by detail.fieldPath");
		eventTypeDetailQuery.setParameter("eventTypeId",_eventTypeId);
		eventTypeDetailQuery.setParameter("isDeleted",false);
		eventTypeDetailQuery.setParameter("isEditableNormalization",true);
		details=eventTypeDetailQuery.list();
		for(CAEventTypeDetail detail:details){
			replyMap=new HashMap<String,Object>();
			replyMap.put("eventTypeDetail",detail);
			if((_providerId!=null)&&(!"".equals(_providerId))){
				eventFieldMessageConfigQuery=session.createQuery("select messageFieldConfigSel.normalizationScript from CAEventFieldMessageConfig as messageFieldConfigSel join messageFieldConfigSel.eventFieldConfig as eventFieldConfig with eventFieldConfig.eventTypeDetail=:eventTypeDetail where messageFieldConfigSel.messageType.id.format.id=:formatId and messageFieldConfigSel.messageType.id.code=:messageTypeId and messageFieldConfigSel.provider.id=:providerId");
				eventFieldMessageConfigQuery.setParameter("providerId", _providerId);
			}else{
				eventFieldMessageConfigQuery=session.createQuery("select messageFieldConfigSel.normalizationScript from CAEventFieldMessageConfig as messageFieldConfigSel join messageFieldConfigSel.eventFieldConfig as eventFieldConfig with eventFieldConfig.eventTypeDetail=:eventTypeDetail where messageFieldConfigSel.messageType.id.format.id=:formatId and messageFieldConfigSel.messageType.id.code=:messageTypeId and messageFieldConfigSel.provider is null");
			}
			eventFieldMessageConfigQuery.setParameter("eventTypeDetail", detail);
			eventFieldMessageConfigQuery.setParameter("formatId", _formatId);
			eventFieldMessageConfigQuery.setParameter("messageTypeId", _messageTypeId);
			replyMap.put("normalizationScript",eventFieldMessageConfigQuery.uniqueResult());
			reply.add(replyMap);
		}
				
		return reply;
	}
	
	
	@SuppressWarnings("unchecked")
	public static final List getCompleteAnalizerListWithRequired (final String _dataSource,final String _eventTypeId) throws Exception{
		
		ArrayList externalList=null;
		LocalInstance localInstance=null;
		Session session=null;
		Query eventTypeDetailQuery=null;
		Query eventFieldConfigQuery=null;
		List<CAEventTypeDetail> details=null;
		CAEventFieldConfig eventConfig = null;
		
		externalList=new ArrayList<Map<String,Object>>();
		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);

		eventTypeDetailQuery=session.createQuery("from CAEventTypeDetail as detail where auditor.deleted=:isDeleted and detail.eventType.id=:eventTypeId and editableNormalization=:isEditableNormalization");
		eventTypeDetailQuery.setParameter("eventTypeId",_eventTypeId);
		eventTypeDetailQuery.setParameter("isDeleted",false);
		eventTypeDetailQuery.setParameter("isEditableNormalization",true);
		details=eventTypeDetailQuery.list();
		for(CAEventTypeDetail detail:details){
			ArrayList internalList = new ArrayList();
			eventFieldConfigQuery=session.createQuery("from CAEventFieldConfig where eventTypeDetail.id=:externalId");
			eventFieldConfigQuery.setParameter("externalId", detail.getId());
			eventConfig = (CAEventFieldConfig) eventFieldConfigQuery.uniqueResult();
			internalList.add(detail.getId());
			internalList.add(detail.getFieldPath());
			internalList.add(detail.getName());
			internalList.add(detail.getDescription());
			if(eventConfig != null){
				internalList.add(eventConfig.isRequired());
			}else{
				internalList.add("0");
			}
			externalList.add(internalList);
		}
			
		return externalList;
	}	
	
	//EVENT MESSAGE DETAILS
	public static final String getMessageField(final CAEventMessageAdapter _message,final String _path){
		return _message.getField(_path);
	}
	
	@SuppressWarnings("unchecked")
	public static final List<MessageFieldBean> getMessageDetails (final String _dataSource,final CAEventMessageAdapter _message) throws Exception{
		
		List<MessageFieldBean> reply=null;
		
		reply=new ArrayList<MessageFieldBean>();
		if(_message!=null){
			//Recovering resurces
			final LocalInstance localInstance=DataSourcesManager.getLocalInstance();
			final Session session=localInstance.getHibernateSession(_dataSource);
			//Recovering message fields
			final List<MessageFieldBean> messageFields=new ArrayList<MessageFieldBean>();
			Query query=null;
			if(_message instanceof CAEventMessage){
				query=HibernateUtils.createQuery(session,"select field from CAEventMessageField as field where field.message.id=:messageId order by id");
			}else if(_message instanceof CAEventMessageHistoric){
				query=HibernateUtils.createQuery(session,"select field from CAEventMessageFieldHistoric as field where field.message.id=:messageId order by id");
			}else throw new Exception("Message must be an instance of ["+CAEventMessage.class.getName()+"] or ["+CAEventMessageHistoric.class.getName()+"] and received ["+String.valueOf(_message)+"]");
			query.setParameter("messageId",_message.getId());
			query.setReadOnly(true);
			final List<? extends CAEventMessageFieldAdapter> eventMessageFields=query.list();
			if(eventMessageFields!=null){
				for(CAEventMessageFieldAdapter messageField:eventMessageFields)
					messageFields.add(new MessageFieldBean(messageField));
			}
			//Adding Configurations
			if(!messageFields.isEmpty()){
				final SDMConfigManager configManager=SDMConfigManager.getInstance();
				final Map<String,CAMessageFieldConfig> configurations=configManager.getMessageFieldConfigs(session,_message);
				for(MessageFieldBean field:messageFields){
					final CAMessageFieldConfig config=configurations.get(configManager.getCleanedMessageFieldPath(field.getPath()));
					field.completeWithConfigurationInfo(config);
					reply.add(field);
				}
			}
		}
		
		return reply;
	}
	
	public static final Boolean hasUnknownDetails(final List<MessageFieldBean> _messageFields) throws Exception{
		
		for(MessageFieldBean messageField:_messageFields){
			if(!messageField.isConfigured())
				return true; 
		}
		return false;
	}

	public static final List<TableView> getFormatMessageFields (final String _dataSource,final CAMessageType _messageType,final List<MessageFieldBean> _messageFields) throws Exception{
		
		List<TableView> reply=null;
		boolean isTableHeader=false;
		int currentTableFieldsColumn=0;
		String tableDataFormat="";
		TableView currentTableView=null;
		int tableDataColumns=0;
		int currentTableDataColumn=1;

		//Preparing response
		reply=new ArrayList<TableView>(2);
		final TableView tableFieldsView=new TableView();
		reply.add(tableFieldsView);
		//Recovering resurces
		final LocalInstance localInstance=DataSourcesManager.getLocalInstance();
		final Session session=localInstance.getHibernateSession(_dataSource);
		final SDMConfigManager configManager=SDMConfigManager.getInstance();
		final Map<String,CAMessageFieldConfig> configurations=configManager.getMessageFieldConfigs(session,_messageType);
		//Mounting tables
		for(int ic1=0;ic1<_messageFields.size();ic1++){
			final MessageFieldBean messageField=_messageFields.get(ic1);
			if((messageField.isConfigured())&&(!messageField.isHidden())){
				if("TABLEHDR".equals(messageField.getType())){
					//Fill table data headers
					isTableHeader=true;
					if(!tableDataFormat.equals(messageField.getFormat())){
						//Is another table data
						currentTableView=new TableView();
						reply.add(currentTableView);
						tableDataColumns=0;
						tableDataFormat=messageField.getFormat();
					}
					final String messageFieldPathKey=configManager.getCleanedMessageFieldPath(messageField.getPath())+":"+messageField.getValue();
					currentTableView.addCell(new TableCellView<MessageFieldBean>(true,new MessageFieldBean(messageField,configurations.get(messageFieldPathKey).getFieldName()),0,"center"));
					tableDataColumns++;
				}else if("TABLE".equals(messageField.getType())){
					//Fill current table data
					if((isTableHeader)||(currentTableDataColumn>=tableDataColumns)){
						currentTableView.addRow();
						currentTableDataColumn=0;
						currentTableView.setName(messageField.getName());
					}
					currentTableView.addCell(new TableCellView<MessageFieldBean>(false,messageField,0,"center"));
					currentTableDataColumn++;
					isTableHeader=false;
				}else{
					//Fill detail fields
					if(messageField.getValue().length()<17){
						tableFieldsView.addCell(new TableCellView<MessageFieldBean>(true,messageField));
						tableFieldsView.addCell(new TableCellView<MessageFieldBean>(false,messageField));
						currentTableFieldsColumn++;
						if(currentTableFieldsColumn==2){
							tableFieldsView.addRow();
							currentTableFieldsColumn=0;
						}
					}else if(messageField.getValue().length()<128){
						if(currentTableFieldsColumn==1){
							tableFieldsView.getLastRow().get(1).setColspan(3);
							tableFieldsView.addRow();
						}
						tableFieldsView.addCell(new TableCellView<MessageFieldBean>(true,messageField));
						tableFieldsView.addCell(new TableCellView<MessageFieldBean>(false,messageField,3));
						tableFieldsView.addRow();
						currentTableFieldsColumn=0;
					}else{
						if(currentTableFieldsColumn==1){
							tableFieldsView.getLastRow().get(1).setColspan(3);
							tableFieldsView.addRow();
						}
						tableFieldsView.addCell(new TableCellView<MessageFieldBean>(true,messageField,4));
						tableFieldsView.addRow();
						tableFieldsView.addCell(new TableCellView<MessageFieldBean>(false,messageField,4));
						tableFieldsView.addRow();
						currentTableFieldsColumn=0;
					}
				}
			}
		}
		//Closing open tables
		if(currentTableFieldsColumn==1)
			tableFieldsView.getLastRow().get(1).setColspan(3);
		
		return reply;
	}

	//EVENT COLLECTED DETAILS 
	public static final List<TabView> getFormatedEventFields (final String _dataSource,final PageContext _pageContext,final CAEventCollectedAdapter _event) throws Exception{
		
		class TableContent{
			public TableView tableView=null;
			public boolean asForm=true;
			public List<CAEventTypeDetail> tabDetails=null;
			public TableContent(final TableView _tableView,final List<CAEventTypeDetail> _tabDetails,final boolean _asForm){
				this.tableView=_tableView;
				this.tabDetails=_tabDetails;
				this.asForm=_asForm;
			}
		}
		class TabContent{
			public TabView tabView=null;
			public List<TableContent> tabDetails=null;
			public TabContent(final TabView _tabView,final List<TableContent> _tabDetails){
				this.tabView=_tabView;
				this.tabDetails=_tabDetails;
			}
		}
		
		List<TabView> reply=null;
		Map<String,TabContent> tabs=null;
		String currentDisplayGroup=null;
		List<CAEventTypeDetail> currentDetails=null;
		
		//Preparing response
		reply=new ArrayList<TabView>(3);
		//Recovering resurces
		final LocalInstance localInstance=DataSourcesManager.getLocalInstance();
		final Session session=localInstance.getHibernateSession(_dataSource);
		final SDMConfigManager configManager=SDMConfigManager.getInstance();
		final List<CAEventTypeDetail> configurations=configManager.getEventTypeDetails(session,_event);
		//Mounting tabs
		tabs=new HashMap<String,TabContent>();
		for(CAEventTypeDetail eventTypeDetail:configurations){
			if(tabs.size()==0){
				final TabView tab=new TabView("BODY","event.detail.tab.general","event.detail.tab.general.description");
				final TabContent tabContent=new TabContent(tab,new ArrayList<TableContent>(10));
				tabs.put("BODY",tabContent);
				reply.add(tab);
			}
			if(eventTypeDetail.isExtension()){
				final String extensionCode=EventDetailUtils.getExtensionCode(eventTypeDetail);
				final TabView tab=new TabView(extensionCode,eventTypeDetail.getName(),eventTypeDetail.getDescription());
				final TabContent tabContent=new TabContent(tab,new ArrayList<TableContent>(10));
				tabs.put(extensionCode,tabContent);
				reply.add(tab);
			}else{
				if(!eventTypeDetail.isHidden()){
					final String extensionCode=EventDetailUtils.getExtensionCode(eventTypeDetail);
					final TabContent currentTab=tabs.get(extensionCode);
					final ClassType fieldType=ClassType.valueOf(eventTypeDetail.getFieldType());
					//Mount display groups
					if((currentDisplayGroup==null)||(!currentDisplayGroup.equals(eventTypeDetail.getDisplayGroup()))){
						//Change current display group
						currentDisplayGroup=eventTypeDetail.getDisplayGroup();
						final TableView table=new TableView(eventTypeDetail.getDisplayGroup());
						currentDetails=new ArrayList<CAEventTypeDetail>(10);
						currentTab.tabView.addTable(table);
						currentTab.tabDetails.add(new TableContent(table,currentDetails,!eventTypeDetail.isDisplayInTable()));
					}
					if(ClassType.CLOB==fieldType){
						//It's narrative new table with only one row and one column
						final TableView table=new TableView(eventTypeDetail.getName());
						List<CAEventTypeDetail> details=new ArrayList<CAEventTypeDetail>(1);
						details.add(eventTypeDetail);
						currentTab.tabView.addTable(table);
						currentTab.tabDetails.add(new TableContent(table,details,true));
					}else{
						currentDetails.add(eventTypeDetail);
					}
				}
			}
		}
		//For each tab mount tables
		for(TabContent content:tabs.values()){
			if("BODY".equals(content.tabView.getId())){
				for(TableContent tableContent:content.tabDetails){
					formatTableAsForm(_pageContext,tableContent.tableView,tableContent.tabDetails,_event);
				}
			}else{
				for(TableContent tableContent:content.tabDetails){
					if(tableContent.asForm){
						formatTableAsForm(_pageContext,tableContent.tableView,tableContent.tabDetails,_event);
					}else{
						formatTableAsList(_pageContext,content.tabView.getId(),tableContent.tableView,tableContent.tabDetails,_event);
					}
				}
			}
		}
		
		return reply;
	}
	
	private static void formatTableAsForm(final PageContext _pageContext,final TableView _table,final List<CAEventTypeDetail> _details,final CAEventCollectedAdapter _event) throws Exception{
		
		int currentTableFieldsColumn=0;
		
		for(CAEventTypeDetail eventTypeDetail:_details){
			final ClassType fieldType=ClassType.valueOf(eventTypeDetail.getFieldType());
			final EventFieldBean eventField=new EventFieldBean(_pageContext,eventTypeDetail,_event);
			if(ClassType.CLOB==fieldType){
				_table.addCell(new TableCellView<EventFieldBean>(false,eventField));
			}else if(ClassType.VERYLONGSTRING==fieldType){
				//It's so long, entire line
				if(currentTableFieldsColumn==1){
					_table.getLastRow().get(1).setColspan(3);
					currentTableFieldsColumn=0;
				}
				_table.addRow();
				_table.addCell(new TableCellView<EventFieldBean>(true,eventField));
				_table.addRow();
				_table.addCell(new TableCellView<EventFieldBean>(false,eventField,4));
				_table.addRow();
			}else if(ClassType.LONGSTRING==fieldType){
				//It's so long, double size
				if(currentTableFieldsColumn==1){
					_table.getLastRow().get(1).setColspan(3);
					currentTableFieldsColumn=0;
				}
				_table.addRow();
				_table.addCell(new TableCellView<EventFieldBean>(true,eventField));
				_table.addCell(new TableCellView<EventFieldBean>(false,eventField,3));
				_table.addRow();
			}else{
				//Normal
				if(currentTableFieldsColumn==2){
					_table.addRow();
					currentTableFieldsColumn=0;
				}
				_table.addCell(new TableCellView<EventFieldBean>(true,eventField));
				_table.addCell(new TableCellView<EventFieldBean>(false,eventField));
				currentTableFieldsColumn++;
			}
		}
		if(currentTableFieldsColumn==1){
			_table.getLastRow().get(1).setColspan(3);
			currentTableFieldsColumn=0;
		}
	}
	
	private static void formatTableAsList(final PageContext _pageContext,final String _tabId,final TableView _table,final List<CAEventTypeDetail> _details,final CAEventCollectedAdapter _event) throws Exception{
		
		class ListComparer implements Comparator<CAEventTypeDetail>{
			public int compare(CAEventTypeDetail o1,CAEventTypeDetail o2){
				return Integer.valueOf(o1.getDisplayColumn()).compareTo(Integer.valueOf(o2.getDisplayColumn()));
			}
		}
		
		//Prepare details
		Collections.sort(_details,new ListComparer());
		//Recover extension from this tab
		SortedSet<String> extensions=new TreeSet<String>(_event.getEventDetail().getExtensions().keySet());
		//Mount headers
		for(CAEventTypeDetail eventTypeDetail:_details){
			final EventFieldBean eventField=new EventFieldBean(_pageContext,eventTypeDetail,_event);
			_table.addCell(new TableCellView<EventFieldBean>(true,eventField));
		}
		//Mount rows
		for(String extensionIdCode:extensions){
			final CAEventDetailExtensionId extensionId=new CAEventDetailExtensionId(extensionIdCode);
			if(extensionId.getExtensionName().equals(_tabId)){
				_table.addRow();
				for(CAEventTypeDetail eventTypeDetail:_details){
					final EventFieldBean eventField=new EventFieldBean(_pageContext,eventTypeDetail,_event,extensionId.getExtensionNumber());
					_table.addCell(new TableCellView<EventFieldBean>(false,eventField));
				}
			}
		}
	}
		
	public static final String getEventTypeDetailValue (final EventFieldBean _eventField) throws Exception{
		
		String reply=null;
		
		if(_eventField.getValue()!=null){
			reply=String.valueOf(_eventField.getValue());
			final String eventTypeFormat=_eventField.getFormat();
			if((eventTypeFormat!=null)&&(!"".equals(eventTypeFormat))){
				if(eventTypeFormat.startsWith("LINK:")){
					reply=MessageFormat.format("(href:{0}={1})",eventTypeFormat.substring(5),String.valueOf(_eventField.getValue()));
				}else if(eventTypeFormat.startsWith("I18N:")){
					reply=MessageFormat.format(eventTypeFormat.substring(5),_eventField.getValue());
				}else{
					if(_eventField.getValue() instanceof Date){
						reply=(new SimpleDateFormat(eventTypeFormat)).format(_eventField.getValue());
					}else if(_eventField.getValue() instanceof Number){
						reply=(new DecimalFormat(eventTypeFormat)).format(_eventField.getValue());
					}
				}
			}
		}
		
		return reply;
	}
	
	public static final String getEventTypeDetailValue (final EventFieldBean _eventField, final String locale) throws Exception{
		
		String reply=null;
		
		if(_eventField.getValue()!=null){
			reply=String.valueOf(_eventField.getValue());
			final String eventTypeFormat=_eventField.getFormat();
			if((eventTypeFormat!=null)&&(!"".equals(eventTypeFormat))){
				if(eventTypeFormat.startsWith("LINK:")){
					reply=MessageFormat.format("(href:{0}={1})",eventTypeFormat.substring(5),String.valueOf(_eventField.getValue()));
				}else if(eventTypeFormat.startsWith("I18N:")){
					reply=MessageFormat.format(eventTypeFormat.substring(5),_eventField.getValue());
				}else{
					if(_eventField.getValue() instanceof Date){
						reply=(new SimpleDateFormat(eventTypeFormat)).format(_eventField.getValue());
					}else if(_eventField.getValue() instanceof Number){
						NumberFormat numberFormat = NumberFormat.getInstance(new Locale(locale));
						DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
						decimalFormat.applyPattern(eventTypeFormat);
						reply=decimalFormat.format(_eventField.getValue());
					}
				}
			}
		}
		
		return reply;
	}



	public static final CAExternalEventProvider getExternalProvider(final String _dataSource,final String _format,final String _idAtMessage) throws Exception{
		
		CAExternalEventProvider reply=null;
		LocalInstance localInstance=null;
		Session session=null;
		Query query=null;
		
		try{
			localInstance=DataSourcesManager.getLocalInstance();
			session=localInstance.getHibernateSession(_dataSource);
			query=session.createQuery("select fp.eventProvider from CAFormatProvider as fp where fp.messageFormat.id=:formatId and fp.idAtMessage=:idAtMessage");
			query.setParameter("formatId",_format);
			query.setParameter("idAtMessage",_idAtMessage);
			reply=(CAExternalEventProvider)query.uniqueResult();
		}catch (Exception e) {
			e.printStackTrace();
			reply=null;
		}
		
		return reply;
	}

	public static final String getNotificationTypeName(final String _notificationId) throws Exception{
		
		String reply=null;
		
		try{
			reply=CAOperation.valueOf(_notificationId).getName();
		}catch (Exception e) {
			reply=_notificationId;
		}
		
		return reply;
	}

	public static final String getEventTypeName(final String _dataSource,final String _eventId) throws Exception{
		
		String reply=null;
		LocalInstance localInstance=null;
		Session session=null;
		CAEventType type=null;
		
		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);
		if((type=(CAEventType)session.get(CAEventType.class, _eventId))!=null)
			reply=type.getName();
		if(reply==null)
			reply=_eventId;
		
		return reply;
	}
	
	public static final String getDomain(final String _dataSource,final String _value,final String _domain,final String _format) throws Exception{
		
		String reply=null;
		SDMConfigManager manager=null;
		LocalInstance localInstance=null;
		Session session=null;
		Map<String,String> domain=null;
		
		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);
		manager=SDMConfigManager.getInstance();
		if(_format!=null){
			domain=manager.getDomain(session,_domain,_format);
			reply=domain.get(_value);
		}
		if(reply==null)
			reply=_value;
		
		return reply;
	}

	public static final String getInverseDomain(final String _dataSource,final String _normal,final String _domain,final String _format) throws Exception{
		
		String reply=null;
		SDMConfigManager manager=null;
		LocalInstance localInstance=null;
		Session session=null;
		Map<String,String> domain=null;
		
		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);
		manager=SDMConfigManager.getInstance();
		if(_format!=null){
			domain=manager.getDomain(session,_domain,_format);
			for(String domainParticular:domain.keySet()){
				if(domain.get(domainParticular).equals(_normal))
					reply=domainParticular;
			}
		}
		if(reply==null)
			reply=_normal;
		
		return reply;
	}
	
	public static final String formatScript(final String _script) throws Exception{
	
		String reply=null;
		StringBuffer stringBuffer=null;
		List<String> scriptLines=null;
		BufferedReader reader=null;
		String st1=null;
		int digids=1;
	
		try{
			reply=_script;
			if((reply!=null)&&(reply.length()>0)){
				scriptLines=new ArrayList<String>();
				reader=new BufferedReader(new StringReader(_script));
				//Buffer the scripts
				while((st1=reader.readLine())!=null){
					scriptLines.add(st1);
				}
				reader=null;
				//Count num lines digids
				digids=String.valueOf(scriptLines.size()).length();
				//Mount output
				stringBuffer=new StringBuffer();
				for(int ic1=0;ic1<scriptLines.size();ic1++){
					stringBuffer.append(StringUtils.leftPad(String.valueOf(ic1+1),digids,'0'));
					stringBuffer.append("|	");
					stringBuffer.append(scriptLines.get(ic1));
					stringBuffer.append('\n');
				}
				reply=stringBuffer.toString();
			}
		}catch (Exception e) {
			throw e;
		}
		
		return reply;
	}
	
	public static final String getSecurityFinancialAssetDetailValue(final SPSecurityFinancialAssetsDetails _faDetails,final SPSecurityDetail _securityDetail) throws Exception{ 
		String reply=null;
		String[] fieldPathSplitted=null;
		String field=null;
		int position=0;

		try {
			fieldPathSplitted=_faDetails.getFieldPath().split(":");
			field=fieldPathSplitted[0];
			position=Integer.parseInt(fieldPathSplitted[1]);
			reply=_securityDetail.get(field+position);
		}catch (Exception e){
			throw e;
		}
		return reply;
	}
	
	public static final Boolean isEventFieldRequired (final String _dataSource,final  String _eventTypeDetailId) throws Exception{
		Boolean reply = false;
		LocalInstance localInstance=null;
		Session session=null;
		Query query=null;

		try{
			localInstance=DataSourcesManager.getLocalInstance();
			session=localInstance.getHibernateSession(_dataSource);

			query=session.createQuery("select required from CAEventFieldConfig where eventTypeDetail.id=:eventTypeId");
			query.setParameter("eventTypeId",Long.parseLong(_eventTypeDetailId));
			reply = (Boolean)query.uniqueResult();

		}catch (Exception e){
			throw e;
		}
		return reply;
	}
	
	@SuppressWarnings("unchecked")	
    public static final List getPlanifications(final String _plannifications) throws Exception{
		List reply=null;
		StringTokenizer st = new StringTokenizer(_plannifications, ",");
		reply=new ArrayList<UpdatableAdapter>();
		while (st.hasMoreElements()){
			reply.add(st.nextElement());
		}
		return reply;
    }
	
	@SuppressWarnings("unchecked")	
    public static final String getPlanificationName(final String _dataSource, final SPVirtualSecurity _security) throws Exception{
    	
		String reply=null;
		LocalInstance localInstance=null;
		Session session=null;
		Query query=null;
		List<String> planificationNames = null;
		
		try{
			localInstance=DataSourcesManager.getLocalInstance();
			session=localInstance.getHibernateSession(_dataSource);

			query=session.createQuery("select distinct(plann.name) from SPPlanningProcess plann, "+
					" SPSecuritiesPlannings secplan where secplan.security=:securityId and secplan.planning = plann.id");
			query.setParameter("securityId",(_security));
			planificationNames = query.list();
			if (planificationNames.size()>0){
				reply = query.list().toString().replace("[", "").replace("]", "");
			}

		}catch (Exception e){
			throw e;
		}
		
		return reply;
    }
	
	@SuppressWarnings("unchecked")	
    public static final boolean isSelectedPlanning(final String _dataSource, final String  _planning,final String  _security) throws Exception{

		boolean reply=false;
		LocalInstance localInstance=null;
		Session session=null;
		Query query=null;

		try{
			localInstance=DataSourcesManager.getLocalInstance();
			session=localInstance.getHibernateSession(_dataSource);
		
			query=session.createQuery("select secplan from SPSecuritiesPlannings secplan "+
					"where secplan.security.id=:securityId and secplan.planning.id=:planId");
			query.setParameter("securityId",(Long.parseLong(_security)));
			query.setParameter("planId",(Long.parseLong(_planning)));
			if (query.list().size()>0){
				reply = true;
			}

		}catch (Exception e){
			throw e;
		}
		
		return reply;
    }
	
	@SuppressWarnings("unchecked")
	public static final String getClientAnswerValue(HashMap<String, Object> _answer, String _path) throws Exception{
		String reply=null;

		try{
			
			reply = _answer.get(_path)!=null?(_answer.get(_path)).toString():"";
			
			if (_path.startsWith("TIMESTAMP") && !reply.equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.US);
	        	Date date = sdf.parse(reply);
	        	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

				reply=sdf2.format(date);
			}
		}catch (Exception e){
			throw e;
		}
		return reply;
	}
	
	@SuppressWarnings("unchecked")
	public static final List<Map<String,Object>> getAlivedEvents(final String _dataSource, String _userId) throws Exception{
		List<Map<String,Object>> reply=null;
		Map<String,Object> replyMap=null;
		Query query = null;
		Iterator ite=null;
		Object[] array =null;
		try{
			final LocalInstance localInstance=DataSourcesManager.getLocalInstance();
			final Session session=localInstance.getHibernateSession(_dataSource);
			reply=new ArrayList<Map<String,Object>>();
			
			String sQuery = "select distinct eventGroup.eventType.id, eventGroup.security.name, "+ 
				"eventGroup.security.isin, secPortfolio.custodianAccount.eventProvider.name, "+
				"eventGroup.masterEvent.executionDate, eventGroup.masterEvent.expirationDate, "+
				"eventGroup.masterEvent.operationalDate, eventGroup.masterEvent.id "+  
				"from CAEventGroup as eventGroup, SPSecurityPortfolio as secPortfolio "+
				"where eventGroup.security.id = secPortfolio.security.id "+
				"and (eventGroup.operationStatus.state.id.code=:statusAuthorizedCode or eventGroup.operationStatus.state.id.code=:statusNotifiedCode)"+
				"and eventGroup.auditor.refId=:refId "+
				"and secPortfolio.customer.manager.customerId=:idManager";
			
			query=session.createQuery(sQuery);
			query.setParameter("statusAuthorizedCode","AUTH");
			query.setParameter("statusNotifiedCode","NSTA");
			query.setParameter("refId",0l);
			query.setParameter("idManager",_userId);
			
			ite = query.list().iterator();
			
			while (ite.hasNext()){
				replyMap=new HashMap<String,Object>();
				array = (Object[])(ite.next());
				replyMap.put("eventTypeId", array[0]!=null?array[0].toString():"");
				replyMap.put("securityName", array[1]!=null?array[1].toString():"");
				replyMap.put("securityIsin", array[2]!=null?array[2].toString():"");
				replyMap.put("eventProviderName", array[3]!=null?array[3].toString():"");
				replyMap.put("executionDate", array[4]!=null?array[4].toString():"");
				replyMap.put("expirationDate", array[5]!=null?array[5].toString():"");
				replyMap.put("operationalDate", array[6]!=null?array[6].toString():"");
				replyMap.put("masterEventId", array[7]!=null?array[7].toString():"");
				
				reply.add(replyMap);
			}
			
		}catch (Exception e){
			throw e;
		}
		return reply;
	}
	
	@SuppressWarnings("unchecked")
	public static final List<Map<String,Object>> getAnswersPercentages(final String _dataSource, String _eventId, String _custodianId) throws Exception{
		List<Map<String,Object>> reply=null;
		Map<String,Object> replyMap=null;
		Map<String,Integer> questionsSumMap = null;
		List<Double> answersPercentages = null;
		Query query = null;
		Object answerValue = null;
		int acumValue = 0;
		try{
			final LocalInstance localInstance=DataSourcesManager.getLocalInstance();
			final Session session=localInstance.getHibernateSession(_dataSource);
			reply=new ArrayList<Map<String,Object>>();
			
			//recupera todas las SPProviderAccounts del evento
			String sQuery = "from SPProviderAccount as account where account.id in" +
				"(select distinct portfolio.custodianAccount.id from SPSecurityPortfolio as portfolio where portfolio.security.id in " +
				"(select event.security.id from CAEventCollected as event where event.id=:eventId))";
			/*
			if(_custodianId != null && _custodianId.length() > 0){
				sQuery += " and account.eventProvider.id=" + _custodianId;
			}*/
			query=session.createQuery(sQuery);
			query.setParameter("eventId",Long.parseLong(_eventId));
			List<SPProviderAccount> providerAccountList =(List<SPProviderAccount>) query.list();
			
			//recupera todas las CAQuestions del evento
			sQuery = "from CAQuestions where event.id=:eventId order by headerPosition";
			query=session.createQuery(sQuery);
			query.setParameter("eventId",Long.parseLong(_eventId));
			List<CAQuestions> questionsList =(List<CAQuestions>) query.list();
			
			//inicializa los campos de CAEventHoldingAnswer.dynamicTable que ser�n considerados
			questionsSumMap = new LinkedHashMap<String,Integer>();
			for(CAQuestions question : questionsList){
				questionsSumMap.put(question.getAnswerType().getId()+question.getHeaderPosition(), 0);
			}
			
			//por cada SPProviderAccount devuelve un registro 
			for(SPProviderAccount providerAccount : providerAccountList){
				replyMap=new LinkedHashMap<String,Object>();
				replyMap.put("custodian", providerAccount.getEventProvider().getName());
				replyMap.put("custodianAccount", providerAccount.getAccountNumber());
				replyMap.put("own", providerAccount.isOwnAccount());
				
				//recupera todas las CAEventHoldingAnswer para la SPProviderAccount dada
				sQuery = "from CAEventHoldingAnswer where holding in " +
					"(select id from SPSecurityPortfolio where custodianAccount.id=:providerAccountId)";
				query=session.createQuery(sQuery);
				query.setParameter("providerAccountId",providerAccount.getId());
				List<CAEventHoldingAnswer> answersList = (List<CAEventHoldingAnswer>) query.list();
				
				//por cada CAEventHoldingAnswer sumariza las que tienen seteado un valor
				for(CAEventHoldingAnswer answer : answersList){					
					for(String key : questionsSumMap.keySet()){
						answerValue = answer.getDynamicTable().get(key);
						if(answerValue != null){
							acumValue = ((Integer)questionsSumMap.get(key));
							acumValue++;
							questionsSumMap.put(key, acumValue);
						}
					}
				}
				
				//calcula los porcentajes de cada respuesta
				answersPercentages = new ArrayList<Double>();
				for(String key : questionsSumMap.keySet()){
					answersPercentages.add(questionsSumMap.get(key) * 100.0 / answersList.size());
				}
				replyMap.put("percentages", answersPercentages);
				
				reply.add(replyMap);
				
				//resetea la suma de respuestas
				for(String key : questionsSumMap.keySet()){
					questionsSumMap.put(key,0);
				}
			}
		}catch (Exception e){
			throw e;
		}
		return reply;
	}
    
	/**
	 * Función de ordenación de descripciones por orden alfabético para el combo EventType 
	 * @param _dataSource
	 * @return java.util.List<SortSelectBean>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")	
    public static final String formatLocaleNumber(final String format, final String locale, final String _number) throws Exception{		
		String result = "";
		try {
			NumberFormat numberFormat = NumberFormat.getInstance(new Locale(locale));
	        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
			//DecimalFormat decimalFormat = new DecimalFormat();
	        if(format!=null && format!=""){
	        	decimalFormat.applyPattern(format);
	        } else {
	        	// Default Number Format
	        	decimalFormat.applyPattern("#,###,###,##0.000000");
	        }
			Number number = decimalFormat.parse(_number);
			result = decimalFormat.format(number);
		} catch(Exception e){
			e.printStackTrace();
		}
        return result;
    }
	
	/**
	 * Función de ordenación de descripciones por orden alfabético para el combo AssetType 
	 * @param _dataSource
	 * @return java.util.List<SortSelectBean>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")	
    public static final List getAssetTypeSelect(final String _dataSource) throws Exception{
    	
		List<SortSelectBean> reply=null;
		Session session=null;
		LocalInstance localInstance=null;
		Query query=null; 
		ResourceBundle bundle = I18nSystem.getInstance().getBundle("sdm");
		String idSelect=null;
		String description=null;
		
		reply=new ArrayList<SortSelectBean>();
		localInstance=DataSourcesManager.getLocalInstance();
		session=localInstance.getHibernateSession(_dataSource);
		query=session.createQuery("select id from AssetType");
		
		for(Object id: query.list()){
			idSelect = (String)id;
			description=bundle.getString("asset.type."+(String)id);
			SortSelectBean ssb= new SortSelectBean(idSelect,description);
			reply.add(ssb); 
		}
		
		Collections.sort(reply);
		
		return reply;
    }
}