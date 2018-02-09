package io.explore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordStat {

	public static void main(String[] args) throws IOException {

		String file = "src/main/resources/words.txt";

		Stream<String> stream = Files.lines(Paths.get(file));

		List<String> list =  stream.filter(line -> line.startsWith("Th")).map(String::toUpperCase).collect(Collectors.toList());

		stream.close();
		list.forEach(System.out::println);

	}

}
