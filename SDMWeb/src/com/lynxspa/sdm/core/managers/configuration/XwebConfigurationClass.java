package com.lynxspa.sdm.core.managers.configuration;

import com.lynxspa.dictionaries.ClassType;
import com.lynxspa.entities.application.configurations.adapter.ConfigDictAdapter;

public enum XwebConfigurationClass implements ConfigDictAdapter {
	// Configuración del MICROPORTAL
	PASSWORDEXPIRATIONMARGIN("microportal.pwdResetExpMargin", "Password expiration margin in days", ClassType.LONG, 10l, true, true),
	DEFAULTLANGUAGE("microportal.DefaultLanguage", "Default language", ClassType.SHORTSTRING, "en", false, false),
	CONFIGUREDLANGUAGES("microportal.configured.languages", "Configured languages", ClassType.MIDDLESTRING, "es,ca,en,fr", false, false),
	UPLOADFILESPATH("microportal.uploadfiles.path", "Raiz del path a partir de la cual se pueden guardar ficheros", ClassType.LONGSTRING, "/upload/boxlet", true, true),
	TEMPORALDIRECTORY("temporal.directory", "Temporal directory (should be into webapp deployment)", ClassType.VERYLONGSTRING, "userfiles/temp", true, true),
	// Configuración del GPM
	CUSTOMERICONSIZELIMIT("limit.size.customer_icon", "Maximum size in bytes of the customer icon", ClassType.LONG, 15000, true, true),
	CUSTOMERICONDIMLIMIT("limit.dimensions.customer_icon", "Maximum dimensions of the customer icon", ClassType.VERYLONGSTRING, "32x32", true, true),
	CUSTOMERICONEXTENSIONS("customer.icon.extensions", "Valid image extensions", ClassType.VERYLONGSTRING, "png", true, true),
	PDFEXTENSIONS("extensions.newsletters", "Valid document extensions", ClassType.VERYLONGSTRING, "pdf", true, true),
	PDFMAXSIZE("limit.size.pdf", "Maximum newsletter document size", ClassType.LONG, 150000, true, true),
	
	PDFPAGEPDFSIZELIMIT("limit.size.pdfpage.pdf", "Maximum pdf page bytes", ClassType.LONG, 1000000, true, true), //1000K
	PDFPAGETHUMBSIZELIMIT("limit.size.pdfpage.pdf_thumb", "Maximum pdf front page bytes", ClassType.LONG, 250000, true, true), // 250K
	PDFPAGETHUMDIMENSIONLIMIT("limit.dimensions.pdfpage.thumb", "Maximum dimensions of pdf front page", ClassType.VERYLONGSTRING, "160x225", true, true),
	
	SHOPPHOTODIMENSIONLIMIT("limit.dimensions.shop.photo","Maximum dimensions of image", ClassType.VERYLONGSTRING, "2000x3000", true, true),
	SHOPPHOTOSIZELIMIT("limit.size.shop.photo", "Maximum image bytes", ClassType.LONG, 1000000, true, true), //1000K

