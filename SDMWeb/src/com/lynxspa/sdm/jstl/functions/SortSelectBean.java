package com.lynxspa.sdm.jstl.functions;
import java.io.Serializable;

public class SortSelectBean implements Serializable,Comparable{
	
	private String key;
	private String description;
	
	public SortSelectBean(){
		
	}
	
	public SortSelectBean(String key, String description){
		
		this.key=key;
		this.description=description;
	}
	
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

	@SuppressWarnings("unused")
	private void setDescription(String description) {
		this.description = description;
	}

	private String getDescription() {
		return description;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	
	@Override
	public int compareTo(Object o) {
		SortSelectBean ssb = (SortSelectBean)o;
		return description.compareTo(ssb.getDescription());
	}

}
