package org.llin.demo.northwind.util;

import java.util.ArrayList;
import java.util.List;

public interface StringUtil {
	
	public static final int ellipsisLimit = 50;
	public static final String query_parameter = "=";
	
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
	
	public static String[] csvToArray(String csvData) {
        // Create a List to store the flattened values
        List<String> values = new ArrayList<>();

        // Split the input CSV data into lines (rows)
        String[] rows = csvData.split("\\r?\\n");

        // Iterate over each row
        for (String row : rows) {
            // Split the row into columns (fields) by the comma delimiter
            String[] columns = row.split(",");

            // Add the individual columns to the values list
            for (String column : columns) {
                values.add(column.trim()); // Trim to remove any accidental leading/trailing spaces
            }
        }

        // Convert the list to an array and return it
        return values.toArray(new String[0]);	
	}
	
	public static int populateIdFromLink(String href) throws NumberFormatException {
		String id = "0";
		if (href.contains(query_parameter)) {
			id = href.substring(href.lastIndexOf(query_parameter) + 1);
		} else {
			id = href.substring(href.lastIndexOf("/") + 1);
		}
		return Integer.parseInt(id);
	}
	
	public static String getKeyFromParent(String parentKey, String childKey) {
		return parentKey + "_" + childKey;
	}
	
	
	public static String deCapitalize(String s) {
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}
	
	public static String capitalize(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}	
	
	public static String decamelize(String input) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);

			if (Character.isUpperCase(currentChar)) {
				result.append(' '); // Add a space before uppercase letters
			}

			result.append(currentChar);
		}
		
		char[] ch = { 0 };
		ch[0] = result.charAt(0);
		result = result.replace(0, 1, (new String(ch)).toUpperCase());
		
		return result.toString().trim(); // Trim any leading or trailing spaces
	}

	public static void main(String[] args) {
		String camelCase = "roleType";
		String decamelized = decamelize(camelCase);
		System.out.println(decamelized); // Output: "Role Type"
	}	
}
