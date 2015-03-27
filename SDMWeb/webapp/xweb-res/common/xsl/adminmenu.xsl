
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

<xsl:param name="context"/>

<xsl:template match="menu" >
	<table width="160" cellspacing="0" cellpadding="0" border="0">
    	<tr>
        	<td width="100%" height="20" class="clsTdMenuIntest">
            	<xsl:value-of select="@name" disable-output-escaping="yes"/>
          	</td>
       	</tr>
		  <xsl:apply-templates select="item"/>
	</table>
</xsl:template>

<xsl:template match="item">
	<xsl:choose>
		<xsl:when test="position()=last()">
      		<tr>
       			<td height="20" class="clsTdMenuLeftRightBottom">
        			<img src="{$context}/res?resource=FRCMENU"/><xsl:text> </xsl:text>
					<a href="{url}" class="clsFntMenuLink">
						<xsl:value-of select="text" disable-output-escaping="yes"/>
					</a>
				</td>
			</tr>
       	</xsl:when>
       	<xsl:otherwise >
       		<tr>
       			<td height="20" class="clsTdMenuLeftRight">
        			<img src="{$context}/res?resource=FRCMENU"/><xsl:text> </xsl:text>
					<a href="{url}" class="clsFntMenuLink">
						<xsl:value-of select="text" disable-output-escaping="yes"/>
					</a>
				</td>
			</tr>
       </xsl:otherwise>
	</xsl:choose>

</xsl:template>

</xsl:stylesheet>