package com.lynxspa.sdm.core.services.quartz.api.xml;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.lynxspa.sdm.core.services.validation.ValidationService;
import com.lynxspa.sdm.core.services.validation.Validator;
import com.lynxspa.sdm.core.utils.NormalizationUtils;

public abstract class BasicSaxHandler extends DefaultHandler {
	private @Autowired
	ValidationService		validationService;

	private StringBuilder	stringBuilder	= new StringBuilder();

	@Override
	public final void characters(char[] chr, int start, int end) throws SAXException {
		stringBuilder.append(new String(chr, start, end));
	}

	@Override
	public final void endElement(String uri, String localName, String qName) throws SAXException {
		closeElement(uri, localName, qName);
		stringBuilder = new StringBuilder();
	}

	protected abstract void closeElement(String uri, String localName, String qName) throws SAXException;

	protected final String getNodeText(boolean escaped) {
		if (escaped) {
			return NormalizationUtils.normalizeText(stringBuilder.toString().trim()).replaceAll("\\s+", " ");
		} else {
			return stringBuilder.toString().trim();
		}

	}

	protected final String getNodeText() {
		return getNodeText(true);
	}

}
