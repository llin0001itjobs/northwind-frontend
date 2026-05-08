<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simple" page-height="21cm" page-width="29.7cm" margin-top="1cm" margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
                    <fo:region-body margin-top="3cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simple">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="18pt" font-weight="bold" text-align="center" space-after="10mm">
                        Hello, <xsl:value-of select="hello"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>