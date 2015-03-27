package com.lynxspa.sdm.core.utils;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import com.lynxspa.sdm.core.utils.collections.Pair;

public final class HtmlUtils {
	private static Logger logger = Logger.getLogger(HtmlUtils.class);
	
	private HtmlUtils() {}
	
	public static String forceHtmlContent(String plainText) {
		Pair<OutputSettings, Whitelist> cfg = readConfiguration();
		cfg.getFirst().escapeMode(EscapeMode.xhtml);
		
		Document doc = Jsoup.parseBodyFragment(plainText);
		Element body = doc.body();
		
		String output = plainText;
		Elements query = doc.select("body > *").select(":matches(^$)");
		
		if (query.isEmpty() == false) {
			query.remove();
		}
		
		if (body.children().size() == 0) {
			 output = "<p align='justify'>" + body.html() + "\n</p>";
		}
		return StringEscapeUtils.unescapeXml(Jsoup.clean(output, "", cfg.getSecond(), cfg.getFirst()));
	}
	
	public static String cleanHtml(String htmlCode) {	
		Pair<OutputSettings, Whitelist> settings = readConfiguration();
		
		Document doc = Jsoup.parseBodyFragment(htmlCode);
		Elements query = doc.select("br");
		query.append("<p>&nbsp;</p>");
		
		htmlCode = Jsoup.clean(doc.body().html(), "", settings.getSecond(), settings.getFirst());
		doc = Jsoup.parseBodyFragment(htmlCode);

		doc.outputSettings(settings.getFirst());		
		query = doc.select("body > *").select(":matches(^$)");
		if (query.isEmpty() == false) {
			query.remove();
		}
				
		for (Element child : doc.body().children()) {
			child.attr("align", "justify");
		}
		
		return doc.body().html();
	}
	
	public static String cleanAllHtml(String htmlCode) {
		return Jsoup.clean(htmlCode, "", Whitelist.none(), readConfiguration().getFirst());
	}
	
	public static String cleanAllHtmlNoEscape(String htmlCode) {
		Pair<OutputSettings, Whitelist> p = readConfiguration();
		p.getFirst().escapeMode(EscapeMode.xhtml);
		
		return StringEscapeUtils.unescapeXml(Jsoup.clean(htmlCode, "", Whitelist.none(), p.getFirst()));
	}
	
	private static Pair<OutputSettings, Whitelist> readConfiguration() {
		Whitelist whiteList = Whitelist.none();
		ResourceBundle cfg = ResourceBundle.getBundle("htmlutils");
		OutputSettings s = new OutputSettings();
		
		Enumeration<String> keys = cfg.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = cfg.getString(key).trim();
				
			if (key.startsWith("tag.")) {
				String[] validAttrs = value.split(",");
				whiteList.addAttributes(key.substring(4), validAttrs);
				
			} else if (key.equalsIgnoreCase("configuration.escape.mode")) {
				s.escapeMode(EscapeMode.valueOf(value));
			} else if (key.equalsIgnoreCase("configuration.indent.amount")) {
				s.indentAmount(Integer.valueOf(value));
			} else if (key.equalsIgnoreCase("configuration.outline")) {
				s.outline(Boolean.valueOf(value));
			} else if (key.equalsIgnoreCase("configuration.pretty.print")) {
				s.prettyPrint(Boolean.valueOf(value));
			} else if (key.equalsIgnoreCase("configuration.charset")) {
				if (!value.equalsIgnoreCase("*") && !value.isEmpty()) {
					s.charset(value);
				}
			} else {
				logger.warn("Unknown property at htmlutils.properties: '" + key + "'. Ignoring it.");
			}
		}
		return Pair.makePair(s, whiteList);
	}
}
