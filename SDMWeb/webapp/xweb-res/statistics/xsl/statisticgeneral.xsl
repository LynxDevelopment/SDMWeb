
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

    <xsl:template match="GENERAL_STATS">
		<table class="detail">
			<tr>
				<th colspan="2">DATA INIZIO STATISTICHE</th>
			</tr>
			<tr>
				<td colspan="2"><xsl:value-of select="attribute::STARTING_DATE" disable-output-escaping="yes"/>&#160;</td>
			</tr>
			<tr>
				<th colspan="2">ACCESSI TOTALI</th>
			</tr>
			<tr>
				<td colspan="2"><xsl:value-of select="TOT_VISITORS" disable-output-escaping="yes"/>&#160;</td>
			</tr>
			<tr>
				<th colspan="2">NUMERO MEDIO DI ACCESSI GIORNALIERI</th>
			</tr>
			<tr>
				<td colspan="2"><xsl:value-of select="AVG_VISITORS_PER_DAY" disable-output-escaping="yes"/>&#160;</td>
			</tr>
			<tr>
				<th colspan="2">DURATA MEDIA DELLA SESSIONE</th>
			</tr>
			<tr>
				<td colspan="2"><xsl:value-of select="AVG_SESSION_TIME" disable-output-escaping="yes"/>&#160;minuti</td>
			</tr>
			<tr>
				<th colspan="2">NUMERO MEDIO DI PAGINE VISITATE DAGLI UTENTI</th>
			</tr>
			<tr>
				<td colspan="2"><xsl:value-of select="AVG_PAGES_PER_USER" disable-output-escaping="yes"/>&#160;</td>
			</tr>
			<tr>
				<th width="50%">MESE CON PIU' ACCESSI</th>
				<th width="50%">NUMERO ACCESSI</th>
			</tr>
			<tr>
				<td><xsl:value-of select="MAX_USERS_MONTH" disable-output-escaping="yes"/>&#160;</td>
				<td><xsl:value-of select="MAX_USERS_IN_MONTH" disable-output-escaping="yes"/>&#160;</td>
			</tr>
			<tr>
				<th>GIORNO CON PIU' ACCESSI</th>
				<th>NUMERO ACCESSI</th>
			</tr>
			<tr>
				<td><xsl:value-of select="MAX_USERS_DAY" disable-output-escaping="yes"/>&#160;</td>
				<td><xsl:value-of select="MAX_USERS_IN_DAY" disable-output-escaping="yes"/>&#160;</td>
			</tr>
			
		</table>
    </xsl:template>
</xsl:stylesheet>

