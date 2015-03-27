
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

	<xsl:template match="resultset">
			  <xsl:apply-templates select="row"/>
	</xsl:template>
	
	<xsl:template match="row">
		<table class="detail">
			<tr>
			   <th width="50%">SOGGETTO</th>
			   <th width="50%">DATA MESSAGGIO</th>
			</tr>
			<tr>
				<td>
					<xsl:value-of select="SUBJECT" disable-output-escaping="yes"/>
				</td>
				<td>
					<xsl:value-of select="NOTE_DATE" disable-output-escaping="yes"/>
				</td>
			</tr>
			<tr>
				<th colspan="2">CONTENUTO</th>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<xsl:value-of select="NOTE" disable-output-escaping="yes"/>&#160;
				</td>
			</tr>
			<tr>
			   <th>DATA DI PUBBLICAZIONE</th>
			   <th>DATA DI SCADENZA</th>
			</tr>
			<tr>
				<td>
					<xsl:value-of select="PUBLISH_DATE" disable-output-escaping="yes"/>
				</td>
				<td>
					<xsl:value-of select="EXPIRATION_DATE" disable-output-escaping="yes"/>
				</td>
			</tr>
			<tr>
				<th colspan="2">STATO</th>
			</tr>
			<tr>
				<td colspan="2">
					<xsl:value-of select="DESCRIPTION" disable-output-escaping="yes"/>
				</td>
			</tr>
		</table>
	</xsl:template>



</xsl:stylesheet>
