<%@ page isELIgnored="false" %>
<%@ include file="/fpm/commons/header.xwb" %>	
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<xweb:setBundle baseName="FPM-commons"/>
 <% 
 int pageSize = (Integer)request.getAttribute("pageSize");
 String sPageSize = (String)request.getAttribute("sPageSize");
 %>
	<%-- <table class="functions" style="width:99%" cellpadding="0">
		<tr><th colspan="2"><fmt:message key="securities.group"/></th></tr>
	</table> --%>
	
	<%-- <wcomp:form action="/event/securities/listEventsGroup.sdo" id="messageSecuritiesListFilterForm" lifeCycle="page"> --%>
	
	<table class="functions" style="width:99%" cellpadding="1">
		<tr>
			<th colspan="2"><fmt:message key="securities.group"/></th>
		</tr>
		<!-- <tr>
			<td> -->
	</table>
	<form:form id="GroupBean" method="post" action="/SDMWeb/event/securities/listEventsGroup.sdo" commandName="GroupBean"> 
				<table class="tableFilterClass" style="width:99%" border="0">
					<tr>
						<th style="font-weight:bold;"><fmt:message key="isin"/>:</th>
						<td>
							<form:input id="isin" path="isin" type="text" value=""/>
							<%-- <input type="button" class="searchButton" value="" onclick="javascript:openModalCenteredWindow('<c:url value="/fpm/sdm/support/security/securitySearchPopUp.xwb"/>', 'popupObj', 1054, 650, 'resizable=1, scrollbars=1');"/>
							<input type="button" class="clearButton" value="" onclick="javascript:resetValues(Array('securityId','isin'));"/> --%>
						</td>
						<th style="font-weight:bold;"><fmt:message key="name"/>:</th>
						<td>
							<form:input id="name" path="name" type="text" value=""/>						
						</td>
					</tr>				
				
					<tr>			
						<th style="font-weight:bold;"><fmt:message key="cusip"/></th>
						<td>
							<form:input id="cusip" path="cusip" type="text" value=""/>						
						</td>
						<th style="font-weight:bold;"><fmt:message key="provider"/>:</th>
						<td>
							<form:input id="provider" path="provider" type="text" value=""/>						
						</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
<%-- 						<td valign="middle" align="right" style="margin-top:5px;">   
						  <a class="clearButton boton" href="javascript:resetValues(Array('securityId','isin','eventType','eventProvider','eventsCollectedFilterForm_components_fromEffectiveDate','eventsCollectedFilterForm_components_toEffectiveDate','eventsCollectedFilterForm_components_fromOperationalDate','eventsCollectedFilterForm_components_toOperationalDate'));"><fmt:message key="clear"/></a>                        
                        </td> --%>
                        <td valign="middle" align="right" style="margin-top:5px">
							<%-- <wcomp-ext:submitForm styleClass="classButton searchImg" href="/SDMWeb/event/securities/listEventsGroup.sdo"><fmt:message key="filter"/></wcomp-ext:submitForm> --%>
							<input type="button" class="classButton searchImg" value="clear" onclick="this.form.reset()"/>
							<input type="submit" class="classButton searchImg" value="filter" />
						</td>
					</tr>
				</table>
<!-- 			</td>
		</tr>
	</table> -->
	<%-- <wcomp-ext:displayFormErrors showMode="title" styleClass="sdmErrorClass" /> --%>
<%-- </wcomp:form> --%>
    </form:form>	

	
	<display:table name="eventGroupList" id="group" pagesize="<%=pageSize%>" requestURI="/event/securities/listEventsGroup.sdo" class="data" defaultsort="1" defaultorder="descending">
    	<display:column property="isin" title="ISIN" sortable="true" group="1"/>
        <display:column property="name" title="Name" sortable="true" group="2"/>
        <display:column property="cusip" title="CUSIP" sortable="true" group="3"/>
        <display:column property="provider.name" title="Provider" sortable="true" group="4"/>
        <display:setProperty name="paging.banner.placement" value="bottom" />
        <display:setProperty name="paging.banner.item_name" value="result" />
        <display:setProperty name="paging.banner.items_name" value="results" />
        <display:setProperty name="paging.banner.full"  value="<span style=\"padding-left:33%;width: 33%;text-align: center;\"> <a style=\"text-decoration:none;\" href=\"{1}\"><font style=\"font-size: 18px;color: #FF8300;\">&laquo;</font></a> <a style=\"text-decoration:none;\" href=\"{2}\"><font style=\"font-size: 18px;color: #FF8300;\">&lsaquo;</font></a> page {5} of {6} <a style=\"text-decoration:none;\" href=\"{3}\"><font style=\"font-size: 18px;color: #FF8300;\">&rsaquo;</font></a> <a style=\"text-decoration:none;\" href=\"{4}\"><font style=\"font-size: 18px;color: #FF8300;\">&raquo; </font></a></span>" />
        <display:setProperty name="paging.banner.all_items_found" value="<div class=\"pn\" style=\"border: 1px solid #cbcbcb;padding-top:5px;padding-bottom:5px;margin-top: 15px;font-weight: bolder;color: #747474;padding-left: 5px;padding-right: 10px;font-family: arial;font-size: 12px;text-align:center;\"> {0} {1} found, displaying all {2}. </div>" />
        <display:setProperty name="paging.banner.some_items_found" value="<div class=\"pn\" style=\"border: 1px solid #cbcbcb;padding-top:5px;padding-bottom:5px;margin-top: 15px;font-weight: bolder;color: #747474;padding-left: 5px;padding-right: 10px;font-family: arial;font-size: 12px;\"><span style=\"width: 33%;\"> found: {0} </span><span style=\"float:right;text-align: right;padding-top:4px;\"> results per page: 10</span>" />
        <display:setProperty name="paging.banner.first" value="<span style=\"padding-left:35%;width: 33%;text-align: center;\"> <font style=\"font-size: 18px;\">&laquo;</font><font style=\"font-size: 18px;\"> &lsaquo;</font> page {5} of {6} <a style=\"text-decoration:none;\" href=\"{3}\"><font style=\"font-size: 18px;color: #FF8300;\">&rsaquo;</font></a>  <a style=\"text-decoration:none;\" href=\"{4}\"><font style=\"font-size: 18px;color: #FF8300;\">&raquo;</font></a> </span></div>" />
        <display:setProperty name="paging.banner.last" value="<span style=\"padding-left:35%;width: 33%;text-align: center;\"> <a style=\"text-decoration:none;\" href=\"{1}\"><font style=\"font-size: 18px;color: #FF8300;\">&laquo;</font></a> <a style=\"text-decoration:none;\" href=\"{2}\"><font style=\"font-size: 18px;color: #FF8300;\">&lsaquo;</font></a> page {5} of {6} <font style=\"font-size: 18px;\">&rsaquo; </font> <font style=\"font-size: 18px;\">&raquo; </font></span>" />
        <display:setProperty name="paging.banner.page.selected" value="" />
        <%-- <display:setProperty name="paging.banner.page.selected" value="<strong>{0}</strong>" /> --%>
        <display:setProperty name="paging.banner.page.link" value="" />
        <%-- <display:setProperty name="paging.banner.page.link" value="<a href=\"{1}\" title=\"Go to page {0}\">{0}</a>" /> --%>
    </display:table>