	LIMITSIZEPDFTHUMB("limit.size.pdf_thumb", "PDF thumb image size", ClassType.LONG, 50000L, true, true),
	LIMITDIMENSIONPDFTHUMB("limit.dimensions.pdf_thumb", "Aprox. dimensions of the PDF thumb.", ClassType.VERYLONGSTRING, "160x225", true, true),
	LIMITSIZESPONSOR("limit.size.sponsor", "Sponsor maximum image size", ClassType.LONG, 80000, true, true),
	FORMATDATE("format.date", "Date pattern used for serializing internal dates in the XMLs", ClassType.VERYLONGSTRING, "dd/MM/yyyy", true, true),
	FORMATPUBLICDATE("format.public.date", "Date pattern used for serializing internal dates in the XMLs", ClassType.VERYLONGSTRING, "EEEEEEEEEEE dd 'de' MMMMMMMMMMMMMM", true, true),
	VIDEOEXTENSIONS("video.extensions", "Allowed file extensions for videos (without the initial dot)", ClassType.VERYLONGSTRING, "mp4|avi|wav", true, true),
	AUDIOEXTENSIONS("audio.extensions", "Allowed audio extensions for radios (without the initial dot)", ClassType.VERYLONGSTRING, "mp3|wma", true, true),
	IMAGEEXTENSIONS("image.extensions", "Allowed image extensions for thumbs (without the initial dot)", ClassType.VERYLONGSTRING, "png", true, true),
	VIDEOMAXSIZE("video.max.size", "Video maximum file size", ClassType.LONG, 50000000L, true, true),
	AUDIOMAXSIZE("audio.max.size", "Audio maximum file size", ClassType.LONG, 5000000L, true, true),
	LIMITDIMENSIONSFRONTPAGEPHOTO("limit.dimensions.frontpage.photo", "Aprox. dimensions of the frontpage image", ClassType.VERYLONGSTRING, "160x225", true, true),
	LIMITSIZEFRONTPAGEPHOTO("limit.size.frontpage.photo", "Maximum size of the frontpage image.", ClassType.LONG, 75000L, true, true),
	LIMITFRONTPAGETEXTLENGTH("limit.frontpage.text.length", "Frontpage maximum text length (in characters)", ClassType.LONG, 512L, true, true),
	LIMITSIZEDIARYTHUMB("limit.size.diary_thumb", "Maximum size of the parameters image.", ClassType.LONG, 250000L, true, true),
	LIMITDIMENSIONSDIARYTHUMB("limit.dimensions.diary_thumb", "Aprox. dimensions of the parameters image", ClassType.VERYLONGSTRING, "225x115", true, true),
	LIMITSIZEPARTYTHUMB("limit.size.party_thumb", "Maximum size of the parameters image.", ClassType.LONG, 250000L, true, true),
	LIMITDIMENSIONSPARTYTHUMB("limit.dimensions.party_thumb", "Aprox. dimensions of the parameters image", ClassType.VERYLONGSTRING, "225x115", true, true),
	LIMITSIZERADIOTHUMB("limit.size.radio_thumb", "Maximum size of the radio image.", ClassType.LONG, 250000L, true, true),
	LIMITDIMENSIONSRADIOTHUMB("limit.dimensions.radio_thumb", "Aprox. dimensions of the radio image", ClassType.VERYLONGSTRING, "225x115", true, true),
	LIMITSIZEVIDEOTHUMB("limit.size.video_thumb", "Maximum size of the video image.", ClassType.LONG, 250000L, true, true),
	LIMITDIMENSIONSVIDEOTHUMB("limit.dimensions.video_thumb", "Aprox. dimensions of the video image", ClassType.VERYLONGSTRING, "225x115", true, true),
	LIMITSIZENEWITEMSIMAGE("limit.size.newsItems_image", "Maximum size of the news item image.", ClassType.LONG, 250000L, true, true),
	LIMITDIMENSIONSNEWSITEMSIMAGE("limit.dimensions.newsItems_image", "Aprox. dimensions of the news item image", ClassType.VERYLONGSTRING, "225x115", true, true),

	FILTER_NEWSITEMSFILTER_BACKWARD_COUNT("NewsItemsFilter.backward.count", "Maximum entry count backwards to current date in the NewsItemsFilter", ClassType.LONG, 20L, true, true),

	FILTER_DIARYFILTER_BACKWARD_COUNT("GDiaryFilter.backward.count", "Maximum entry count backwards to current date in the GDiaryFilter", ClassType.LONG, 5L, true, true),
	FILTER_DIARYFILTER_FORWARD_COUNT("GDiaryFilter.forward.count", "Maximum entry count forwards to current date in the GDiaryFilter", ClassType.LONG, 20L, true, true),

	FILTER_PARTYFILTER_BACKWARD_COUNT("PartyFilter.backward.count", "Maximum entry count backwards to current date in the PartyFilter", ClassType.LONG, 5L, true, true),
	FILTER_PARTYFILTER_FORWARD_COUNT("PartyFilter.forward.count", "Maximum entry count forwards to current date in the PartyFilter", ClassType.LONG, 20L, true, true),
	
