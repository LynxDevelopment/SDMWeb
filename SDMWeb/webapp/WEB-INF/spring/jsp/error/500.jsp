<%@ page language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="xweb" uri="http://www.lynxit.com/xweb/taglib"%>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- GLOBAL VARIABLES --%>
<c:set var="resourcesBase" value="${pageContext.request.contextPath}/resources" />
<c:set var="cssBase" value="${resourcesBase}/css" />
<c:set var="jsBase" value="${resourcesBase}/js" />
<c:set var="imgBase" value="${resourcesBase}/img" />

<div id="error-form">
	<h1 id="error-title"><spring:message  code="error.title" /></h1> 
	<div id="error-content">
		<spring:message code="error.content" />		
		<p id="error-back-button" class="clearfix" style="margin-top: 2em;">
			<a style="float: left;" class="button" href="<c:url value='' />"><spring:message code="back" /></a>
		</p>
	</div>
</div>
