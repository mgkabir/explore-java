package io.explore;

public class VarArgs {
	public static void main(String... args) {
		for(String arg:args)
		System.out.println("Hello "+arg);
	}
}
