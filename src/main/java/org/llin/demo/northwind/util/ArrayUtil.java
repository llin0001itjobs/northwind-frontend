package org.llin.demo.northwind.util;

import org.llin.demo.northwind.model.entity._EntityObject;

public interface ArrayUtil {
	
	public static void printOut(_EntityObject[] boArr) {
		
		for (_EntityObject bo : boArr) {
			System.out.println(bo.toString());
		}
		
	}
}
