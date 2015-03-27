package com.lynxspa.sdm.events;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;
import com.lynxspa.validation.utils.ValidationsDict;

public class EventValidator {
	
	private static final String XWEB_DATE_FORMAT = "dd/MM/yyyy";
	
	protected Session session;
	
	public EventValidator(Session session){
		this.session = session;
	}
	
	public boolean isBlank(Object cand){
		return !ValidationsDict.ISNOTEMPTY.validate(new Object[]{cand});
	}
	
	public boolean isValidBoolean(Object cand){
		return ValidationsDict.ISBOOLEAN.validate(new Object[]{cand});
	}

	public boolean isValidLong(Object cand){
		return ValidationsDict.ISLONG.validate(new Object[]{cand});
	}

	public boolean isValidDouble(Object cand){
		return ValidationsDict.ISDOUBLE.validate(new Object[]{cand});
	}

	public boolean isValidTimestamp(Object cand){
		return ValidationsDict.ISDATE.validate(new Object[]{cand});
	}
	
	public boolean isValidString(Object cand, int maxLength){
		String s = String.valueOf(cand);
		return s.length() <= maxLength;
	}

	public boolean isValidClob(Object cand){
		return true;
	}

	public Boolean toBoolean(Object obj){
		try{
			return Boolean.valueOf(String.valueOf(obj));
		}
		catch(Exception e){
			return null;
		}
	}

	public Long toLong(Object obj){
		try{
			return Long.parseLong(String.valueOf(obj));
		}
		catch(Exception e){
			return null;
		}
	}

	public Double toDouble(Object obj){
		try{
			return Double.parseDouble(String.valueOf(obj));
		}
		catch(Exception e){
			return null;
		}
	}

	public Float toFloat(Object obj){
		try{
			return Float.parseFloat(String.valueOf(obj));
		}
		catch(Exception e){
			return null;
		}
	}

	public Date toDate(Object obj){
		Date date = null;

		if(obj instanceof Date){
			date = (Date)obj;
		}

		else if(obj instanceof String){			
			try{
				DateFormat df = new SimpleDateFormat(EventValidator.XWEB_DATE_FORMAT);
				date = df.parse((String)obj);
			}
			catch(Exception e){
				return null;
			}
		}
		return date;
	}

	public String toString(Object obj, int maxLength){
		String s = String.valueOf(obj);
		return isValidString(s, maxLength) ? s: s.substring(0, maxLength);
	}

	public String toClob(Object obj){
		return obj.toString();
	}
}
