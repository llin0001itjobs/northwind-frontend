<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<purchaseOrderStatuses>
			<xsl:apply-templates />
		</purchaseOrderStatuses>
	</xsl:template>

	<xsl:template match="purchaseOrderStatuses">
		<purchaseOrderStatus>
			<id>
				<xsl:value-of select="substring-after(_links/self/href, 'purchaseOrderStatus/')" />
			</id>				
			<xsl:apply-templates select="status"/>
		</purchaseOrderStatus>
	</xsl:template>

	<xsl:template match="status">
		<status>
			<xsl:value-of select="." />
		</status>
	</xsl:template>
	
	<xsl:template match="self" />
	<xsl:template match="profile" />
	<xsl:template match="search" />
	<xsl:template match="page" />

</xsl:stylesheet>