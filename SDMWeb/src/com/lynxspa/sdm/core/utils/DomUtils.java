package com.lynxspa.sdm.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class DomUtils {
	private static final Logger	logger	= Logger.getLogger(DomUtils.class);
	private static DomUtils		instance;
	private DocumentBuilder		docBuilder;
	private Transformer			transformer;

	private DomUtils() throws ParserConfigurationException, TransformerConfigurationException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		this.docBuilder = docBuilderFactory.newDocumentBuilder();
		this.transformer = tFactory.newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	}

	public synchronized static DomUtils getInstance() {
		try {
			if (DomUtils.instance == null) {
				DomUtils.instance = new DomUtils();
			}
			return DomUtils.instance;
		} catch (Exception e) {
			logger.error("Could not instantiate DomUtils: " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public Document parseDocument(String path) throws SAXException, IOException {
		return this.docBuilder.parse(path);
	}

	public Document parseDocument(InputStream is) throws SAXException, IOException {
		return this.docBuilder.parse(is);
	}
	
	public Document buildDocument() {
		return this.docBuilder.newDocument();
	}

	public void saveDocument(Document doc, String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			DOMSource source = new DOMSource(doc);
			FileOutputStream fOutStream = new FileOutputStream(file);
			StreamResult result = new StreamResult(fOutStream);
			transformer.transform(source, result);
			fOutStream.close();
		} catch (Throwable ex) {
			logger.info(ex.getMessage(), ex);
		}
	}
	
	public String writeDocumentToString(Document doc) throws TransformerException {
		StringWriter buffer = new StringWriter();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		
		transformer.transform(new DOMSource(doc), new StreamResult(buffer));
		return buffer.toString();
	}
}
