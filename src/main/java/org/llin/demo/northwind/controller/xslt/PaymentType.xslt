<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<paymentTypes>
			<xsl:apply-templates />
		</paymentTypes>
	</xsl:template>

	<xsl:template match="paymentTypes">
		<paymentType>
			<xsl:apply-templates />
		</paymentType>
	</xsl:template>

	<xsl:template match="description">
		<description>
			<xsl:value-of select="." />
		</description>
	</xsl:template>

	<xsl:template match="_links">
		<links>
			<xsl:apply-templates />
		</links>
	</xsl:template>

	<xsl:template match="paymentType">
		<link lable="paymentType">
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