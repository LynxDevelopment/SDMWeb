
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

<xsl:param name="context"/>

	<xsl:template match="root">
		<div id="treecat">
			<ul>
				<xsl:apply-templates select="item"/>
			</ul>
		</div>
	</xsl:template>
	
	<xsl:template match="item">
		<li id="{@id}">
			<xsl:if test="count(item) &gt; 0">
				<img class="treeImage" src="{$context}/xweb-res/images/tree/plus.gif"/>
			</xsl:if>
			<a>
				<img src="{$context}/images/icons/docs_folders_and_files/folder_closed_oblique/folder_closed_oblique_16_n_g.gif"/>
				<xsl:value-of select="name"/>
			</a>
			
			<!-- if menuitem has enabled children -->
			<xsl:if test="count(item) &gt; 0">
				<ul style="display: none;list-style-type: none;">
					<xsl:apply-templates select="item"/>
				</ul>
			</xsl:if>
		</li>
	</xsl:template>
</xsl:stylesheet>