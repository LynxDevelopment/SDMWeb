
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

<xsl:template match="link" >
	<xsl:if test="text">
		<a href="{href}" onClick="{onClick}" title="{title}">
			<xsl:if test="class != ''">
				<xsl:attribute name="class"><xsl:value-of select="class"/></xsl:attribute>
			</xsl:if>
			<xsl:choose>
				<xsl:when test="(iconSource='')">:: <xsl:value-of select="text" disable-output-escaping="yes"/></xsl:when>
				<xsl:otherwise><img src="{iconSource}" border="0" alt="{text}"/></xsl:otherwise>
			</xsl:choose>
		</a>&#160;&#160;
	</xsl:if>
</xsl:template>

</xsl:stylesheet>