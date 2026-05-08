<xsl:stylesheet version="1.0" 
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="xml" indent="yes" />

	<!-- Define a parameter for the image base path -->
	<xsl:param name="imageBasePath"
		select="'file:///src/main/resources/static/images/'" />
	<xsl:param name="colSize" select="7" />

	<xsl:template match="/Employees">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master
					master-name="first-page" page-height="21.59cm" page-width="27.94cm"
					margin-top="0.7cm" margin-bottom="0.7cm" margin-left="1.0cm"
					margin-right="1.0cm">
					<fo:region-body margin-top="0.1cm"
						border-width="0.5pt" border-style="solid" border-color="gray" />
					<fo:region-before extent="0.1cm" />
					<fo:region-after extent="0.1cm" />
				</fo:simple-page-master>

				<fo:simple-page-master
					master-name="other-pages" page-height="21.59cm"
					page-width="27.94cm" margin-top="0.7cm" margin-bottom="0.7cm"
					margin-left="1.0cm" margin-right="1.0cm">
					<fo:region-body margin-top="0.1cm"
						border-width="0.5pt" border-style="solid" border-color="gray" />
					<fo:region-before extent="0.1cm" />
					<fo:region-after extent="0.1cm" />
				</fo:simple-page-master>

				<fo:page-sequence-master
					master-name="document">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference
							master-reference="first-page" page-position="first" />
						<fo:conditional-page-master-reference
							master-reference="other-pages" />
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="document">
				<fo:flow flow-name="xsl-region-body">
					<fo:table table-layout="fixed" width="100%"
						border-collapse="separate">
						<fo:table-column column-width="37mm" />
						<fo:table-column column-width="37mm" />
						<fo:table-column column-width="37mm" />
						<fo:table-column column-width="37mm" />
						<fo:table-column column-width="37mm" />
						<fo:table-column column-width="37mm" />
						<fo:table-column column-width="37mm" />

						<fo:table-body>
							<!-- Process items in groups of 7 -->
							<xsl:for-each
								select="item[position() mod 7 = 1]">
								<fo:table-row>
									<!-- Process current item and up to 6 following siblings -->
									<xsl:variable name="group"
										select=". | following-sibling::item[position() &lt; 7]" />
									<xsl:for-each select="$group">

										<xsl:if test="position() &lt;= 7">
											<fo:table-cell display-align="after"
												padding-top="0.3cm"
												padding-bottom="0.3cm">
												<xsl:choose>
													<xsl:when test="position() = 7">
														<xsl:attribute name="border-right">none</xsl:attribute>
													</xsl:when>
													<xsl:otherwise>
														<xsl:attribute name="border-right">0.5pt solid gray</xsl:attribute>
													</xsl:otherwise>
												</xsl:choose>
												<fo:block text-align="center" font-size="9pt">
													<fo:external-graphic
														src="{$imageBasePath}{portraitPath}" content-width="30mm"
														content-height="50mm" scaling="uniform" />
												</fo:block>
												<fo:block text-align="center" font-size="9pt"
													font-weight="bold">
													<xsl:value-of select="portraitTitle" />
												</fo:block>
												<fo:block text-align="center" font-size="9pt">
													<xsl:value-of select="jobTitle" />
												</fo:block>
											</fo:table-cell>
										</xsl:if>
									</xsl:for-each>
									<!-- Fill remaining cells to ensure exactly 7 cells per row -->
									<xsl:variable name="cellsInRow"
										select="count($group)" />
									<xsl:if test="$cellsInRow &lt; 7">
										<xsl:call-template name="fill-blank-cells">
											<xsl:with-param name="count"
												select="7 - $cellsInRow" />
										</xsl:call-template>
									</xsl:if>
								</fo:table-row>
							</xsl:for-each>
						</fo:table-body>

					</fo:table>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<xsl:template name="fill-blank-cells">
		<xsl:param name="count" />
		<xsl:if test="$count > 0">
			<fo:table-cell>
				<fo:block />
			</fo:table-cell>
			<xsl:call-template name="fill-blank-cells">
				<xsl:with-param name="count" select="$count - 1" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>