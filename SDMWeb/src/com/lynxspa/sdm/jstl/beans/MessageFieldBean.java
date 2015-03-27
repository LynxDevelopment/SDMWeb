package com.lynxspa.sdm.jstl.beans;

import com.lynxspa.sdm.entities.events.messages.adapters.CAEventMessageFieldAdapter;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFieldConfig;

public class MessageFieldBean{

	private String name=null;
	private String description=null;
	private String type=null;
	private String format=null;
	private String path=null;
	private String value=null;
	private boolean hidden=false;
	private boolean configured=false;
	
	
	public MessageFieldBean(final CAEventMessageFieldAdapter _eventField){
		this.path=_eventField.getPath();
		this.value=(_eventField.getValue()==null)? "" : _eventField.getValue();
	}
	public MessageFieldBean(final MessageFieldBean _messageFieldBean,final String _name){
		this.name=_name;
		this.description=_messageFieldBean.getDescription();
		this.type=_messageFieldBean.getType();
		this.format=_messageFieldBean.getFormat();
		this.path=_messageFieldBean.getPath();
		this.value=_messageFieldBean.getValue();
		this.configured=_messageFieldBean.isConfigured();
		this.hidden=_messageFieldBean.isHidden();
	}

	public void completeWithConfigurationInfo(final CAMessageFieldConfig _config){
		if(_config!=null){
			this.configured=true;
			this.name=_config.getFieldName();
			this.description=_config.getDescription();
			this.type=_config.getFieldType();
			this.format=_config.getFieldFormat();
			this.hidden=_config.isHidden();
		}
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
	
	public String getPath(){
		return path;
	}
	public void setPath(String path){
		this.path=path;
	}

	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value=value;
	}
	
	public boolean isHidden(){
		return hidden;
	}
	public void setHidden(boolean hidden){
		this.hidden=hidden;
	}

	public boolean isConfigured(){
		return configured;
	}
	public void setConfigured(boolean configured){
		this.configured=configured;
	}
}
