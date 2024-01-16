<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>
		
	<xsl:template match="_embedded">
		<typeStates>
			<xsl:apply-templates />
		</typeStates>
	</xsl:template>

	<xsl:template match="typeStates">
		<typeState>
			<id>
				<xsl:value-of select="substring-after(_links/self/href, 'typeState/')" />
			</id>				
			<xsl:apply-templates select="description"/>
		</typeState>
	</xsl:template>

	<xsl:template match="description">
		<description>
			<xsl:value-of select="." />
		</description>
	</xsl:template>
		
	<xsl:template match="self" />
	<xsl:template match="profile" />
	<xsl:template match="search" />
	<xsl:template match="page" />
</xsl:stylesheet>