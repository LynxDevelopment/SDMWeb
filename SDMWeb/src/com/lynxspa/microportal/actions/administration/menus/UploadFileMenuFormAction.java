package com.lynxspa.microportal.actions.administration.menus;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lynxit.utils.UrlHelper;
import com.lynxit.utils.XMLConfigurator;
import com.lynxit.xweb.actions.Action;
import com.lynxit.xweb.upload.MultipartRequest;

/**
 * Aggiorna i campi specificati nel file di configurazione nella tabella
 * specificata dal parametro di configurazione "TABLE_NAME". Used by:
 * administrator, power user
 * 
 * schema di configurazione
 * 
 * &lt;action&gt; &lt;name/&gt; &lt;class/&gt; &lt;page/&gt; &lt;errpage/&gt;
 * &lt;update_params&gt; &lt;param/&gt; &lt;param/&gt; &lt;update_params/&gt;
 * &lt;/action&gt;
 * 
 */

public class UploadFileMenuFormAction extends Action {

	private static final Logger	logger_			= Logger.getLogger(UploadFileMenuFormAction.class.getName());

	private String				redirectPage_	= "";
	private String				errorPage_		= "";
	private String				uploadPath_		= null;
	private File				dirUpload_		= null;
	private String[]			uploadParams_;

	@Override
	public void init(Element e) {

		try {
			Element uploadParams;
			NodeList params;
			Node child;

			setActionName(XMLConfigurator.getParam(e, "name"));
			redirectPage_ = XMLConfigurator.getParam(e, "page");
			errorPage_ = XMLConfigurator.getParam(e, "errpage");
			uploadPath_ = XMLConfigurator.getParam(e, "uploadpath");

			// creo un vettore per la lista dei campi per l'upload
			uploadParams = (Element) e.getElementsByTagName("upload_params").item(0);
			params = uploadParams.getElementsByTagName("param");
			uploadParams_ = new String[params.getLength()];

			for (int x = 0; x < params.getLength(); x++) {
				child = params.item(x);
				uploadParams_[x] = child.getFirstChild().getNodeValue();
				logger_.debug("uploadParams " + uploadParams_[x]);
			}

		} catch (Exception ex) {
			logger_.fatal("Error creating the action table.", ex);
		}

	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			String tmpTimestampFile = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(new Date());
			uploadPath_ = new StringBuilder().append("/upload/boxlet/tmp").append("/").append(tmpTimestampFile).toString();

			createDir(uploadPath_);

			// Array per salvare il path completo di tutti i file
			String[] uploadFiles = new String[uploadParams_.length];

			MultipartRequest multi = new MultipartRequest(request, dirUpload_.getPath());

			for (int x = 0; x < uploadParams_.length; x++) {
				File fileUploaded = multi.getFile(uploadParams_[x]);

				if (fileUploaded != null) {
					// Salvo il path completo del file:
					uploadFiles[x] = uploadPath_ + "/" + fileUploaded.getName();
					uploadFiles[x] = uploadFiles[x].replaceAll("//", "/");
					logger_.debug("Il file " + fileUploaded.getName() + " (" + uploadParams_[x] + ") è stato caricato in " + uploadPath_);
				} else {
					uploadFiles[x] = null;
					logger_.debug("Nessun file associato al parametro " + uploadParams_[x]);
				}
			}

			UrlHelper urlhelper = new UrlHelper(redirectPage_);

			// Aggiungo all'url i vari path dei file salvati
			for (int x = 0; x < uploadParams_.length; x++) {
				// Solo se il file era stato caricato:
				if (uploadFiles[x] != null) {
					urlhelper.appendParam(uploadParams_[x], uploadFiles[x]);
					urlhelper.appendParam("success", "true");
					urlhelper.appendParam("tmpTimestampFile", uploadPath_);

				}
			}

			super.redirect(urlhelper.getUrl(), request, response);

		} catch (Exception e) {
			logger_.error("", e);
			UrlHelper urlhelper = new UrlHelper(errorPage_);
			urlhelper.appendParam(ERROR_MSG, e.getMessage());
			urlhelper.appendParam("success", "false");
			super.redirect(urlhelper.getUrl(), request, response);
		}
	}

	private void createDir(String dir) throws Exception {

		uploadPath_ = dir;

		if (uploadPath_ == null) {
			Exception ex = new Exception("uploadPath missing in action config");
			logger_.fatal("uploadPath missing in action config");
			throw ex;
		} else {
			dirUpload_ = new File(super.getServletContext().getRealPath("/") + uploadPath_);

			if (!dirUpload_.exists()) {
				try {
					dirUpload_.mkdirs();
				} catch (Exception exc) {
					logger_.fatal("unable to create directory");
				}
			}

		}
	}
}// end class