	FILTER_VIPFILTER_BACKWARD_COUNT("VipOffersFilter.backward.count", "Maximum entry count backwards to current date in the VipOffersFilter", ClassType.LONG, 5L, true, true),
	FILTER_VIPFILTER_FORWARD_COUNT("VipOffersFilter.forward.count", "Maximum entry count forwards to current date in the VipOffersFilter", ClassType.LONG, 20L, true, true),

	CONTEXT_TEMPORAL_DIRECTORY("Context.temporal.directory", "Directory where to save uploaded files", ClassType.VERYLONGSTRING, "", true, true),
	
	VALID_URL_PREFIXES("valid.url.prefixes", "Valid URL prefixes for any URL in a form", ClassType.VERYLONGSTRING, "http://|https://", true, true),
	VALIDATION_SERVER_RESPONSE_TIMEOUT("server.response.timeout", "Maximum timeout for a ping request to verify that the target server is alive", ClassType.LONG, 5000L, true, true),
	
	VIDEOS_RESERVED_SIZE("Videos.Reserved.Size", "Reserved hard disk space for the video section.", ClassType.LONG, 500 * 1000 * 1000, true, true),
	RADIOS_RESERVED_SIZE("Radios.Reserved.Size", "Reserved hard disk space for the radio section.", ClassType.LONG, 500 * 1000 * 1000, true, true),
	
	VALID_CHARACTERS_FILTER("Valid.Characters.Filter", "Valid characters in text inputs", ClassType.VERYLONGSTRING, "qwèeértyúuüíiïòoópaàsdfghjklñzxcvbnmQWÈEÉRTYÚUÜÍIÏÒOÓPAÀSDFGHJKLÑZXCVBNMçÇ:;,._-+*/!\"·$%&/()=|@#~€¬1234567890?¿", true, true),
	APPLY_VALID_CHARACTERS_FILTER("Apply.Valid.Characters.Filter", "Apply valid characters filter in text inputs", ClassType.VERYLONGSTRING, "true", true, true),
	
	MAIL_SERVER_ADDRESS("Mail.Server.Address","Mail.Server.Address", ClassType.VERYLONGSTRING, "mbox.lynxspa.com", true, true),
	MAIL_SERVER_PORT("Mail.Server.Port", "Mail.Server.Address", ClassType.LONG, 25l, true, true),
	MAIL_SERVER_AUTH_TYPE("Mail.Server.Auth.Type", "Mail.Server.Auth.Type", ClassType.VERYLONGSTRING, "PLAIN", true, true),
	MAIL_SERVER_AUTH_USER("Mail.Server.Auth.User", "Mail.Server.Auth.User", ClassType.VERYLONGSTRING, "soporte.tecnico", true, true),
	MAIL_SERVER_AUTH_PASSWORD("Mail.Server.Auth.Password", "Mail.Server.Auth.Password", ClassType.VERYLONGSTRING, "Batraci09", true, true),
	MAIL_CONTACT_ACCOUNT("Mail.Contact.Account", "Mail.Contact.Account", ClassType.VERYLONGSTRING, "soporte.tecnico@lynxiberica.com", true, true);
	;

	private String		code			= null;
	private String		description		= null;
	private ClassType	type			= null;
	private Object		defaultValue	= null;
	private boolean		updatable		= false;
	private boolean		editable		= false;

	XwebConfigurationClass(String _code, String _description, ClassType _type, Object _defaultValue, boolean _updatable, boolean _editable) {
		this.code = _code;
		this.description = _description;
		this.type = _type;
		this.defaultValue = _defaultValue;
		this.editable = _editable;
		this.updatable = _updatable;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public ClassType getType() {
		return type;
	}

	@Override
	public Object getDefaultValue() {
		return defaultValue;
	}

	@Override
	public boolean isEditable() {
		return editable;
	}

	@Override
	public boolean isUpdatable() {
		return updatable;
	}
}
