package org.llin.demo.northwind.controller.util;

import java.net.URI;
import org.springframework.http.HttpMethod;

public class GETResponseErrorHandler extends GenericResponseErrorHandler {

	public GETResponseErrorHandler(URI uri) {
		super(uri, HttpMethod.GET);
	}

}
