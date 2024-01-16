<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<inventoryTransactions>
			<xsl:apply-templates />
		</inventoryTransactions>
	</xsl:template>

	<xsl:template match="inventoryTransactions">
		<inventoryTransaction>
			<id>
				<xsl:value-of select="substring-after(_links/self/href, 'inventoryTransaction/')" />
			</id>		
			<xsl:apply-templates />
		</inventoryTransaction>
	</xsl:template>

	<xsl:template match="transactionCreatedDate">
		<transactionCreatedDate>
			<xsl:value-of select="." />
		</transactionCreatedDate>
	</xsl:template>
	
	<xsl:template match="transactionModifiedDate">
		<transactionModifiedDate>
			<xsl:value-of select="." />
		</transactionModifiedDate>
	</xsl:template>
	
	<xsl:template match="quantity">
		<quantity>
			<xsl:value-of select="." />
		</quantity>
	</xsl:template>
	
	<xsl:template match="comments">
		<comments>
			<xsl:value-of select="." />
		</comments>
	</xsl:template>
	
	<xsl:template match="_links">
		<links>
			<xsl:apply-templates select="customerOrder|inventoryTransactionType|product|purchaseOrder" />
		</links>
	</xsl:template>

	<xsl:template match="customerOrder">
		<link label="customerOrder">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>
	
	<xsl:template match="inventoryTransactionType">
		<link label="inventoryTransactionType">
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
	
	<xsl:template match="purchaseOrder">
		<link label="purchaseOrder">
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