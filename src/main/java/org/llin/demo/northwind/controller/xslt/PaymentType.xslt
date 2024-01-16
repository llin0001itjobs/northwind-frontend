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
			<id>
				<xsl:value-of select="substring-after(_links/self/href, 'paymentType/')" />
			</id>					
			<xsl:apply-templates select="description"/>
		</paymentType>
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