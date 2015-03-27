<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

	<xsl:template match="resultset" >
		<select name="WFS.WF_STATUS_ID" size="1" class="clsSelect">
			<option name="WFS.WF_STATUS_ID"  value="" disable-output-escaping="yes"></option>
			<xsl:apply-templates select="row" />
		</select>
	</xsl:template>

	<xsl:template match="row">
		<option name="WFS.WF_STATUS_ID"  value="{WF_STATUS_ID}" disable-output-escaping="yes">
			<xsl:value-of select="DESCRIPTION" disable-output-escaping="yes"/>
		</option>
	</xsl:template>
</xsl:stylesheet>