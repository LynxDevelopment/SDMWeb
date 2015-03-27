package com.lynxspa.sdm.jstl.beans;

import javax.servlet.jsp.PageContext;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.lynxspa.sdm.entities.events.adapters.CAEventCollectedAdapter;
import com.lynxspa.sdm.entities.events.types.CAEventTypeDetail;
import com.lynxspa.sdm.utils.EventDetailUtils;


public class EventFieldBean{

	private String name=null;
	private String description=null;
	private String type=null;
	private String format=null;
	private Object value=null;
	
	
	public EventFieldBean(){}
	public EventFieldBean(final PageContext _pageContext,final CAEventTypeDetail _eventTypeDetail,final CAEventCollectedAdapter _event,final int _extensionNumber) throws Exception{
		if(_eventTypeDetail!=null){
			this.name=_eventTypeDetail.getName();
			this.description=_eventTypeDetail.getDescription();
			this.type=_eventTypeDetail.getFieldType();
			this.format=_eventTypeDetail.getFormat();
			if(_event!=null){
				if(_eventTypeDetail.isEditableNormalization()){
					this.value=EventDetailUtils.getEventDetailValue(_event.getEventDetail(), _eventTypeDetail.getFieldPath(),_extensionNumber);
				}else{
					if(_eventTypeDetail.getFieldPath().startsWith("${")){
						_pageContext.setAttribute("varEventCollectedToEvaluate",_event);
						String st1="${varEventCollectedToEvaluate."+_eventTypeDetail.getFieldPath().substring(2);
						this.value=ExpressionEvaluatorManager.evaluate(_eventTypeDetail.getFieldPath(),st1,Object.class,_pageContext);
						_pageContext.removeAttribute("varEventCollectedToEvaluate");
					}
				}
			}
		}
	}
	public EventFieldBean(final PageContext _pageContext,final CAEventTypeDetail _eventTypeDetail,final CAEventCollectedAdapter _event) throws Exception{
		this(_pageContext,_eventTypeDetail,_event,0);
	}
	public EventFieldBean(final EventFieldBean _messageFieldBean,final String _name){
		this.name=_name;
		this.description=_messageFieldBean.getDescription();
		this.type=_messageFieldBean.getType();
		this.format=_messageFieldBean.getFormat();
		this.value=_messageFieldBean.getValue();
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description=description;
	}
	
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type=type;
	}

	public String getFormat(){
		return format;
	}
	public void setFormat(String format){
		this.format=format;
	}

	public Object getValue(){
		return value;
	}
	public void setValue(Object value){
		this.value=value;
	}
}
