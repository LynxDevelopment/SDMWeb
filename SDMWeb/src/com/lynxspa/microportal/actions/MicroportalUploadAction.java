package com.lynxspa.microportal.actions;

import java.io.File;

import org.hibernate.Session;
import org.w3c.dom.Element;

import com.lynxit.utils.UrlHelper;
import com.lynxit.utils.XMLConfigurator;
import com.lynxit.xweb.initialization.initializers.DataSourcesManager;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.configuration.MicroportalConfig;
import com.lynxspa.xweb.actions.UploadBasicAction;

public class MicroportalUploadAction extends UploadBasicAction {

	private String		datasourceName		= null;
	protected String[]	uploadParameters	= { "fileName" };

	@Override
	public void init(Element e) {
		this.datasourceName = XMLConfigurator.getParam(e, "datasource", "hibernateXWeb");
		super.init(e);
	}

	@Override
	protected String[] getParametersToUpload() throws Exception {
		return this.uploadParameters;
	}

	@Override
	protected UrlHelper getReturnedParams(UrlHelper _urlHelper, File[] _uploadedFiles) throws Exception {

		UrlHelper reply = null;

		reply = _urlHelper;
		for (int ic1 = 0; ic1 < this.uploadParameters.length; ic1++)
			reply.appendParam(this.uploadParameters[ic1], (_uploadedFiles[ic1] == null) ? "" : _uploadedFiles[ic1].getAbsolutePath());

		return reply;
	}

	@Override
	protected String getTemporalDirectory() throws Exception {

		String reply = null;
		Session session = null;

		try {
			session = DataSourcesManager.getLocalInstance().getHibernateSession(this.datasourceName);
			HibernateUtils.beguinTransaction(session);
			reply = (String) MicroportalManager.getInstance().getConfiguration(session, MicroportalConfig.TEMPORALDIRECTORY);
			reply = getRequest().getSession().getServletContext().getRealPath(reply);
			HibernateUtils.commitTransaction(session);
		} catch (Throwable e) {
			HibernateUtils.rollbackTransaction(session);
			throw new Exception(e);
		}

		return reply;
	}
}
