package io.explore;

import java.util.Arrays;
import java.util.Spliterator;

public class Java8Spliterator {

	public static void main(String[] args) {
		Spliterator<String> split = Arrays.asList("Australia","Brazil").spliterator();
		split.tryAdvance(System.out::print);
		split.tryAdvance(System.out::print);
		split.tryAdvance(System.out::print);
	}

}
