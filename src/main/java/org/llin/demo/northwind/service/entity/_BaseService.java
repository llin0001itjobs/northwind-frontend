package org.llin.demo.northwind.service.entity;

import org.llin.demo.northwind.model.entity.links.Link;

public abstract class _BaseService {
	
	Integer extractIdFromSelf(Link self) {
		if (self == null || self.getHref() == null)
			return null;
		String href = self.getHref();
		int slash = href.lastIndexOf('/');
		if (slash < 0)
			return null;
		try {
			return Integer.valueOf(href.substring(slash + 1));
		} catch (NumberFormatException e) {
			return null;
		}		
	}
	
	 Integer extractIdFromLink(Link Link) {
		if (Link == null)
			return null;

		String href = Link.getHref();
		if (href == null)
			return null;

		String idParam = queryParam(href, "id");
		if (idParam != null) {
			try {
				return Integer.valueOf(idParam);
			} catch (NumberFormatException ignored) {
			}
		}

		int last = href.lastIndexOf('/');
		if (last >= 0) {
			try {
				return Integer.valueOf(href.substring(last + 1));
			} catch (NumberFormatException ignored) {
			}
		}
		return null;
	}

	private String queryParam(String href, String name) {
		int q = href.indexOf('?');
		if (q < 0)
			return null;
		for (String part : href.substring(q + 1).split("&")) {
			String[] pair = part.split("=", 2);
			if (pair.length == 2 && pair[0].equals(name))
				return pair[1];
		}
		return null;
	}
	
}
