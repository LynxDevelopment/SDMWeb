<%@ page isELIgnored="false" %>
<%@ include file="/fpm/commons/header.xwb" %>
<xweb:setBundle baseName="FPM-commons"/>
<table class="functions" style="width:99%" cellpadding="0">
	<tr><th colspan="2"><fmt:message key="statics.event.list"/></th></tr>
</table>

<s-form:form id="staticEventList" method="post" commandName="staticEventList" action="/SDMWeb/event/securities/filter.sdo">

<table class="data" style="width:100%;border: 0px solid black;" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th><fmt:message key="name"/></th>
			<th><fmt:message key="isin"/></th>
			<th><fmt:message key="asset.type"/></th>
			<th><fmt:message key="provider"/></th>
			<th><fmt:message key="market"/></th>
		</tr>
		<c:forEach items="${staticEventList.assetList}" var="varStaticEventList">
			<tr onclick="">
				<td>
					${varStaticEventList.name}
				</td>
				<td>
					${varStaticEventList.isin}
				</td>
				<td>
					${varStaticEventList.assetType.name}
				</td>
				<td>
					${varStaticEventList.provider.name}
				</td>
				<td>
					${varStaticEventList.market.name}
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:choose>
	<c:when test="${staticEventList.listSize>0}">
		<table class="page_navigation">
			<tr>
				<td style="width:40%;">
					<dl>
						<dt><fmt:message key="dataset.pagination.found"/>:</dt>
						<dd><c:out value="${staticEventList.listSize}"/></dd>
					</dl>
				</td>
				<c:if test="${staticEventList.listSize > staticEventList.resultsPerPage and staticEventList.resultsPerPage > 0}">
					<td style="width:40%;">
						<c:choose>
							<c:when test="${staticEventList.currentPage==1}">
								<font>&laquo;</font> 
								<font>&lsaquo;</font>
							</c:when>
							<c:otherwise>
								<font>&laquo;</font>
								<font>&lsaquo;</font>
							</c:otherwise>
						</c:choose>
						<fmt:message key="dataset.pagination.pagina"/><c:out value="${staticEventList.currentPage}"/><fmt:message key="dataset.pagination.of"/> <c:out value="${staticEventList.availablePages}"/>
						<c:choose>
							<c:when test="${staticEventList.currentPage==staticEventList.availablePages}">
								<font>&rsaquo;</font>
								<font>&raquo;</font>
							</c:when>
							<c:otherwise>
								<font>&rsaquo;</font>
								<font>&raquo;</font>
							</c:otherwise>
						</c:choose>

						 	
					</td>
				</c:if> 
				<td style="text-align:right;">
					<dl>
						<dt><fmt:message key="dataset.pagination.results.page"/>:</dt>
						<dd>${staticEventList.resultsPerPage}</dd>
						<%-- <dd><wcomp:editProperty property="${staticEventList.resultsPerPage}" maxLength="2" defaultValue="${staticEventList.resultsPerPage}"/></dd> --%>
					</dl>
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<table class="page_navigation"><tr><td><dl><dt><fmt:message key="dataset.pagination.not.results.found"/></dt></dl></td></tr></table>
	</c:otherwise>
</c:choose>
</s-form:form>