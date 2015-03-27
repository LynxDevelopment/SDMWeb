
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />


<xsl:param name="context"/>

<!-- variabili generali -->
<xsl:variable name="noRecord">Non sono stati trovati record che soddisfano i criteri di ricerca</xsl:variable>
<xsl:variable name="redirect" select="//toolbar/redirect" />

<!-- fine variabili generali -->



<!-- template per il link -->
<xsl:template name="link">
	<xsl:param name="offset"></xsl:param>
	<xsl:param name="image"></xsl:param>
	<xsl:variable name="moreparameters">
		<xsl:if test="moreparameter != ''">
			&amp;<xsl:value-of select="moreparameter"/>
		</xsl:if>
	</xsl:variable>
	<xsl:variable name="separator">
		<xsl:choose>
			<xsl:when test="contains($redirect,'?')">&amp;</xsl:when>
			<xsl:otherwise>?</xsl:otherwise>
		</xsl:choose>
	</xsl:variable>

	<a href='{$redirect}{$separator}offset={$offset}&amp;{sqlparamname}={encodedsql}{$moreparameters}'>
		<img src="{$context}/res?resource={$image}" border="0" align="absmiddle"></img>
	</a>
</xsl:template>
<!-- fine template per il link -->


	<xsl:template match="toolbar" >
		<xsl:choose>
			<xsl:when test="(recordcount=0)">
				<xsl:value-of select="$noRecord" disable-output-escaping="yes"/>
			</xsl:when>
			<xsl:otherwise>
				<table class="page_navigation">
					<tr>
						<td>
							<dl>
								<dt>Trovati:</dt>
								<dd><xsl:value-of select="recordcount" disable-output-escaping="yes"/></dd>
							</dl>
						</td>
						<td style="text-align:right;">
							<!-- primo bottone -->
							<xsl:call-template name="link">
								<xsl:with-param name="offset">1</xsl:with-param>
								<xsl:with-param name="image">PREVIOUSEND</xsl:with-param>
							</xsl:call-template>
							<!-- secondo bottone -->
							<xsl:call-template name="link">
								<xsl:with-param name="offset">
									<xsl:value-of select="previouspage * length + 1"/>
								</xsl:with-param>
								<xsl:with-param name="image">PREVIOUS</xsl:with-param>
							</xsl:call-template>
					<!-- pagina x di y -->
					pag <xsl:value-of select="currentpage + 1"/> di <xsl:value-of select="lastpage + 1" disable-output-escaping="yes"/>
							<!-- terzo bottone -->
							<xsl:call-template name="link">
								<xsl:with-param name="offset">
									<xsl:value-of select="length * nextpage + 1" disable-output-escaping="yes"/>
								</xsl:with-param>
								<xsl:with-param name="image">NEXT</xsl:with-param>
							</xsl:call-template>
							<!-- quarto bottone -->
							<xsl:call-template name="link">
								<xsl:with-param name="offset">
									<xsl:value-of select="length * lastpage + 1" disable-output-escaping="yes"/>
								</xsl:with-param>
								<xsl:with-param name="image">NEXTEND</xsl:with-param>
							</xsl:call-template>
						</td>
			  		</tr>
				</table>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>