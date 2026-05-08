package org.llin.demo.northwind.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Cache ModelViewCache stores Class<T[]> based on keys of Class name eg.
 * Authentications, Company...
 * 
 * @param <T>
 */
@Configuration
@ConfigurationProperties(prefix = "northwind")
public class PropertiesConfig {

	private String dataApiUri;
	private String dataApiUriId;
	private String entitiesFileJson;
	private boolean entitiesAddlistSubpath;		
	private String regexApiUri;
	private String entitiesCustomSqlKeys;
	private String[] customSqlKeys;
	private Integer employeeChunkSize;
	private String imageBasePath;
	
	public String getDataApiUri() {
		return dataApiUri;
	}

	public void setDataApiUri(String dataApiUri) {
		this.dataApiUri = dataApiUri;
	}
	
	public String getDataApiUriId() {
		return dataApiUriId;
	}

	public void setDataApiUriId(String dataApiUriId) {
		this.dataApiUriId = dataApiUriId;
	}

	public boolean isEntitiesAddlistSubpath() {
		return entitiesAddlistSubpath;
	}

	public void setEntitiesAddlistSubpath(boolean entitiesAddlistSubpath) {
		this.entitiesAddlistSubpath = entitiesAddlistSubpath;
	}

	public String getEntitiesFileJson() {
		return entitiesFileJson;
	}

	public void setEntitiesFileJson(String entitiesFileJson) {
		this.entitiesFileJson = entitiesFileJson;
	}

	public String getRegexApiUri() {
		return regexApiUri;
	}

	public void setRegexApiUri(String regexApiUri) {
		this.regexApiUri = regexApiUri;
	}
	
	public String getEntitiesCustomSqlKeys() {
		return entitiesCustomSqlKeys;
	}

	public void setEntitiesCustomSqlKeys(String entitiesCustomSqlKeys) {
		this.entitiesCustomSqlKeys = entitiesCustomSqlKeys;
	}

	public Integer getEmployeeChunkSize() {
		return employeeChunkSize;
	}

	public void setEmployeeChunkSize(Integer employeeChunkSize) {
		this.employeeChunkSize = employeeChunkSize;
	}
	
	public String getImageBasePath() {
		return imageBasePath;
	}

	public void setImageBasePath(String imageBasePath) {
		this.imageBasePath = imageBasePath;
	}
	
	public String[] getCustomSqlKeys() {
		if (customSqlKeys == null) {
			customSqlKeys = entitiesCustomSqlKeys.split(",");
		}
		return customSqlKeys;
	}



}
