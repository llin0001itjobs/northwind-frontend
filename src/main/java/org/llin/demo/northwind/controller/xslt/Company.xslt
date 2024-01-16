<?xml version = "1.0" encoding = "UTF-8"?>
<!-- xsl stylesheet declaration with xsl namespace: Namespace tells the xlst 
	processor about which element is to be processed and which is used for output 
	purpose only -->
<xsl:stylesheet version="3.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- xsl template declaration: template tells the xlst processor about the 
		section of xml document which is to be formatted. It takes an XPath expression. 
		In our case, it is matching document root element and will tell processor 
		to process the entire document with this template. -->

	<xsl:template match="_embedded">
		<companies>
			<xsl:apply-templates />
		</companies>
	</xsl:template>

	<xsl:template match="companies">
		<company>
			<id>
				<xsl:value-of
					select="substring-after(_links/self/href, 'companies/')" />
			</id>
			<xsl:apply-templates select="name" />
		</company>
	</xsl:template>

	<xsl:template match="name">
		<name>
			<xsl:value-of select="." />
		</name>
	</xsl:template>

	<xsl:template match="self" />
	<xsl:template match="profile" />
	<xsl:template match="search" />
	<xsl:template match="page" />
	
</xsl:stylesheet>