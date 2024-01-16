<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<orderDetails>
			<xsl:apply-templates />
		</orderDetails>
	</xsl:template>

	<xsl:template match="orderDetails">
		<orderDetail>
			<id>
				<xsl:value-of select="substring-after(_links/self/href, 'orderDetail/')" />
			</id>			
			<xsl:apply-templates />
		</orderDetail>
	</xsl:template>

	<xsl:template match="quantity">
		<quantity>
			<xsl:value-of select="." />
		</quantity>
	</xsl:template>

	<xsl:template match="unitPrice">
		<unitPrice>
			<xsl:value-of select="." />
		</unitPrice>
	</xsl:template>

	<xsl:template match="discount">
		<discount>
			<xsl:value-of select="." />
		</discount>
	</xsl:template>

	<xsl:template match="dateAllocated">
		<dateAllocated>
			<xsl:value-of select="." />
		</dateAllocated>
	</xsl:template>

	<xsl:template match="_links">
		<links>
			<xsl:apply-templates select="customerOrder|inventoryTransaction|orderStatus|product|purchaseOrder"/>
		</links>
	</xsl:template>

	<xsl:template match="customerOrder">
		<link label="customerOrder">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="inventoryTransaction">
		<link lable="inventoryTransaction">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="orderStatus">
		<link lable="orderStatus">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>
	
	<xsl:template match="product">
		<link lable="product">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="purchaseOrder">
		<link lable="purchaseOrder">
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