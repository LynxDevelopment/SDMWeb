package com.lynxspa.sdm.jstl.views;


public class TableCellView<DATA>{
	
	private boolean label=false;
	private int colspan=0;
	private String textAlign="left";
	private DATA data=null;
	
	
	public TableCellView(final boolean _isLabel,final DATA _data){
		this(_isLabel,_data,0,null);
	}
	public TableCellView(final boolean _isLabel,final DATA _data,final int _colspan){
		this(_isLabel,_data,_colspan,null);
	}
	public TableCellView(final boolean _isLabel,final DATA _data,final int _colspan,final String _textAlign){
		this.label=_isLabel;
		this.colspan=_colspan;
		this.data=_data;
		if(_textAlign!=null)
			this.textAlign=_textAlign;
	}

	public boolean isLabel(){
		return label;
	}
	public void setLabel(boolean label){
		this.label=label;
	}
	public int getColspan(){
		return colspan;
	}
	public void setColspan(int colspan){
		this.colspan=colspan;
	}
	public String getTextAlign(){
		return textAlign;
	}
	public void setTextAlign(String textAlign){
		this.textAlign=textAlign;
	}
	public DATA getData(){
		return data;
	}
	public void setData(DATA data){
		this.data=data;
	}
}
