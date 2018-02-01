package io.explore;

import java.util.HashMap;
import java.util.Map;

public class Java8Map {

	public static void main(String[] args) {
		mapTest();
	}

	private static void mapTest() {
		Map<String, String> map = initializeMap();
		displayMap(map);

		System.out.println("\n replaceAll() ");
		map.replaceAll((k, v) -> k.toLowerCase() + v.length());
		displayMap(map);

		System.out.println("\n computeIfPresent() ");
		map.computeIfPresent("B", (k, v) -> {
			return k + v;
		});
		displayMap(map);
		
		System.out.println("\n computeIfAbsent() ");
		map.computeIfAbsent("Z", k -> k +" : "+k.length());
		displayMap(map);
	}

	private static Map<String, String> initializeMap() {
		Map<String, String> map = new HashMap<>();
		map.put("D", "d");
		map.put("A", "a");
		map.put("C", "c");
		return map;
	}

	private static void displayMap(Map<String, String> map) {
		System.out.println("\t\tElements : ");
		map.forEach((k, v) -> {
			System.out.println("\tKey : " + k + "\t\tValue : " + v);
		});
	}
}
