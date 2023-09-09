<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<orderDetailStatuses>
			<xsl:apply-templates />
		</orderDetailStatuses>
	</xsl:template>

	<xsl:template match="orderDetailStatuses">
		<orderDetailStatus>
			<xsl:apply-templates />
		</orderDetailStatus>
	</xsl:template>

	<xsl:template match="statusName">
		<statusName>
			<xsl:value-of select="." />
		</statusName>
	</xsl:template>

	<xsl:template match="_links">
		<links>
			<xsl:apply-templates />
		</links>
	</xsl:template>

	<xsl:template match="orderDetailStatus">
		<link lable="orderDetailStatus">
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