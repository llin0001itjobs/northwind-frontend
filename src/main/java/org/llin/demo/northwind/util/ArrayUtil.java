package org.llin.demo.northwind.util;

import org.llin.demo.northwind.model.BaseObject;

public interface ArrayUtil {
	
	public static void printOut(BaseObject[] boArr) {
		
		for (BaseObject bo : boArr) {
			System.out.println(bo.toString());
		}
		
	}
}
