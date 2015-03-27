<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />


	<xsl:template match="resultset">
		<table class="data">
			<thead>
			<tr>
				<th>DESTINATARI</th>
			</tr>
			</thead>
			<tbody>
				<xsl:apply-templates select="row"/>
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="row">
		<tr>
			<td>
				<xsl:value-of select="USER_ID" />&#160;&#160;-&#160;&#160;<xsl:value-of select="LASTNAME" />&#160;<xsl:value-of select="FIRSTNAME" />
			</td>
		</tr>
	</xsl:template>



</xsl:stylesheet>			