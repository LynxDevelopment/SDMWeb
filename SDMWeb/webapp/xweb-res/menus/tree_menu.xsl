
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

<xsl:param name="context"/>

	<xsl:template match="root">
		<ol id="treemenu">
			<xsl:apply-templates select="item"/>
		</ol>
	</xsl:template>
	
	<xsl:template match="item">
		<li id="{@id}">
			<xsl:attribute name="class">
				<xsl:if test="count(item) &gt; 0 and itemType != 'ADMIN'">nCollapse </xsl:if>
				<xsl:if test="enabled = 'T'">itemMenuEnabled </xsl:if>
				<xsl:if test="marked = 'T'">itemMenuMarked </xsl:if>
				<xsl:if test="itemType = 'CATEGORY'">categoryItem </xsl:if>
			</xsl:attribute>
			<xsl:if test="enabled = 'F'"><xsl:attribute name="style">font-style: italic;color: #cccccc;</xsl:attribute></xsl:if>
			<xsl:if test="enabled = 'T'"><xsl:attribute name="style">font-style: normal;color: black;</xsl:attribute></xsl:if>
			<xsl:choose>
				<xsl:when test="count(item) &gt; 0 and itemType != 'ADMIN'">
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
			<a class="elemMenuItem">
				<xsl:choose>
					<xsl:when test="enabled = 'T'">
						<img class="elemMenuItem" src="{$context}/xweb-res/images/tree/menuitem/itemactive.gif"/>
					</xsl:when>
					<xsl:otherwise>
						<img class="elemMenuItem" src="{$context}/xweb-res/images/tree/menuitem/itemdisabled.gif"/>
					</xsl:otherwise>
				</xsl:choose>				
				<xsl:value-of select="title"/>
			</a>
			
			<!-- if menuitem has enabled children -->
			<xsl:if test="count(item) &gt; 0 and itemType != 'ADMIN'">
				<ol style="display: none;list-style-type: none;">
					<xsl:apply-templates select="item"/>
				</ol>
			</xsl:if>
		</li>
	</xsl:template>
</xsl:stylesheet>