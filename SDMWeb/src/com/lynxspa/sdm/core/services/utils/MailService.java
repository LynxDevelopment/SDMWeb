package com.lynxspa.sdm.core.services.utils;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lynxspa.sdm.core.managers.configuration.ConfigurationDictionary;
import com.lynxspa.sdm.core.services.cfg.XWebConfigurationService;

@Service
public class MailService {
	public static class SMTPAuthenticator extends Authenticator {

		private PasswordAuthentication	authentication	= null;

		public SMTPAuthenticator(String _user, String _password) {
			this.authentication = new PasswordAuthentication(_user, _password);
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return this.authentication;
		}
	}

	@Autowired
	private XWebConfigurationService	cfgService;

	/**
	 * Crea un mensaje de correo.
	 * 
	 * @param to
	 * @param subject
	 * @param message
	 * @return El mensaje construido
	 * @throws MessagingException
	 */
	public MimeMessage buildMessage(String to, String subject, String message) throws MessagingException {
		MimeMessage reply=null;
		javax.mail.Session mailSession = null;

		final Properties mailProperties = getMailProperties();
		if (Boolean.valueOf(mailProperties.getProperty("mail.smtp.auth"))) {
			mailSession = javax.mail.Session.getInstance(mailProperties, getAuthenticator());
		} else {
			mailSession = javax.mail.Session.getInstance(mailProperties);
		}
		
		reply = new MimeMessage(mailSession);
		reply.setRecipients(Message.RecipientType.TO, to);
		reply.setSubject(subject);
		reply.setSentDate(Calendar.getInstance().getTime());
		reply.setContent(message + getAvisoLegal(), "text/html;charset=\"ISO-8859-1\"");
		
		return reply;
	}
	
	/**
	 * Envia el mensaje de email.
	 * 
	 * @param msg
	 * @throws MessagingException
	 */
	public void send(MimeMessage msg) throws MessagingException {
		Transport.send(msg);
	}

	protected Properties getMailProperties() {

		Properties reply = null;
		long serverPort = 0l;

		reply = new Properties();

		reply.put("mail.smtp.host", cfgService.getConfiguration(ConfigurationDictionary.MAIL_SERVER_ADDRESS).toString());
		if ((serverPort = Long.valueOf(cfgService.getConfiguration(ConfigurationDictionary.MAIL_SERVER_PORT).toString())) > 0l) {
			reply.put("mail.smtp.port", "" + serverPort);
		}
		reply.put("mail.from", cfgService.getConfiguration(ConfigurationDictionary.MAIL_CONTACT_ACCOUNT).toString());
		if ("PLAIN".equals(cfgService.getConfiguration(ConfigurationDictionary.MAIL_SERVER_AUTH_TYPE).toString())) {
			reply.put("mail.smtp.auth", "true");
			reply.put("mail.user", cfgService.getConfiguration(ConfigurationDictionary.MAIL_SERVER_AUTH_USER).toString());
		}

		return reply;
	}

	protected Authenticator getAuthenticator() {

		Authenticator reply = null;

		reply = new SMTPAuthenticator(cfgService.getConfiguration(ConfigurationDictionary.MAIL_SERVER_AUTH_USER).toString(), cfgService.getConfiguration(ConfigurationDictionary.MAIL_SERVER_AUTH_PASSWORD).toString());

		return reply;
	}

	protected String getAvisoLegal() {

		StringBuilder sb = new StringBuilder();

		sb.append("<br/><br/><br/><b>***** AVISO LEGAL *****</b>");
		sb.append("<br/>Este mensaje se dirige exclusivamente a su destinatario y puede contener informaci&oacute;n privilegiada o confidencial. Si no es usted el destinatario indicado, queda notificado de que la  utilización, divulgaci&oacute;n y/o copia sin autorizaci&oacute;n est&aacute; prohibida en virtud de la legislaci&oacute;n vigente.");
		sb.append("<br/>Si ha recibido este mensaje por error, le rogamos que nos lo comunique inmediatamente por esta misma v&iacute;a y proceda a su destrucci&oacute;n.");
		sb.append("<br/><br/>This message is  intended exclusively for its addresses and  may contain information that is CONFIDENTIAL & protected  by professional privileges. If you are not the intended  recipient you are  hereby notified that any dissemination,  copy  or disclosure  of this  communication  is strictly prohibited by law. If this message has been received in error, please immediatly notify us via e-mail and delete it.<br/>");

		return sb.toString();
	}
}
