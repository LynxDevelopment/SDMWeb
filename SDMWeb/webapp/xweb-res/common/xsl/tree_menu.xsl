
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

<xsl:param name="context"/>

	<xsl:template match="root">
		<ul>
			<xsl:apply-templates select="item"/>
		</ul>
	</xsl:template>
	
	<xsl:template match="item">
		<xsl:if test="enabled = 'T'">
			<li>
				<form name="form_menu_{@id}" style="display: inline;" action="{url}" method="POST">
					<input type="hidden" name="expandId" value="{@id}"/>
					<a href="{url}" onclick="document.form_menu_{@id}.submit(); return false;">
						<xsl:if test="@expand = 'true'">
							<xsl:attribute name="class">selected</xsl:attribute>
						</xsl:if>
						<xsl:value-of select="title"/>
					</a>
				</form>
				
				<!-- if menuitem has enabled children -->
				<xsl:if test="count(item[enabled='T']) &gt; 0">
					<ul>
						<xsl:apply-templates select="item"/>
					</ul>
				</xsl:if>
			</li>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>