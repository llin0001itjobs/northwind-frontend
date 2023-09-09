<?xml version = "1.0" encoding = "UTF-8"?>
<!-- xsl stylesheet declaration with xsl namespace: Namespace tells the xlst 
	processor about which element is to be processed and which is used for output 
	purpose only -->
<xsl:stylesheet version="3.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
	<!-- xsl template declaration: template tells the xlst processor about the 
		section of xml document which is to be formatted. It takes an XPath expression. 
		In our case, it is matching document root element and will tell processor 
		to process the entire document with this template. -->
	
	<xsl:template match="_embedded">
		<authentications>
			<xsl:apply-templates/>
		</authentications>
	</xsl:template>

	<xsl:template match="authentications">
		<authentication>
			<xsl:apply-templates />
		</authentication>
	</xsl:template>

	<xsl:template match="roleType">
		<roleType>
			<xsl:value-of select="." />
		</roleType>
	</xsl:template>

	<xsl:template match="userName">
		<userName>
			<xsl:value-of select="." />
		</userName>
	</xsl:template>
	
	<xsl:template match="password">
		<password>
			<xsl:value-of select="." />
		</password>
	</xsl:template>	
	      
	<xsl:template match="_links">
		<links>
			<xsl:apply-templates />
		</links>
	</xsl:template>
	
	<xsl:template match="authentication">	
		<link label="authentication">
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