<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

<xsl:template match="link" >
	<xsl:if test="(iconSource='')">
		<xsl:choose>
			<xsl:when test="(class='')">
				<a href="{href}" onClick="{onClick}" title="{title}">:: <xsl:value-of select="text" disable-output-escaping="yes"/></a>&#160;&#160;
			</xsl:when>
			<xsl:otherwise>
				<a href="{href}" class="{class}" onClick="{onClick}" title="{title}">:: <xsl:value-of select="text" disable-output-escaping="yes"/></a>&#160;&#160;
			</xsl:otherwise>
		</xsl:choose>
	</xsl:if>
	<xsl:if test="(iconSource!='')">
		<xsl:choose>
			<xsl:when test="(class='')">
				&#160;:&#160;:&#160;<a href="{href}" onClick="{onClick}" title="{title}"><img src="{iconSource}" border="0"></img><xsl:value-of select="text" disable-output-escaping="yes"/></a>&#160;&#160;
			</xsl:when>
			<xsl:otherwise>
				&#160;:&#160;:&#160;<a href="{href}" class="{class}" onClick="{onClick}" title="{title}"><xsl:value-of select="text" disable-output-escaping="yes"/></a>&#160;&#160;
			</xsl:otherwise>
		</xsl:choose>
	</xsl:if>

</xsl:template>

</xsl:stylesheet>