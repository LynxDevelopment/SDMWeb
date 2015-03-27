<?xml version="1.0" encoding="ISO-8859-1"?>


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	 	       encoding="ISO-8859-1"
		       indent = "yes" />
<xsl:param name="context"/>
<xsl:template match="resultset">
		<!-- Grafico come immagine JPEG
		<img> 
			<xsl:attribute name="src">/ChartServlet?chart=pie&amp;3DModeOn=true&amp;width=650&amp;height=250&amp;&amp;background=%23EAEBED&amp;chartTitle=Utilizzo browser\n&amp;sampleLabels=<xsl:call-template name="useragentlist"/>&amp;percentLabelsOn=true&amp;percentLabelStyle=inside&amp;percentDecimalCount=1&amp;sampleValues=<xsl:call-template name="usagelist"/>&amp;legendOn=true&amp;legendPosition=right</xsl:attribute>
		</img>
		-->
		<table class="data">
		 <tr>
			<td>
		        <center>
					<br/><br/>
				<applet code="com.objectplanet.chart.BarChartApplet" width="600" height="380" archive="{$context}/applet/chart.jar">
			
					<param name="background" value="#f5f5f5"/>
					<param name="3DModeOn" value="true"/>
					<param name="3DDepth" value="5"/>
					<param name="multiColorOn" value="true"/>
					<param name="barAlignment" value="horizontal"/>
					<param name="barWidth" value="0.2"/>
					<param name="chartTitle" value=""/>
			
					<param name="sampleAxisLabel" value="Versioni\n"/>
					<param name="sampleAxisLabelAngle" value="270"/>
					<param name="sampleLabels">
						<xsl:attribute name="value">
							<xsl:call-template name="versionlist"/>
						</xsl:attribute>
					</param> 
					<param name="barLabelsOn" value="true"/>
					<param name="valueLabelsOn" value="true"/>
					<param name="valueLabelStyle" value="outside"/>
					<param name="sampleScrollerOn" value="true"/>
					<param name="valueLinesOn" value="true"/>
			
					<param name="rangeAxisLabel" value="\nAccessi effettuati\n"/>
					<param name="sampleValues_0"> 
						<xsl:attribute name="value">
							<xsl:call-template name="usagelist"/>
						</xsl:attribute>
					</param>
					<param name="rangeAxisLabelAngle" value="270"/>
					<param name="barOutlineOff" value="true"/>
					<param name="rangeAdjusterOn" value="true"/>
				</applet>
				</center>
				<br/><br/>
			</td>
		</tr>
	</table>
</xsl:template>
	
<xsl:template name="versionlist">

	<xsl:for-each select="row">
		<xsl:sort select="VERSION" data-type="number" order="descending"/>
		<xsl:choose>
			<xsl:when test="VERSION = ''">
				Altro
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="VERSION" disable-output-escaping="yes"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:if test="position() != last()">,</xsl:if>		
	</xsl:for-each>

</xsl:template>

<xsl:template name="usagelist">

	<xsl:for-each select="row">
		<xsl:sort select="VERSION" data-type="number" order="descending"/>
		<xsl:value-of select="USAGE" disable-output-escaping="yes"/>
		<xsl:if test="position() != last()">,</xsl:if>		
	</xsl:for-each>

</xsl:template>

</xsl:stylesheet>

