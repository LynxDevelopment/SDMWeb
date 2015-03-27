<%@ page isELIgnored="false" %>
<%@ include file="/fpm/commons/header.xwb" %>

<table class="functions" style="width:99%" cellpadding="0">
	<tr><th colspan="2"><fmt:message key="security.detail"/></th></tr>
</table>

	<wcomp:dataset id="varMessageDetailDataset" stateful="false" query="from SDMRow where id=?" dataSource="hibernateXWeb" lifeCycle="page">
		<wcomp:queryParam value="${param.securityId}" defaultValue="0"/>
	</wcomp:dataset>
	<wcomp:datatable dataset="varMessageDetailDataset" style="width:100%;" cellpadding="0" cellspacing="0">
		
	</wcomp:datatable>
<br/>

<table class="functions buttonsBarClass" style="width: 99%" cellpadding="0">
	<tr>
		<td class="buttonsBarClass"><a class="boton"
			href="javascript:history.back();"><fmt:message key="back" /></a> <xweb:hasUserFunction
				moduleName="sdm" functionName="PARSEDMESSAGEVIEW">
				<a class="boton"
					href="javascript:openCenteredWindow('<c:url value="/fpm/sdm/support/message/parsedMessagePopUp.xwb?messageId=${param.messageId}&messagesEntity=${param.messagesEntity}"/>', 'parsedMessage${param.messageId}', 1000, 640);"><fmt:message
						key="parsed.message" /></a>
			</xweb:hasUserFunction> <a class="boton"
			href="javascript:openCenteredWindow('<c:url value="/fpm/sdm/support/message/originalMessagePopUp.xwb?messageId=${param.messageId}&messagesEntity=${param.messagesEntity}"/>', 'originalMessage${param.messageId}', 1040, 640,'scrollbars=yes,resizable=no');"><fmt:message
					key="original.message" /></a></td>
	</tr>
</table>