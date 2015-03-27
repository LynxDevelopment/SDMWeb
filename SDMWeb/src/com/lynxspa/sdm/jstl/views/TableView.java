package com.lynxspa.sdm.jstl.views;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class TableView{

	private String name=null;
	private List<List<TableCellView>> tableData=null;
	
	
	public TableView(){
		this.tableData=new ArrayList<List<TableCellView>>(8);
		this.tableData.add(new ArrayList<TableCellView>(3));
	}
	public TableView(String _name){
		this.name=_name;
		this.tableData=new ArrayList<List<TableCellView>>(8);
		this.tableData.add(new ArrayList<TableCellView>(3));
	}

	
	public void addRow(){
		this.tableData.add(new ArrayList<TableCellView>(3));
	}
	public void addCell(final TableCellView _tableCell){
		getLastRow().add(_tableCell);
	}
	public List<TableCellView> getLastRow(){
		return this.tableData.get(this.tableData.size()-1);
	}
	
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public List<List<TableCellView>> getTableData(){
		return tableData;
	}
	public void setTableData(List<List<TableCellView>> tableData){
		this.tableData=tableData;
	}
}
