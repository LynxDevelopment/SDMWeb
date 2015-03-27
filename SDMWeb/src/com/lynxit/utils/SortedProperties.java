package com.lynxit.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class SortedProperties extends Properties {

	private static final long serialVersionUID = -6276211999583824237L;
	
	
	/**
	 * Overrides, called by the store method.
	 */
	@SuppressWarnings("unchecked")
	public synchronized Enumeration keys() {
		
		final List reply=new ArrayList();
		final Enumeration keysEnum=super.keys();
		
		while(keysEnum.hasMoreElements())
			reply.add(keysEnum.nextElement());
		Collections.sort(reply);
		
		return Collections.enumeration(reply);
	}
}