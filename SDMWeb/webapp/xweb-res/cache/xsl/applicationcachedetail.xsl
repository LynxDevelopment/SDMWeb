
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />
	
	<xsl:variable name="readonly"><xsl:value-of select="CACHE/@readonly"/></xsl:variable>

	<xsl:template match="CACHE">

		<table class="detail">
			<tr>
				
				<th width="50%">Nome cache</th>
				<th width="50%">Stato</th>
			</tr>  
			<tr>
				<td><xsl:value-of select="@NAME"/></td>
				<td> 
					<xsl:choose>
						<xsl:when test="@enabled='true'">abilitata</xsl:when>
						<xsl:otherwise>disabilitata</xsl:otherwise>
					</xsl:choose>
				</td>
			</tr>
			<tr>
				<th>Numero oggetti</th>
				<th>Dimensione totale</th>
			</tr>
			<tr>
				<td><xsl:value-of select="@KEYSNUM"/></td>
				<td>
					<xsl:choose>
						<xsl:when test="number(@SIZE) &gt; 1000">
							<xsl:value-of select="format-number(number(@SIZE div 1024), '#.00')"/> KBytes
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="number(@SIZE)"/> Bytes
						</xsl:otherwise>
					</xsl:choose>
				</td>
			</tr>
			<tr>
				<th>Numero hits</th>
				<th>Numero miss</th>
			</tr>
			<tr>		
				<td><xsl:value-of select="@HITS"/></td>
				<td><xsl:value-of select="@MISS"/></td>
			</tr>
		</table>
		<br/>
 		
 		<table class="data">
			<thead>
	          <tr>
	          	<th width="3%">N.</th>
				<th style="text-align: center;" width="5%">
					<input type="checkbox" name="CheckAll" value="" onClick="ToggleCheckAll(document.formDelete.object, this);">
						<xsl:if test="$readonly = 'true'">
							<xsl:attribute name="disabled">true</xsl:attribute>
						</xsl:if>					
					</input>
				 </th>
	             <th width="35%">CHIAVE</th>
	             <th width="30%" style="text-align: right;">DIMENSIONE OGGETTO</th>
	             <th width="30%">SCADE IN</th>
			</tr>
            </thead>
			<tbody>
				<xsl:apply-templates select="KEYS"/>
			</tbody>
		</table>
	</xsl:template>

<xsl:template match="KEYS">
		<xsl:apply-templates select="KEY"/>
</xsl:template>

<xsl:template match="KEY">
	<tr>
		<td><xsl:value-of select="INDEX" /></td>
		<td style="text-align: center;">
				   <input type="checkbox" name="object" value="{.}">
					<xsl:if test="$readonly = 'true'">
						<xsl:attribute name="disabled">true</xsl:attribute>
					</xsl:if>
				   </input>
		</td>
		<td>
			<xsl:value-of select="."/> 
		</td>
		<td style="text-align: right;">
			<xsl:choose>
				<xsl:when test="number(@SIZE) &gt; 1000">
					<xsl:value-of select="format-number(number(@SIZE div 1024), '#.00')"/> KBytes
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="number(@SIZE)"/> Bytes
				</xsl:otherwise>
			</xsl:choose>
		</td>
		<td>
			<xsl:choose>
				<xsl:when test="@EXPIRES=''">Nessuna scadenza</xsl:when>
				<xsl:when test="@EXPIRES='expired' or floor(number(@EXPIRES) div 1000) = 0">scaduto</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="formatDelay">
						<xsl:with-param name="delay" select="@EXPIRES"/>
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
		</td>		
	</tr>
</xsl:template>

<xsl:template name="formatDelay">
		<xsl:param name="delay"/>
		<xsl:variable name="days" select="floor(number($delay) div 86400000)"/>
		<xsl:if test="$days &gt; 1">
			<xsl:value-of select="format-number($days, '#')"/>
			<xsl:choose>
				<xsl:when test="$days &lt; 2"><xsl:text>&#160;giorno&#160;</xsl:text></xsl:when>
				<xsl:otherwise><xsl:text>&#160;giorni,&#160;</xsl:text></xsl:otherwise>
			</xsl:choose>					
		</xsl:if>
		
		<xsl:variable name="hours" select="floor(number($delay) div 3600000) mod 24"/>
		<xsl:if test="$hours &gt; 1">
			<xsl:value-of select="format-number($hours, '#')"/>
			<xsl:choose>
				<xsl:when test="$hours &lt; 2"><xsl:text>&#160;ora&#160;</xsl:text></xsl:when>
				<xsl:otherwise><xsl:text>&#160;ore,&#160;</xsl:text></xsl:otherwise>
			</xsl:choose>
		</xsl:if>
		
		<xsl:variable name="minutes" select="floor(number($delay) div 60000) mod 60"/>
		<xsl:if test="$minutes &gt; 0">
			<xsl:value-of select="format-number($minutes, '#')"/>
			<xsl:choose>
				<xsl:when test="$minutes &lt; 2"><xsl:text>&#160;minuto&#160;</xsl:text></xsl:when>
				<xsl:otherwise><xsl:text>&#160;minuti,&#160;</xsl:text></xsl:otherwise>
			</xsl:choose>
		</xsl:if>
		
		<xsl:variable name="seconds" select="floor(number($delay) div 1000) mod 60"/>
		<xsl:if test="$seconds &gt; 0">
			<xsl:value-of select="format-number($seconds, '#')"/>
			<xsl:choose>
				<xsl:when test="$seconds &lt; 2"><xsl:text>&#160;secondo</xsl:text></xsl:when>
				<xsl:otherwise><xsl:text>&#160;secondi</xsl:text></xsl:otherwise>
			</xsl:choose>
		</xsl:if>
</xsl:template>

</xsl:stylesheet>
