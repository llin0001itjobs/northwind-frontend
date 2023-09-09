package org.llin.demo.northwind.controller.util;

import java.io.IOException;
import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class GenericResponseErrorHandler implements ResponseErrorHandler {

	private static final Logger logger = LogManager.getLogger(GenericResponseErrorHandler.class);
	private boolean ok;
	private URI uri;
	private HttpMethod method;
	
	public GenericResponseErrorHandler() {
		// TODO Auto-generated constructor stub
	}

	public GenericResponseErrorHandler(URI uri, HttpMethod method) {
		this.uri = uri;
		this.method = method;
	}
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {	
		HttpStatus status = response.getStatusCode();
		if (status.is1xxInformational() ||
			status.is2xxSuccessful() ||
			status.is3xxRedirection() ||
			status.is4xxClientError()) ok = true;
		if (status.is5xxServerError()) {
			ok = false;
		}
		return ok;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		handleResponse(response);
	}
	
	private void handleResponse(ClientHttpResponse response) throws IOException {
		  if (logger.isDebugEnabled()) {
		    try {
		      int code = response.getRawStatusCode();
		      HttpStatus status = HttpStatus.resolve(code);
		      logger.debug("Response " + (status != null ? status : code));
		    }
		    catch (IOException ex) {
		      // ignore
		    }
		  }
		}


	public URI getUri() {
		return uri;
	}


	public void setUri(URI uri) {
		this.uri = uri;
	}


	public HttpMethod getMethod() {
		return method;
	}


	public void setMethod(HttpMethod method) {
		this.method = method;
	}
	
}
