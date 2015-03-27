<%@ page isELIgnored="false" %>
<%@ include file="/fpm/commons/header.xwb" %>

<table class="functions" style="width:99%" cellpadding="0">
	<tr><th colspan="2"><fmt:message key="message.security.original"/></th></tr>
</table>

<table class="sdmDetailClass" style="width: 99%">
	<tr><td class="subtitleClass"><fmt:message key="message.origin"/></td></tr>
	<tr>
		<td style="padding:0px;">
			<table class="blueDetailClass" >
				<tr >
					<td><textarea rows="10" cols="120" disabled="disabled">${originalMessage}</textarea></td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<br/>
<table class="functions buttonsBarClass" style="width:99%" cellpadding="0">
	<tr>
		<td class="buttonsBarClass">
			<a class="classButton" href="javascript:window.close();"><fmt:message key="close"/></a>
		</td>
	</tr>
</table>