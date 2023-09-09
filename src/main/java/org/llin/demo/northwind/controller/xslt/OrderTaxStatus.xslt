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
			<xsl:apply-templates />
		</orderTaxStatus>
	</xsl:template>

	<xsl:template match="taxStatusName">
		<taxStatusName>
			<xsl:value-of select="." />
		</taxStatusName>
	</xsl:template>

	<xsl:template match="_links">
		<links>
			<xsl:apply-templates />
		</links>
	</xsl:template>

	<xsl:template match="orderTaxStatus">
		<link lable="orderTaxStatus">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="self" />
	<xsl:template match="profile" />
	<xsl:template match="search" />
	<xsl:template match="page" />

</xsl:stylesheet>