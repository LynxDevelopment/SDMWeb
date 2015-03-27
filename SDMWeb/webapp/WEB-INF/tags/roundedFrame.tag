<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="style" required="false" %>
<%@ attribute name="contentStyle" required="false" %>
<c:if test="${!empty title}">
	<div class="titulos">${title}</div>
</c:if>
<table cellspacing="0" cellpadding="0" class="roundedFrame" style="${style}">
	<tbody>
		<tr>
		    <td class="tl_corner">&nbsp;</td>
		    <td class="top_frame">&nbsp;</td>
		    <td class="tr_corner">&nbsp;</td>
		</tr>
		<tr>
		    <td class="left_frame">&nbsp;</td>
		    <td style="${contentStyle}"><jsp:doBody/></td>
	    	<td class="right_frame">&nbsp;</td>
		</tr>
		<tr>
		    <td class="bl_corner">&nbsp;</td>
		    <td class="bottom_frame">&nbsp;</td>
		    <td class="br_corner">&nbsp;</td>
		</tr>
	</tbody>
</table>