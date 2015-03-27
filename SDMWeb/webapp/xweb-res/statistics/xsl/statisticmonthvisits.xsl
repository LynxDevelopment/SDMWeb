<?xml version="1.0" encoding="ISO-8859-1"?>


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	 	       encoding="ISO-8859-1"
		       indent = "yes" />

<xsl:param name="context"/>

<xsl:variable name="redirect" select="/xweb-res/statistics/statistic_monthvisits.xwb" />

<xsl:template match="daily_visits">
	
<table class="data">
	<tr>
		<th>GRAFICO ACCESSI MESE <xsl:value-of select="@month"/></th>
	</tr>
	<tr>
		<td>
	      <center>

			<br/><br/>
	<!--
	Grafico con immagine JPEG
	<img> 
		<xsl:attribute name="src">/ChartServlet?width=650&amp;height=250&amp;background=%23EAEBED&amp;chartTitle=Accessi nel mese <xsl:value-of select="@month"/>&amp;sampleLabels=<xsl:call-template name="daynums"/>&amp;barLabelsOn=true&amp;sampleAxisLabel=Giorni&amp;sampleValues=<xsl:call-template name="numvisits"/>&amp;rangeStep=12&amp;rangeAxisLabel=Accessi&amp;rangeAxisLabelAngle=270</xsl:attribute>
	</img>
	-->
			<applet code="com.objectplanet.chart.BarChartApplet" width="700" height="350" archive="{$context}/applet/chart.jar">
		
				<param name="background" value="#f5f5f5"/>
				<param name="3DModeOn" value="true"/>
				<param name="3DDepth" value="3"/>
				<param name="multiColorOn" value="false"/>
				<!--
				<param name="chartTitle">
					<xsl:attribute name="value">
						Accessi nel mese <xsl:value-of select="@month"/>
					</xsl:attribute>	
				</param>
				-->
				
				
				<param name="titleFont" value="Arial, bold, 20"/>
				
				<param name="sampleAxisLabel" value="\nGiorni"/>
				<param name="barLabels">
					<xsl:attribute name="value">
						<xsl:call-template name="daynums"/>
					</xsl:attribute>
				</param> 
				<param name="barLabelsOn" value="true"/>
				<param name="valueLabelsOn" value="true"/>
				<param name="valueLabelStyle" value="outside"/>
				<param name="sampleScrollerOn" value="true"/>
				<param name="valueLinesOn" value="true"/>
				<param name="sampleLabels">
					<xsl:attribute name="value">
						<xsl:call-template name="numvisits"/>
					</xsl:attribute>
				</param>
				<param name="rangeAxisLabel" value="Accessi effettuati"/>
				<param name="sampleValues_0"> 
					<xsl:attribute name="value">
						<xsl:call-template name="numvisits"/>
					</xsl:attribute>
				</param>
				<param name="rangeAxisLabelAngle" value="270"/>
				<param name="barOutlineOff" value="true"/>
				<param name="rangeAdjusterOn" value="true"/>
			</applet>
	<br/><br/>	
	<a href="{$redirect}?month={prev_month}">&lt; Mese precedente</a>
	&#160;&#160;&#160;
	<a href="{$redirect}?month={next_month}">Mese successivo &gt;</a>
	<br/><br/>
	   </center>
	</td>
	</tr>
</table>
</xsl:template>

<xsl:template name="numvisits">
	<xsl:for-each select="day">
		<xsl:choose>
			<xsl:when test="position()!=last()"><xsl:value-of select="visits"/>,</xsl:when>
			<xsl:otherwise><xsl:value-of select="visits"/></xsl:otherwise>
		</xsl:choose>
	</xsl:for-each>	
</xsl:template>

<xsl:template name="daynums">
	<xsl:for-each select="day">
		<xsl:choose>
			<xsl:when test="position()!=last()"><xsl:value-of select="date"/>,</xsl:when>
			<xsl:otherwise><xsl:value-of select="date"/></xsl:otherwise>
		</xsl:choose>
	</xsl:for-each>	
</xsl:template>


</xsl:stylesheet>

