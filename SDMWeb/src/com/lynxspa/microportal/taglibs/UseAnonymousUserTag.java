package com.lynxspa.microportal.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.lynxit.xweb.users.User;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxspa.xweb.actions.utils.AnonymousLoggedUser;

/**
 * @author albert.farre Forces use of anonymous user inside this tag.
 */
public class UseAnonymousUserTag extends BodyTagSupport {

	private static final long	serialVersionUID	= 4113091307074336490L;

	private Object				currentUser			= null;

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		this.currentUser = this.pageContext.getSession().getAttribute("LOGGED_USER");
		if (((User) this.currentUser).isAuthenticated()) {
			this.pageContext.getSession().setAttribute("LOGGED_USER", new AnonymousLoggedUser(((XWebUser) this.currentUser).getLocale()));
		} else
			this.currentUser = null;
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {

		if (this.currentUser != null)
			this.pageContext.getSession().setAttribute("LOGGED_USER", this.currentUser);
		return EVAL_PAGE;
	}
}
