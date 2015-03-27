<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />
		    
	<xsl:param name="context"/>
		<xsl:template match="root">
			<table class="modules" border="1">
				<xsl:for-each select="item[position() mod 2 = 1]">
					<tr>
						<!-- first column -->
						<td width="1%">
							<xsl:variable name="imgpath">
								<xsl:choose>
									<xsl:when test="contains(icon1, 'null') or (string-length(icon1) = 0)">
										<xsl:value-of select="concat($context,'/images/icons/windows_and_views/window/window_32_n_g.gif')"/>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="concat($context, icon1)"/>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:variable>
							
							<a href="{url}"><img src="{$imgpath}" alt="{title}" align="top" border="0"/></a>
						</td>
						<td>
							<a href="{url}"><xsl:value-of select="title" disable-output-escaping="yes"/></a>
							<xsl:value-of select="description" disable-output-escaping="yes" />	
						</td>
						<td>&#160;</td>
						<!-- last column -->
						<xsl:variable name="new_item" select="following-sibling::item"/>
						<xsl:if test="$new_item">
							<td width="1%">
								<xsl:variable name="new_imgpath">
									<xsl:choose>
										<xsl:when test="contains($new_item/icon1, 'null') or (string-length($new_item/icon1) = 0)">
											<xsl:value-of select="concat($context,'/images/icons/windows_and_views/window/window_32_n_g.gif')"/>
										</xsl:when>	
										<xsl:otherwise>
											<xsl:value-of select="concat($context, $new_item/icon1)"/>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:variable>
								
								<a href="{$new_item/url}"><img src="{$new_imgpath}" alt="{$new_item/title}" align="top" border="0"/></a>
							</td>
							<td>
								<a href="{$new_item/url}"><xsl:value-of select="$new_item/title" disable-output-escaping="yes"/></a>
								<xsl:value-of select="$new_item/description" disable-output-escaping="yes" />	
							</td>
						</xsl:if>
					</tr>
				</xsl:for-each>
				
			</table>
			 
		</xsl:template>
		
</xsl:stylesheet>
