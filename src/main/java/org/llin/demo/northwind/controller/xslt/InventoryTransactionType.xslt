<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<inventoryTransactionTypes>
			<xsl:apply-templates />
		</inventoryTransactionTypes>
	</xsl:template>

	<xsl:template match="inventoryTransactionTypes">
		<inventoryTransaction>
			<xsl:apply-templates />
		</inventoryTransaction>
	</xsl:template>

	<xsl:template match="typeName">
		<name>
			<xsl:value-of select="." />
		</name>
	</xsl:template>
	
	<xsl:template match="_links">
		<links>
			<xsl:apply-templates />
		</links>
	</xsl:template>

	<xsl:template match="inventoryTransactionType">
		<link label="inventoryTransactionType">
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