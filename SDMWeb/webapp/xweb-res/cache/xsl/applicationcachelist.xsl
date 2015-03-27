
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

<xsl:template match="CACHE_MANAGER">
				<xsl:apply-templates select="CACHE"/>
</xsl:template>

<xsl:template match="CACHE">
	<tr height="20">
		<td><xsl:value-of select="INDEX" /></td>
		<td style="text-align: center;">
			<input type="checkbox" name="cache" value="{NAME}">
				<xsl:if test="@readonly = 'true'">
					<xsl:attribute name="disabled">true</xsl:attribute>
				</xsl:if>
			</input>
		</td>
		<td>
			<a>
				<xsl:attribute name="href">cachedetail.xwb?cachename=<xsl:value-of select="NAME"/></xsl:attribute>
				<xsl:value-of select="NAME" disable-output-escaping="yes"/>
			</a>
		</td>
		<td style="text-align: right;">
			<xsl:choose>
				<xsl:when test="number(SIZE) &gt; 1000">
					<xsl:value-of select="format-number(number(SIZE div 1024), '#.00')"/> KB
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="number(SIZE)"/> Bytes
				</xsl:otherwise>
			</xsl:choose>
		</td>
		<td>
			<xsl:variable name="hitRatio">
				<xsl:choose>
					<xsl:when test="HITS = 0"><xsl:value-of select="number('0')"/></xsl:when>
					<xsl:otherwise><xsl:value-of select="HITS div (HITS + MISS)"/></xsl:otherwise>
				</xsl:choose>
			</xsl:variable>

			<xsl:variable name="barColor">
				<xsl:call-template name="hsv2rgb">
					<xsl:with-param name="h" select="$hitRatio * 2"/>
					<xsl:with-param name="s" select="1"/>
					<xsl:with-param name="v" select="1"/>
				</xsl:call-template>
			</xsl:variable>
			<div style="border: 1px solid black; height: 12px; padding: 0px;" title="{floor($hitRatio * 100)}%" >
				<div style="background-color:{$barColor}; width: {$hitRatio * 100}%; height: 100%;"></div>
			</div>
		</td>
		<td style="text-align: center;">
			<input type="checkbox" name="cacheEnable" value="{NAME}">
				<xsl:if test="@enabled = 'true'">
					<xsl:attribute name="checked">true</xsl:attribute>
				</xsl:if>
				<xsl:if test="@readonly = 'true'">
					<xsl:attribute name="disabled">true</xsl:attribute>
				</xsl:if>
			</input>
		</td>
	</tr>
</xsl:template>

<xsl:template name="hsv2rgb">
	<xsl:param name="h"/>
	<xsl:param name="s"/>
	<xsl:param name="v"/>

	<xsl:variable name="i" select="floor($h)"/>
	<xsl:variable name="f">
		<xsl:choose>
			<xsl:when test="$i mod 2 = 0">
				<xsl:value-of select="$i + 1 - $h"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$h - $i"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:variable>

	<xsl:variable name="m" select="$v * (1 - $s)"/>
	<xsl:variable name="n" select="$v * (1 - $s * $f)"/>

	<xsl:choose>
		<xsl:when test="$i = 0 or $i = 6">rgb(<xsl:value-of select="round($v * 255)"/>, <xsl:value-of select="round($n * 255)"/>, <xsl:value-of select="round($m * 255)"/>)</xsl:when>
		<xsl:when test="$i = 1">rgb(<xsl:value-of select="round($n * 255)"/>, <xsl:value-of select="round($v * 255)"/>, <xsl:value-of select="round($m * 255)"/>)</xsl:when>
		<xsl:when test="$i = 2">rgb(<xsl:value-of select="round($m * 255)"/>, <xsl:value-of select="round($v * 255)"/>, <xsl:value-of select="round($n * 255)"/>)</xsl:when>
		<xsl:when test="$i = 3">rgb(<xsl:value-of select="round($m * 255)"/>, <xsl:value-of select="round($n * 255)"/>, <xsl:value-of select="round($v * 255)"/>)</xsl:when>
		<xsl:when test="$i = 4">rgb(<xsl:value-of select="round($n * 255)"/>, <xsl:value-of select="round($m * 255)"/>, <xsl:value-of select="round($v * 255)"/>)</xsl:when>
		<xsl:when test="$i = 5">rgb(<xsl:value-of select="round($v * 255)"/>, <xsl:value-of select="round($m * 255)"/>, <xsl:value-of select="round($n * 255)"/>)</xsl:when>
	</xsl:choose>

</xsl:template>
</xsl:stylesheet>