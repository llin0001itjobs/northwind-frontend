<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="_embedded">
		<purchaseOrders>
			<xsl:apply-templates />
		</purchaseOrders>
	</xsl:template>

	<xsl:template match="purchaseOrders">
		<purchaseOrder>
			<id>
				<xsl:value-of select="substring-after(_links/self/href, 'purchaseOrder/')" />
			</id>			
			<xsl:apply-templates />
		</purchaseOrder>
	</xsl:template>

	<xsl:template match="approvedDate">
		<approvedDate>
			<xsl:value-of select="." />
		</approvedDate>
	</xsl:template>
	
	<xsl:template match="submittedDate">
		<submittedDate>
			<xsl:value-of select="." />
		</submittedDate>
	</xsl:template>
	
	<xsl:template match="creationDate">
		<creationDate>
			<xsl:value-of select="." />
		</creationDate>
	</xsl:template>
	
	<xsl:template match="expectedDate">
		<expectedDate>
			<xsl:value-of select="." />
		</expectedDate>
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
	
	<xsl:template match="paymentDate">
		<paymentDate>
			<xsl:value-of select="." />
		</paymentDate>
	</xsl:template>
	
	<xsl:template match="paymentAmount">
		<paymentAmount>
			<xsl:value-of select="." />
		</paymentAmount>
	</xsl:template>
	
	<xsl:template match="paymentMethod">
		<paymentMethod>
			<xsl:value-of select="." />
		</paymentMethod>
	</xsl:template>
	
	<xsl:template match="notes">
		<notes>
			<xsl:value-of select="." />
		</notes>
	</xsl:template>

	<xsl:template match="_links">
		<links>
			<xsl:apply-templates select="approvedBy|createdBy|submittedBy|orderStatus|supplier" />
		</links>
	</xsl:template>	

	<xsl:template match="approvedBy">
		<link label="approvedBy">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="createdBy">
		<link label="createdBy">
			<href>
				<xsl:value-of select="." />
			</href>
		</link>
	</xsl:template>

	<xsl:template match="submittedBy">
		<link label="submittedBy">
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

	<xsl:template match="supplier">
		<link label="supplier">
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