<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

<xsl:template match="menu" >
	<ul>
		<xsl:apply-templates/>
	</ul>
</xsl:template>

<xsl:template match="item">
	<li><a href="{url}"><xsl:value-of select="text"/></a></li>
</xsl:template>

</xsl:stylesheet>