<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<privileges>
			<xsl:apply-templates />
		</privileges>
	</xsl:template>

	<xsl:template match="privileges">
		<privilege>
			<id>
				<xsl:value-of select="substring-after(_links/self/href, 'privilege/')" />
			</id>				
			<xsl:apply-templates select="privilegeName" />
		</privilege>
	</xsl:template>

	<xsl:template match="privilegeName">
		<privilegeName>
			<xsl:value-of select="." />
		</privilegeName>
	</xsl:template>
	
	<xsl:template match="self" />
	<xsl:template match="profile" />
	<xsl:template match="search" />
	<xsl:template match="page" />

</xsl:stylesheet>