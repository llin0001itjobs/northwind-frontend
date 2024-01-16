<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<invoices>
			<xsl:apply-templates />
		</invoices>
	</xsl:template>

	<xsl:template match="invoices">
		<invoice>
			<id>
				<xsl:value-of select="substring-after(_links/self/href, 'invoice/')" />
			</id>				
			<xsl:apply-templates/>
		</invoice>
	</xsl:template>

	<xsl:template match="invoiceDate">
		<invoiceDate>
			<xsl:value-of select="." />
		</invoiceDate>
	</xsl:template>
	
	<xsl:template match="dueDate">
		<dueDate>
			<xsl:value-of select="." />
		</dueDate>
	</xsl:template>
	
	<xsl:template match="tax">
		<tax>
			<xsl:value-of select="." />
		</tax>
	</xsl:template>
	
	<xsl:template match="shipping">
		<shipping>
			<xsl:value-of select="." />
		</shipping>
	</xsl:template>

	<xsl:template match="amountDue">
		<amountDue>
			<xsl:value-of select="." />
		</amountDue>
	</xsl:template>
				
	<xsl:template match="_links">
		<links>
			<xsl:apply-templates select="customerOrder"/>
		</links>
	</xsl:template>
			
	<xsl:template match="customerOrder">
		<link label="customerOrder">
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