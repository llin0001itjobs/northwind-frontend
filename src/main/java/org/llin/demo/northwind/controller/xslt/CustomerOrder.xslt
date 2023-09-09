<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<customerOrders>
			<xsl:apply-templates />
		</customerOrders>
	</xsl:template>

	<xsl:template match="customerOrders">
		<customerOrder>
			<xsl:apply-templates />
		</customerOrder>
	</xsl:template>

	<xsl:template match="orderDate">
		<orderDate>
			<xsl:value-of select="." />
		</orderDate>
	</xsl:template>

	<xsl:template match="paidDate">
		<paidDate>
			<xsl:value-of select="." />
		</paidDate>
	</xsl:template>

	<xsl:template match="shippedDate">
		<shippedDate>
			<xsl:value-of select="." />
		</shippedDate>
	</xsl:template>

	<xsl:template match="shipName">
		<shipName>
			<xsl:value-of select="." />
		</shipName>
	</xsl:template>

	<xsl:template match="shipAddress">
		<shipAddress>
			<xsl:value-of select="." />
		</shipAddress>
	</xsl:template>

	<xsl:template match="shipCity">
		<shipCity>
			<xsl:value-of select="." />
		</shipCity>
	</xsl:template>

	<xsl:template match="shipStateProvince">
		<shipStateProvince>
			<xsl:value-of select="." />
		</shipStateProvince>
	</xsl:template>

	<xsl:template match="shipZipPostalCode">
		<shipZipPostalCode>
			<xsl:value-of select="." />
		</shipZipPostalCode>
	</xsl:template>

	<xsl:template match="shipCountryRegion">
		<shipCountryRegion>
			<xsl:value-of select="." />
		</shipCountryRegion>
	</xsl:template>

	<xsl:template match="shippingFee">
		<shippingFee>
			<xsl:value-of select="." />
		</shippingFee>
	</xsl:template>

	<xsl:template match="taxes">
		<taxes>
			<xsl:value-of select="." />
		</taxes>
	</xsl:template>

	<xsl:template match="taxRate">
		<taxRate>
			<xsl:value-of select="." />
		</taxRate>
	</xsl:template>

	<xsl:template match="paymentType">
		<paymentType>
			<xsl:value-of select="." />
		</paymentType>
	</xsl:template>

	<xsl:template match="notes">
		<notes>
			<xsl:value-of select="." />
		</notes>
	</xsl:template>

	<xsl:template match="_links">
		<links>
			<xsl:apply-templates />
		</links>
	</xsl:template>

	<xsl:template match="customerOrder">
		<link label="customerOrder">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="shipper">
		<link label="shipper">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="orderStatus">
		<link label="orderStatus">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="employee">
		<link label="employee">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="customer">
		<link label="customer">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="orderTaxStatus">
		<link label="orderTaxStatus">
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