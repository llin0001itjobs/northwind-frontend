package org.llin.demo.northwind.util;

public interface StringUtil {
	
	public static final int ellipsisLimit = 50;
	
	public static String addEllipsis(String value) {
		return addEllipsis(value, ellipsisLimit);
	}	
	
	public static String addEllipsis(String value, int limit) {
		String s = value;
		if (s.length() >= limit) {
			int indexOfLastSpace = s.lastIndexOf(" ", limit);
			s = s.substring(0, indexOfLastSpace);
			s += "...";
		}
		return s;
	}
	
	public static int getId(String href) throws NumberFormatException {
		String id = "0";
		id = id.substring(id.lastIndexOf("/") + 1);
		return Integer.parseInt(id);
	}
}
