<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

	<xsl:param name="sessionId"/>

	<xsl:template match="tracking-report"> 
		<xsl:for-each select="tracking-info[sessionId=$sessionId]">
			<table class="detail">
				<tr>
					<th width="50%">id sessione</th>
					<th width="50%">username</th>
				</tr>
				<tr>
					<td><xsl:value-of select="sessionId"/></td>
					<td><xsl:value-of select="userID"/></td>
				</tr>
				<tr>
					<th>inizio sessione</th>
					<th>indirizzo ip</th>
				</tr>
				<tr>
					<td><xsl:value-of select="sessionStart"/></td>
					<td><xsl:value-of select="uniqueIP"/></td>
				</tr>
				<tr>
					<th>user agent</th>
					<th>browser</th>
				</tr>
				<tr>
					<td><xsl:value-of select="userAgent"/></td>
					<td><xsl:value-of select="browserName"/> versione <xsl:value-of select="browserVersion"/></td>
				</tr>
				<tr>
					<th>piattaforma</th>
					<th>risoluzione</th>
				</tr>
				<tr>
					<td><xsl:value-of select="platform"/></td>
					<td><xsl:value-of select="screenSize"/></td>
				</tr>			
			</table>
			<xsl:apply-templates select="pages"/>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template match="pages">
		<br/>
		<table class="data">
			<thead>
				<th>url</th>
				<th>timestamp</th>
				<th>referrer</th>
				<th>parametri</th>
			</thead>
			<tbody>
				<xsl:for-each select="pageInfo">
					<tr>
						<td><xsl:value-of select="pageName"/></td>
						<td><xsl:value-of select="accessTime"/></td>
						<td><xsl:value-of select="referrer"/></td>
						<td><xsl:value-of select="queryString"/></td>						
					</tr>
				</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>
		
</xsl:stylesheet>
