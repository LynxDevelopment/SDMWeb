package com.lynxspa.sdm.jstl.views;

import java.util.ArrayList;
import java.util.List;

public class TabView{

	private String id=null;
	private String name=null;
	private String description=null;
	private List<TableView> tabData=null;
	
	
	public TabView(){
		this.tabData=new ArrayList<TableView>(8);
	}
	public TabView(final String _id,final String _name,final String _description){
		this.id=_id;
		this.name=_name;
		this.description=_description;
		this.tabData=new ArrayList<TableView>(8);
	}

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
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
	public List<TableView> getTabData(){
		return tabData;
	}
	public void setTabData(List<TableView> _tabData){
		this.tabData=_tabData;
	}
	public void addTable(final TableView _tableView){
		this.tabData.add(_tableView);
	}
}
