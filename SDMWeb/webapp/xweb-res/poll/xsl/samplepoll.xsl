<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method = "html"
 	        encoding="ISO-8859-1"
		    indent = "yes" />

	<xsl:param name="poll_id"/>
	<xsl:param name="user_id"/>

	<xsl:template match="poll">
		<form name="vote" action="votePoll.do">
			<input type="hidden" name="USER_ID" value="{$user_id}"/>
			<input type="hidden" name="POOL_ID" value="{$poll_id}"/>
			<h1><xsl:value-of select="description"/></h1>
			<h2><xsl:value-of select="question"/></h2>
			<xsl:choose>
				<xsl:when test="question_type = '0'">
					<xsl:for-each select="answer">
						<input type="radio" name="ANSWERS_ID" value="{answer_id}"/>
						<xsl:value-of select="answer_text"/><br/>
					</xsl:for-each>
				</xsl:when>
				<xsl:otherwise>
					<xsl:for-each select="answer">
						<input type="checkbox" name="ANSWERS_ID" value="{answer_id}"/>
						<xsl:value-of select="answer_text"/><br/>
					</xsl:for-each>
				</xsl:otherwise>
			</xsl:choose>
			<br/>
			<input type="submit" value="Vota!"/>
		</form>
	</xsl:template>
	
</xsl:stylesheet>