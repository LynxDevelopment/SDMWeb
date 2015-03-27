
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

<xsl:param name="context"/>

	<xsl:template match="root">
		<ol id="treecatbrowse">
			<xsl:apply-templates select="item"/>
		</ol>
	</xsl:template>
	
	<xsl:template match="item">
		<li id="{@id}">
			<xsl:attribute name="class">
				<xsl:if test="count(item) &gt; 0">nCollapse </xsl:if>
				<!-- xsl:if test="enabled = 'T'">itemMenuEnabled </xsl:if>
				<xsl:if test="marked = 'T'">itemMenuMarked </xsl:if-->
			</xsl:attribute>
			<!-- xsl:if test="enabled = 'F'"><xsl:attribute name="style">font-style: italic;color: #cccccc;</xsl:attribute></xsl:if>
			<xsl:if test="enabled = 'T'"><xsl:attribute name="style">font-style: normal;color: black;</xsl:attribute></xsl:if-->
			<xsl:choose>
				<xsl:when test="count(item) &gt; 0">
					<xsl:choose>
						<xsl:when test="position() = last()">
							<img class="treeImage" src="{$context}/xweb-res/images/tree/plus2.gif"/>
						</xsl:when>
						<xsl:otherwise>
							<img class="treeImage" src="{$context}/xweb-res/images/tree/plus3.gif"/>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:otherwise>
					<xsl:choose>
						<xsl:when test="position() = last()">
							<img class="treeImage" src="{$context}/xweb-res/images/tree/line2.gif"/>
						</xsl:when>
						<xsl:otherwise>
							<img class="treeImage" src="{$context}/xweb-res/images/tree/line3.gif"/>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:otherwise>
			</xsl:choose>
			<a class="elemCatItem">
				<img class="treecatImage" src="{$context}/images/icons/docs_folders_and_files/folder_closed_oblique/folder_closed_oblique_16_n_g.gif"/>
				<xsl:value-of select="name"/>
			</a>
			<!-- if menuitem has enabled children -->
			<xsl:if test="count(item) &gt; 0">
				<ol style="display: none;list-style-type: none;">
					<xsl:apply-templates select="item"/>
				</ol>
			</xsl:if>
		</li>
	</xsl:template>
</xsl:stylesheet>