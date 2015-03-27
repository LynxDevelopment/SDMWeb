<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />
		    
	<xsl:template match="tracking-report"> 
		<table class="data">
			<thead>
				<tr>
					<th>username</th>
					<th>inizio sessione</th>
					<th>indirizzo ip</th>
					<th>pagine visitate</th>
				</tr>
			</thead>
			<tbody>
				<xsl:for-each select="tracking-info">
							<tr>
								<td>
									<a href="tracking_detail.xwb?sessionId={sessionId}">								
										<xsl:value-of select="userID"/>
									</a>
								</td>
								<td><xsl:value-of select="sessionStart"/></td>
								<td><xsl:value-of select="uniqueIP"/></td>
								<td><xsl:value-of select="count(pages/pageInfo)"/></td>
							</tr>					
				</xsl:for-each>
			</tbody>				
		</table>
	</xsl:template>
		
</xsl:stylesheet>
