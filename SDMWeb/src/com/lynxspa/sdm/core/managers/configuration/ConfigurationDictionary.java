package com.lynxspa.sdm.core.managers.configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lynxspa.sdm.core.managers.configuration.adapters.ConfigurationEntryAdapter;

public enum ConfigurationDictionary implements ConfigurationEntryAdapter {
	CUSTOMER_ICON_SIZE_LIMIT("limit.size.customer_icon", Integer.class, new Integer(15 * 1000)), // 15K
	CUSTOMER_ICON_EXTENSIONS("customer.icon.extensions", String.class, "png"),

	PDF_EXTENSIONS("extensions.newsletters", String.class, "pdf"),
	PDF_MAX_SIZE("limit.size.pdf", Integer.class, new Integer(2000 * 1000)), // 2000K
	PDF_THUMB_SIZE_LIMIT("limit.size.pdf_thumb", Integer.class, new Integer(150 * 1000)), // 150K

	SPONSOR_SIZE_LIMIT("limit.size.sponsor", Integer.class, new Integer(80 * 1000)), // 80K

	DATE_FORMAT("format.date", String.class, "dd/MM/yyyy"),
	PUBLIC_DATE_FORMAT("format.public.date", String.class, "EEEEEEEEEEE dd 'de' MMMMMMMMMMMMMM"),

	VIDEO_EXTENSIONS("video.extensions", String.class, "mp4|avi|wav"),
	AUDIO_EXTENSIONS("audio.extensions", String.class, "mp3|wma"),
	IMAGE_EXTENSIONS("image.extensions", String.class, "png|jpg"),

	VIDEO_SIZE("video.max.size", Integer.class, new Integer(50 * 1000 * 1000)), // 50M
	AUDIO_SIZE("audio.max.size", Integer.class, new Integer(5 * 1000 * 1000)), // 5M


	APPLICATION_LOCALE("application.locale", String.class, "es"),

	FILTER_NEWSITEMSFILTER_BACKWARD_COUNT("NewsItemsFilter.backward.count", Integer.class, new Integer(50)),

	FILTER_DIARYFILTER_BACKWARD_COUNT("GDiaryFilter.backward.count", Integer.class, new Integer(5)),
	FILTER_DIARYFILTER_FORWARD_COUNT("GDiaryFilter.forward.count", Integer.class, new Integer(20)),

	FILTER_PARTYFILTER_BACKWARD_COUNT("PartyFilter.backward.count", Integer.class, new Integer(5)),
	FILTER_PARTYFILTER_FORWARD_COUNT("PartyFilter.forward.count", Integer.class, new Integer(20)),
	
	FILTER_VIPFILTER_BACKWARD_COUNT("VipOffersFilter.backward.count", Integer.class, new Integer(5)),
	FILTER_VIPFILTER_FORWARD_COUNT("VipOffersFilter.forward.count", Integer.class, new Integer(20)),

	CONTEXT_TEMPORAL_DIRECTORY("Context.temporal.directory", String.class, ""),

	VALID_URL_PREFIXES("valid.url.prefixes", String.class, "http://|https://"),
	VALIDATION_SERVER_RESPONSE_TIMEOUT("server.response.timeout", Integer.class, 5000),

	VIDEOS_RESERVED_SIZE("Videos.Reserved.Size", Integer.class, 500 * 1000 * 1000),
	RADIOS_RESERVED_SIZE("Radios.Reserved.Size", Integer.class, 500 * 1000 * 1000),

	VALID_CHARACTERS_FILTER("Valid.Characters.Filter", String.class, "qwèeértyúuüíiïòoópaàsdfghjklñzxcvbnmQWÈEÉRTYÚUÜÍIÏÒOÓPAÀSDFGHJKLÑZXCVBNMçÇ:;,._-+*/!\"·$%&/()=|@#~€¬1234567890?¿'"),
	APPLY_VALID_CHARACTERS_FILTER("Apply.Valid.Characters.Filter", String.class, "true"),

	MAIL_SERVER_ADDRESS("Mail.Server.Address", String.class, "mbox.lynxspa.com"),
	MAIL_SERVER_PORT("Mail.Server.Port", Integer.class, 25l),
	MAIL_SERVER_AUTH_TYPE("Mail.Server.Auth.Type", String.class, "PLAIN"),
	MAIL_SERVER_AUTH_USER("Mail.Server.Auth.User", String.class, "soporte.tecnico"),
	MAIL_SERVER_AUTH_PASSWORD("Mail.Server.Auth.Password", String.class, "Batraci09"),
	MAIL_CONTACT_ACCOUNT("Mail.Contact.Account", String.class, "soporte.tecnico@lynxiberica.com");
	;

	private Class<?>	type;
	private Object		value;
	private String		key;

	private ConfigurationDictionary(final String key, final Class<?> _class, final Object value) {
		this.type = _class;
		this.value = value;
		this.key = key;
	}

	@Override
	public String getKey() {
		return key;
	}

	@SuppressWarnings("all")
	public Object getValue() {
		if (value instanceof String) {
			try {
				return castValue(this, value.toString());
			} catch (Exception ex) {
				return null;
			}
		} else {
			return value;
		}
	}

	@Override
	public Class<?> getType() {
		return type;
	}

	public static final Object castValue(ConfigurationEntryAdapter adapter, Object value) throws ParseException {
		if (adapter.getType().isAssignableFrom(value.getClass())) {
			return value;
		}
		if (adapter.getType().isAssignableFrom(Integer.class)) {
			return Integer.valueOf(value.toString());
		} else if (adapter.getType().isAssignableFrom(String.class)) {
			return value;
		}
		if (adapter.getType().isAssignableFrom(Date.class)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.parse(value.toString());
		}
		return value;
	}

}
