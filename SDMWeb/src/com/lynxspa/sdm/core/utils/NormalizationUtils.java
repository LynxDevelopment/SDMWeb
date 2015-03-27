package com.lynxspa.sdm.core.utils;

import java.net.URI;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringEscapeUtils;

public final class NormalizationUtils {
	private static URLCodec	apacheUrlCodec	= new URLCodec();

	private NormalizationUtils() {
	}

	/**
	 * Returns a default non null empty string "" if the provided
	 * String is null.
	 * 
	 * @param o
	 * @return the provided string or "" if it is null
	 */
	public static String notNull(String o) {
		if (o == null) {
			return "";
		}
		return o.trim();
	}

	/**
	 * Encodes the passed String as UTF-8 using an algorithm that's compatible
	 * with JavaScript's <code>encodeURIComponent</code> function. Returns <code>null</code> if the String is <code>null</code>.
	 * 
	 * @param s
	 *            The String to be encoded
	 * @return the encoded String
	 */
	public static String encodeUri(String s) {
		try {
			return new URI(null, s, null).toASCIIString();
		} catch (Exception ex) {
			try {
				return apacheUrlCodec.encode(s);
			} catch (EncoderException ex1) {
				return "";
			}
		}
	}

	public static String normalizeText(String s) {
		s = s.replaceAll("í", "'");
		s = s.replaceAll("ë", "'");
		s = s.replaceAll("î", "\"");
		s = s.replaceAll("ì", "\"");
		s = HtmlUtils.cleanHtml(s);
		s = StringEscapeUtils.unescapeXml(s);
		s = StringEscapeUtils.unescapeHtml4(s);

		return s.trim();
	}

	public static String normalizePlainText(String s) {
		s = s.replaceAll("í", "'");
		s = s.replaceAll("ë", "'");
		s = s.replaceAll("î", "\"");
		s = s.replaceAll("ì", "\"");
		s = StringEscapeUtils.escapeXml(s);

		return s.trim();
	}

	public static String normalizeFileName(String s) {
		s = s.toLowerCase();

		s = s.replaceAll("[ÈËÎÍ…»À ]", "e");
		s = s.replaceAll("[˙˘¸˚⁄Ÿ‹€]", "u");
		s = s.replaceAll("[ÌÏÔÓÕÃœŒ]", "i");
		s = s.replaceAll("[·‡‰‚¡¿ƒ¬]", "a");
		s = s.replaceAll("[ÛÚˆÙ”‘÷‘]", "o");
		s = s.replaceAll(" ", "_");
		s = s.replaceAll("[Ò—]", "n");
		s = s.replaceAll("[Á«]", "c");
		s = s.replaceAll("[%&$¬∑!()=:°ø*$#@,~]", "_");
		return s.trim();
	}

	public static String normalizeFileSize(Integer bytes) {
		return normalizeFileSize(Long.valueOf(bytes), true);
	}

	public static String normalizePhoneNumber(String s) {
		return s.replaceAll("[^0-9]", "").trim();
	}

	public static String normalizeFileSize(Long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit) {
			return bytes + " B";
		}
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre).trim();
	}
}
