<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html>
<c:set var="resourcesBase" value="${pageContext.request.contextPath}/resources" />
<c:set var="cssBase" value="${resourcesBase}/css" />
<c:set var="jsBase" value="${resourcesBase}/js" />
<c:set var="imgBase" value="${resourcesBase}/img" />

<tiles:useAttribute name="resourcesCss" id="cssResources"
	classname="java.util.List" />
<tiles:useAttribute name="resourcesJs" id="jsResources"
	classname="java.util.List" />

<head>
<title>GPM</title>
<script type="text/javascript" language="JavaScript">
var globalContextUrl = "<c:url value='/' />";
</script>

<link rel="Stylesheet" type="text/css" href="${cssBase}/layout/base.css" />
<link rel="Stylesheet" type="text/css" href="${cssBase}/design/base.css" />
<link rel="Stylesheet" type="text/css" href="${cssBase}/jquery-ui-theme.css" />
<link rel="Stylesheet" type="text/css" href="${cssBase}/colorbox.css" />

<c:forEach items="${cssResources}" var="res">
	<link rel="Stylesheet" type="text/css" href="${cssBase}/<tiles:insertAttribute value='${res}' />" />
</c:forEach>

<script src="${jsBase}/jquery-1.7.2.min.js" type="text/javascript" language="JavaScript"></script>
<script src="${jsBase}/jquery-ui-1.8.19.custom.min.js" type="text/javascript" language="JavaScript"></script>
<script src="${jsBase}/jquery.ui.datepicker-ca.js" type="text/javascript" language="JavaScript"></script>
<script src="${jsBase}/jquery.colorbox-min.js" type="text/javascript" language="JavaScript"></script>
<script src="${jsBase}/server.js" type="text/javascript" language="JavaScript"></script>
<script src="<c:url value="/fpm-template/namebook/js/jquery.jHelperTip.1.0.js"/>" type="text/javascript"></script>

<c:forEach items="${jsResources}" var="res">
	<script src="${jsBase}/<tiles:insertAttribute value='${res}' />" type="text/javascript" language="JavaScript"></script>
</c:forEach>

<script>
function showLoading() {
	$('#ajax-loading').fadeIn(200);
};

function hideLoading() {
	$('#ajax-loading').fadeOut(200);
}

$(function () {
	$("form").submit(function () {
		showLoading();
	});
})
</script>

</head>
<body>
	<div id="ajax-loading">
		
	</div>
	<div id="body-wrapper">
		<div id="base-body">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
</body>
<script src="${jsBase}/jquery.iframe-transport.js" type="text/javascript" language="JavaScript"></script>
<script src="${jsBase}/jquery.fileupload.js" type="text/javascript" language="JavaScript"></script>
<script src="${jsBase}/jquery.imagefit.js" type="text/javascript" language="JavaScript"></script>
<script>
$("*[tooltip]").jHelperTip({
	trigger: "hover", 
	source: "attribute", 
	attrName: "tooltip", 
	opacity: 0.9, 
	autoClose:true
});

$('#ajax-loading').hide() // hide it initially
.ajaxStart(function() {
	$(this).fadeIn(200);
}).ajaxStop(function() {
	$(this).fadeOut(200);
});
$(".input-date").datepicker();
$(function () {	
	$('.file-upload').each(function (_, object) {
		$(object).fileupload({
			url: "<c:url value='/backend/uploadFile.sdo'/>",
			dataType: 'json',
			done: function (e, data) {
				var func = window[object.getAttribute("dispatcher")];
				func(data.result);
			}
		});
		
		var parent = object.parentNode;
		$(parent).on("click", function () {
			object.click();
		});
		
		$(object).bind('fileuploadsubmit', (function (fileName) {
			return function (e, data) {
			    data.formData = {key: fileName};		    
			};
		})(object.getAttribute("fileKey")));
		
	});
});
$(window).load(function(){
    $('#imageFitClass').imagefit();
});
</script>
</html>