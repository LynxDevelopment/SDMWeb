
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

	<xsl:param name="context"/>

	<xsl:template match="profile">
		<xsl:apply-templates select="tag"/>
	</xsl:template>

	<xsl:template match="tag">
		<table border="0" cellspacing="0" cellpadding="0">
	    <tr height="20">
	    	<td class="clsProTdLabel" width="50%">TAG NAME</td>
	    	<td class="clsProTdValue" width="50%"><xsl:value-of select="tagName"/>&#160;</td>
	    </tr>
	    <tr height="20">
	    	<td class="clsProTdLabel" width="50%">MAX TIME</td>
	    	<td class="clsProTdValue" width="50%"><xsl:value-of select="maxTime"/>&#160;</td>
	    </tr>
   		<tr height="20">
	    	<td class="clsProTdLabel" width="50%">MIN TIME</td>
	    	<td class="clsProTdValue" width="50%"><xsl:value-of select="minTime"/>&#160;</td>
	    </tr>
	    <tr height="20">
	    	<td class="clsProTdLabel" width="50%">AVERAGE</td>
	    	<td class="clsProTdValue" width="50%"><xsl:value-of select="windowAverage"/>&#160;</td>
	    </tr>
	  	<tr height="20">
	    	<td class="clsProTdLabel" width="50%">TIME WINDOW</td>
	    	<td class="clsProTdValue" width="50%"><xsl:value-of select="timeWindow"/>&#160;</td>
	    </tr>
	    <tr height="20">
	    	<td class="clsProTdLabel" width="50%">SAMPLE COUNT</td>
	    	<td class="clsProTdValue" width="50%"><xsl:value-of select="sampleCount"/>&#160;</td>
	    </tr>
	    <tr>
	    	<td class="clsProTdChart" colspan="2">
	    	<img src="{context}ChartServlet?chart=line&amp;width=500&amp;background=eaebed&amp;sampleLabels={sampleLabels}&amp;sampleLabelsOn=true&amp;sampleValues={sampleValues}"></img>
	    	</td>
	    </tr>
	  	</table>
		<br></br>
	</xsl:template>

</xsl:stylesheet>