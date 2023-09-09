<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>
		
	<xsl:template match="_embedded">
		<shippers>
			<xsl:apply-templates />
		</shippers>
	</xsl:template>

	<xsl:template match="shippers">
		<shipper>
			<xsl:apply-templates />
		</shipper>
	</xsl:template>

	<xsl:template match="lastName">
		<lastName>
			<xsl:value-of select="." />
		</lastName>
	</xsl:template>
	<xsl:template match="firstName">
		<firstName>
			<xsl:value-of select="." />
		</firstName>
	</xsl:template>
	<xsl:template match="emailAddress">
		<emailAddress>
			<xsl:value-of select="." />
		</emailAddress>
	</xsl:template>
	<xsl:template match="jobTitle">
		<jobTitle>
			<xsl:value-of select="." />
		</jobTitle>
	</xsl:template>
	<xsl:template match="businessPhone">
		<businessPhone>
			<xsl:value-of select="." />
		</businessPhone>
	</xsl:template>
	<xsl:template match="homePhone">
		<homePhone>
			<xsl:value-of select="." />
		</homePhone>
	</xsl:template>
	<xsl:template match="mobilePhone">
		<mobilePhone>
			<xsl:value-of select="." />
		</mobilePhone>
	</xsl:template>
	<xsl:template match="faxNumber">
		<faxNumber>
			<xsl:value-of select="." />
		</faxNumber>
	</xsl:template>
	<xsl:template match="address1">
		<address1>
			<xsl:value-of select="." />
		</address1>
	</xsl:template>
	<xsl:template match="address2">
		<address2>
			<xsl:value-of select="." />
		</address2>
	</xsl:template>
	<xsl:template match="city">
		<city>
			<xsl:value-of select="." />
		</city>
	</xsl:template>
	<xsl:template match="stateProvince">
		<stateProvince>
			<xsl:value-of select="." />
		</stateProvince>
	</xsl:template>
	<xsl:template match="zipPostalCode">
		<zipPostalCode>
			<xsl:value-of select="." />
		</zipPostalCode>
	</xsl:template>
	<xsl:template match="countryRegion">
		<countryRegion>
			<xsl:value-of select="." />
		</countryRegion>
	</xsl:template>
	<xsl:template match="notes">
		<notes>
			<xsl:value-of select="." />
		</notes>
	</xsl:template>
	<xsl:template match="webSiteTitle">
		<webSiteTitle>
			<xsl:value-of select="." />
		</webSiteTitle>
	</xsl:template>
	<xsl:template match="webSiteUrl">
		<webSiteUrl>
			<xsl:value-of select="." />
		</webSiteUrl>
	</xsl:template>
	<xsl:template match="portraitPath">
		<portraitPath>
			<xsl:value-of select="." />
		</portraitPath>
	</xsl:template>
	<xsl:template match="portraitTitle">
		<portraitTitle>
			<xsl:value-of select="." />
		</portraitTitle>
	</xsl:template>

	<xsl:template match="_links">
		<links>
			<xsl:apply-templates />
		</links>
	</xsl:template>	

	<xsl:template match="shipper">
		<link label="shipper">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>
	
	<xsl:template match="company">
		<link label="company">
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