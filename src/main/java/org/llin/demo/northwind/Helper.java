package org.llin.demo.northwind;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.llin.demo.northwind.model.api.core.BaseObject;

public class Helper<T extends BaseObject> {

	@SuppressWarnings("rawtypes")
	private Set<Class<T[]>> findAllClassesUsingClassLoader(String packageName) {
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		return reader.lines().filter(line -> line.endsWith(".class")).map(line -> getClass(line, packageName))
				.collect(Collectors.toSet());
	}

	@SuppressWarnings("rawtypes")
	private Class getClass(String className, String packageName) {
		try {
			return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
		} catch (ClassNotFoundException e) {
			// handle the exception
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes" })
	private Set<String> setToString(Set<Class<T[]>> classes) {
		return classes.stream().map(c -> c.getSimpleName()).collect(Collectors.toSet());
	}
	
	private Set<String> lowerCaseFirst(Set<String> classes) {
		return classes.stream().map(s -> new StringBuilder(s.substring(0, 1).toLowerCase() + s.substring(1)).toString())
				.collect(Collectors.toSet());
	}

	private Set<String> removeExcluded(Set<String> classes, List<String> list) {
		return classes.stream().filter(s -> (!list.contains(s))).collect(Collectors.toSet());
	}	
}
