<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<productSuppliers>
			<xsl:apply-templates />
		</productSuppliers>
	</xsl:template>

	<xsl:template match="productSuppliers">
		<productSupplier>
			<xsl:apply-templates />
		</productSupplier>
	</xsl:template>

	<xsl:template match="product_id">
		<product_id>
			<xsl:value-of select="." />
		</product_id>
	</xsl:template>

	<xsl:template match="supplier_id">
		<supplier_id>
			<xsl:value-of select="." />
		</supplier_id>
	</xsl:template>

	<xsl:template match="_links">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="productSupplier">
		<self>
			<href>
				<xsl:value-of select="." />
			</href>
		</self>
	</xsl:template>

	<xsl:template match="self" />
	<xsl:template match="profile" />
	<xsl:template match="search" />
	<xsl:template match="page" />

</xsl:stylesheet>