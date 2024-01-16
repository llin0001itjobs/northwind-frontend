<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<orderTaxStatuses>
			<xsl:apply-templates />
		</orderTaxStatuses>
	</xsl:template>

	<xsl:template match="orderTaxStatuses">
		<orderTaxStatus>
			<id>
				<xsl:value-of select="substring-after(_links/self/href, 'orderTaxStatus/')" />
			</id>				
			<xsl:apply-templates select="taxStatusName"/>
		</orderTaxStatus>
	</xsl:template>

	<xsl:template match="taxStatusName">
		<taxStatusName>
			<xsl:value-of select="." />
		</taxStatusName>
	</xsl:template>

	<xsl:template match="self" />
	<xsl:template match="profile" />
	<xsl:template match="search" />
	<xsl:template match="page" />

</xsl:stylesheet>