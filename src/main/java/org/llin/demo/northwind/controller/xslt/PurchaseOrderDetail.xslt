<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<purchaseOrderDetails>
			<xsl:apply-templates />
		</purchaseOrderDetails>
	</xsl:template>

	<xsl:template match="purchaseOrderDetails">
		<purchaseOrderDetail>
			<xsl:apply-templates />
		</purchaseOrderDetail>
	</xsl:template>
	
	<xsl:template match="quantity">
		<quantity>
			<xsl:value-of select="." />
		</quantity>
	</xsl:template>
	
	<xsl:template match="unitCost">
		<submittedDate>
			<xsl:value-of select="." />
		</submittedDate>
	</xsl:template>
	
	<xsl:template match="dateReceived">
		<dateReceived>
			<xsl:value-of select="." />
		</dateReceived>
	</xsl:template>
	
	<xsl:template match="postedToInventory">
		<postedToInventory>
			<xsl:value-of select="." />
		</postedToInventory>
	</xsl:template>
	
	<xsl:template match="_links">
		<links>
			<xsl:apply-templates />
		</links>
	</xsl:template>	

	<xsl:template match="purchaseOrderDetail">
		<link label="purchaseOrderDetail">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>
	
	<xsl:template match="inventoryTransaction">
		<link label="inventoryTransaction">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="purchaseOrder">
		<link label="purchaseOrder">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="product">
		<link label="product">
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