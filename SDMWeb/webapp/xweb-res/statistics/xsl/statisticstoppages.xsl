<?xml version="1.0" encoding="ISO-8859-1"?>


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	 	       encoding="ISO-8859-1"
		       indent = "yes" />
			   
<xsl:param name="context"/>
	
<xsl:template match="resultset">
				<table class="data">
					<tr>
						<th>GRAFICO ACCESSI</th>
					</tr>
					<tr>
						<td>
							<br/><br/>
							<center>
							<!--
							Grafico con immagine JPEG
							<img src="{$context}/ChartServlet?chart=bar&amp;3DModeOn=true&amp;3DDepth=4&amp;barWidth=0.6&amp;width=650&amp;height=250&amp;background=%23EAEBED&amp;chartBackground=%23EAEBED&amp;chartTitle=Pagine piu' visitate\n&amp;barAlignment=horizontal&amp;sampleLabels={row[1]/PAGE_NAME},{row[2]/PAGE_NAME},{row[3]/PAGE_NAME},{row[4]/PAGE_NAME},{row[5]/PAGE_NAME},{row[6]/PAGE_NAME},{row[7]/PAGE_NAME},{row[8]/PAGE_NAME},{row[9]/PAGE_NAME},{row[10]/PAGE_NAME}&amp;barLabelsOn=true&amp;sampleValues={row[1]/VISITS},{row[2]/VISITS},{row[3]/VISITS},{row[4]/VISITS},{row[5]/VISITS},{row[6]/VISITS},{row[7]/VISITS},{row[8]/VISITS},{row[9]/VISITS},{row[10]/VISITS}&amp;rangeAxisLabel=Accessi&amp;valueLabelsOn=true&amp;valueLabelStyle=inside"/>
							-->
							<applet code="com.objectplanet.chart.BarChartApplet" width="600" height="400" archive="{$context}/applet/chart.jar">
						
								<param name="background" value="#f5f5f5"/>
								<param name="3DModeOn" value="true"/>
								<param name="3DDepth" value="5"/>
								<param name="multiColorOn" value="true"/>
								<param name="barAlignment" value="horizontal"/>
								<param name="chartTitle" value=""/>
						
								<param name="sampleAxisLabel" value="Pagine\n"/>
								<param name="sampleLabels">
									<xsl:attribute name="value">
										<xsl:call-template name="pagelist"/>
									</xsl:attribute>
								</param> 
								<param name="sampleAxisLabelAngle" value="270"/>
								<param name="barLabelsOn" value="true"/>
								<param name="valueLabelsOn" value="true"/>
								<param name="valueLabelStyle" value="outside"/>
								<param name="sampleScrollerOn" value="true"/>
								<param name="valueLinesOn" value="true"/>
						
								<param name="rangeAxisLabel" value="\nAccessi effettuati"/>
								<param name="sampleValues_0"> 
									<xsl:attribute name="value">
										<xsl:call-template name="visitlist"/>
									</xsl:attribute>
								</param>
								<param name="rangeAxisLabelAngle" value="270"/>
								<param name="barOutlineOff" value="true"/>
								<param name="rangeAdjusterOn" value="true"/>
							</applet>
							<br/><br/>
							</center>
						</td>
				   </tr>
              </table>
</xsl:template>
	
<xsl:template name="pagelist">

	<xsl:for-each select="row/PAGE_NAME">
		<xsl:value-of select="." disable-output-escaping="yes"/>
		<xsl:if test="position() != last()">,</xsl:if>		
	</xsl:for-each>

</xsl:template>

<xsl:template name="visitlist">

	<xsl:for-each select="row/VISITS">
		<xsl:value-of select="." disable-output-escaping="yes"/>
		<xsl:if test="position() != last()">,</xsl:if>		
	</xsl:for-each>

</xsl:template>

</xsl:stylesheet>

