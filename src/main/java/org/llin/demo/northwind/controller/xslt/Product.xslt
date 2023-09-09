<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>
	
	<xsl:template match="_embedded">
		<products>
			<xsl:apply-templates />
		</products>
	</xsl:template>

	<xsl:template match="products">
		<product>
			<xsl:apply-templates />
		</product>
	</xsl:template>
	
	<xsl:template match="productCode">
		<productCode>
			<xsl:value-of select="." />
		</productCode>
	</xsl:template>
	
	<xsl:template match="productName">
		<productName>
			<xsl:value-of select="." />
		</productName>
	</xsl:template>
	
	<xsl:template match="description">
		<description>
			<xsl:value-of select="." />
		</description>
	</xsl:template>
	
	<xsl:template match="standardCost">
		<standardCost>
			<xsl:value-of select="." />
		</standardCost>
	</xsl:template>
	
	<xsl:template match="listPrice">
		<listPrice>
			<xsl:value-of select="." />
		</listPrice>
	</xsl:template>
	
	<xsl:template match="reorderLevel">
		<reorderLevel>
			<xsl:value-of select="." />
		</reorderLevel>
	</xsl:template>
	
	<xsl:template match="targetLevel">
		<targetLevel>
			<xsl:value-of select="." />
		</targetLevel>
	</xsl:template>
	
	<xsl:template match="quantityPerUnit">
		<quantityPerUnit>
			<xsl:value-of select="." />
		</quantityPerUnit>
	</xsl:template>
	
	<xsl:template match="discontinued">
		<discontinued>
			<xsl:value-of select="." />
		</discontinued>
	</xsl:template>
	
	<xsl:template match="minimumReorderQuantity">
		<minimumReorderQuantity>
			<xsl:value-of select="." />
		</minimumReorderQuantity>
	</xsl:template>
	
	<xsl:template match="category">
		<category>
			<xsl:value-of select="." />
		</category>
	</xsl:template>
	
	<xsl:template match="resourceId">
		<resourceId>
			<xsl:value-of select="." />
		</resourceId>
	</xsl:template>
	
	<xsl:template match="_links">
		<links>
			<xsl:apply-templates />
		</links>
	</xsl:template>	
	
	<xsl:template match="product">
		<link label="product">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>
		
	<xsl:template match="supplier">
		<link lable="supplier">
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