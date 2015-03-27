package com.lynxspa.microportal.taglibs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.hibernate.Session;

import com.lynxit.xweb.initialization.initializers.DataSourcesManager;
import com.lynxit.xweb.initialization.initializers.DataSourcesManager.LocalInstance;
import com.lynxit.xweb.xmenu.entities.SimpleMenuItem;

public class TreeTag extends BodyTagSupport implements Serializable {

	private static final long	serialVersionUID	= 6436495005105503385L;

	private String				id;
	private String				dataSource;

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {

		LocalInstance localInstance = null;
		Session session = null;

		try {

			localInstance = DataSourcesManager.getLocalInstance();
			session = localInstance.getHibernateSession(dataSource);

			List<SimpleMenuItem> listSimpleMenuItem = session.createQuery("" + "from SimpleMenuItem " + "where " + "	url is null and" + "	id not like('/private/administration%') and id!='/admin' and id!='/'" + "order by id asc,child_index asc").list();

			LinkedList<SimpleMenuItem> linkedList = new LinkedList<SimpleMenuItem>();

			int level = getLevel(id);
			String[] searched = id.split("\\/");
			String olderUncle = null;
			for (SimpleMenuItem menu : listSimpleMenuItem) {
				int levelactual = getLevel(menu.getId());
				if (levelactual <= (level + 1)) {
					if (menu.getId().startsWith(id)) {
						linkedList.add(menu);
					} else {
						olderUncle = getOlderUncle(searched, menu.getId());
						if (olderUncle.equals(menu.getId())) {
							linkedList.add(menu);
						}

					}
				}
			}

			pageContext.getSession().setAttribute("listMenuIdLink", generateMenuColumnsLinks(linkedList));

		} catch (Exception e) {
			throw new JspException(e.getMessage(), e);
		}

		return SKIP_BODY;
	}

	private List<String> generateMenuColumnsLinks(List<SimpleMenuItem> listMenu) {

		List<String> list = new ArrayList<String>();

		StringBuilder sb = null;
		int level = 0;
		for (SimpleMenuItem menu : listMenu) {
			sb = new StringBuilder();
			level = getLevel(menu.getId());

			for (int inc = 0; inc <= level; inc++) {
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			sb.append("· ");
			sb.append("<a href=\"javascript:refreshMenu('").append(menu.getId()).append("');\">");
			if (level <= 1) {
				sb.append(menu.getId().substring(1));
			} else {
				sb.append(menu.getId().substring(menu.getId().lastIndexOf("/") + 1, menu.getId().length()));
			}
			sb.append("</a>");
			list.add(sb.toString());
		}

		return list;
	}

	@SuppressWarnings("unused")
	private String getParent(String menuId) {
		return menuId.substring(0, menuId.lastIndexOf("/"));
	}

	private int getLevel(final String _path) {
		return _path.replaceAll("[^/]", "").length();
	}

	private String getOlderUncle(final String[] _searched, final String _found) {

		StringBuilder reply = null;
		String[] splittedFound = null;
		int minimum = 0;
		boolean found = false;

		reply = new StringBuilder();
		splittedFound = _found.split("\\/");
		minimum = (splittedFound.length < _searched.length) ? splittedFound.length : _searched.length;
		for (int ic1 = 0; ((ic1 < minimum) && (!found)); ic1++) {
			if (ic1 != 0)
				reply.append('/');
			reply.append(splittedFound[ic1]);
			if (!_searched[ic1].equals(splittedFound[ic1]))
				found = true;
		}

		return reply.toString();
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
