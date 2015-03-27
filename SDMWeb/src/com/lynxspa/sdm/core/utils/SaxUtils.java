package com.lynxspa.sdm.core.utils;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public final class SaxUtils {
	private SaxUtils() {}
	
	private static SAXParserFactory	saxParserFactory	= SAXParserFactory.newInstance();

	public static SAXParser getParser() {
		try {
			return saxParserFactory.newSAXParser();
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}
}
