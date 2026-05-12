package org.llin.demo.northwind.util;

import org.llin.demo.northwind.model.entity.EntityObject;

public interface ArrayUtil {
	
	public static void printOut(EntityObject[] boArr) {
		
		for (EntityObject bo : boArr) {
			System.out.println(bo.toString());
		}
		
	}
}
