package io.explore;

import java.util.Random;

public class RandomNumber {

	public static void main(String[] args) {
		Random r = new Random();
		r.ints()
		.filter(n->n > 0) // postive
		.limit(10)	// only 10
		.sorted()		
		.forEach(System.out::println);
	}

}
