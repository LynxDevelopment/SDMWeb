<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

<xsl:param name="context"/>

	<xsl:template match="profile">
	
		<table class="data">
			<thead>
				<tr>
					<th align="left">Nome del Tag</th>
					<th align="left">Tempo minimo di esecuzione</th>
					<th align="left">Tempo medio di esecuzione</th>
					<th align="left">Tempo massimo di esecuzione</th>
					<th align="left">Numero di campioni visualizzabili</th>
					<th align="left">Numero di campioni</th>
				</tr>
			</thead>
			<tbody>
				<xsl:apply-templates select="tag">
					<xsl:sort select="windowAverage" data-type="number" order="descending"/>
				</xsl:apply-templates>
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="tag">		
		<tr>
			<td><a href="#" onclick="return false;" onmouseover="return escape(openChart('{sampleLabels}', '{sampleValues}', '{maxTime}'));"><xsl:value-of select="tagName"/>&#160;</a></td>
			<td style="text-align:right;"><xsl:value-of select="minTime"/>&#160;ms</td>
			<td style="text-align:right;"><xsl:value-of select="windowAverage"/>&#160;ms</td>
			<td style="text-align:right;"><xsl:value-of select="maxTime"/>&#160;ms</td>
			<td style="text-align:right;"><xsl:value-of select="timeWindow"/>&#160;</td>
			<td style="text-align:right;"><xsl:value-of select="sampleCount"/>&#160;</td>
		</tr>
<!-- 			<tr>
				<td colspan="2">
					<br/>
					<img src="{$context}/ChartServlet?chart=line&amp;width=500&amp;height=300&amp;background=white&amp;3DModeOn=true&amp;3DDepth=10&amp;valueLinesOn=true&amp;rangeAxisLabel=Tempo di esecuzione (ms)&amp;rangeAxisLabelAngle=270&amp;sampleValues={sampleValues}&amp;sampleAxisLabel=Numero progressivo di esecuzione&amp;sampleLabels={sampleLabels}&amp;sampleLabelsOn=true&amp;valueLabelsOn=true"></img>
					<br/>
					<br/>
				</td>
			</tr>-->

	</xsl:template>

</xsl:stylesheet>



