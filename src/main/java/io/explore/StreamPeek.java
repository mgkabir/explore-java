package io.explore;

import java.util.stream.Stream;

public class StreamPeek {

	public static void main(String[] args) {
		Stream.of("ace", "jack", "queen", "king", "joker")
		.peek(System.out::println)
		.mapToInt(card -> card.length())
		.filter(len -> len > 3)
		.peek(System.out::println)
		.limit(2).forEach(System.out::println);
	}

}