<%@ page isELIgnored="false" %>
<%@ include file="/fpm/commons/header.xwb" %>

<table class="functions" style="width:99%" cellpadding="0">
	<tr><th colspan="2"><fmt:message key="security.detail"/></th></tr>
</table>

<table class="sdmDetailClass" style="width: 99%">
	<tr><td class="subtitleClass"><fmt:message key="message.details"/></td></tr>
	<tr>
		<td style="padding:0px;">
			<table class="blueDetailClass" >
				<tr >
					<th><fmt:message key="name"/></th><td>${messageSecurity.securityName}</td>
					<th><fmt:message key="isin"/></th><td>${messageSecurity.securityIsin}</td>
				</tr>
				<tr >
					<th><fmt:message key="mic"/></th><td>${messageSecurity.securityMic}</td>
					<th><fmt:message key="ticker"/></th><td>${messageSecurity.securityTicker}</td>
				</tr>
				<tr >
					<td colspan="4"> </td>
				</tr>
				<c:set var="index" value="0" />
				<c:forEach items="${messageValues}" var="messageSecurity">
					<c:if test="${index%2==0}">
						<tr>
					</c:if>
							<th>${messageSecurity.jobField.name}</th><td>${messageSecurity.value}</td>
					<c:if test="${index%2==1}">
						</tr>
					</c:if>
					<c:set var="index" value="${index+1}" />
				</c:forEach>
			</table>
		</td>
	</tr>
</table>

<table class="functions buttonsBarClass" style="width: 99%" cellpadding="0">
	<tr>
		<td class="buttonsBarClass">
			<a class="boton" href="javascript:history.back();"><fmt:message key="back" /></a> 
			<%-- <a class="boton" href="javascript:openCenteredWindow('<c:url value="/message/securities/originalMessage.sdo?messageId=${param.messageId}' />';"> --%>
			<a class="boton" title="<fmt:message key="help"/>" href="javascript:openCenteredWindow('<c:url value="/message/securities/originalMessagePopUp.sdo?messageId=${messageSecurity.id}"/>','OriginalMessage',900,420);">
				<fmt:message key="original.message" />
			</a>
		</td>
	</tr>
</table>