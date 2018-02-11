package io.explore;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Arrays;

public class WordStat {

	public static void main(String[] args) throws IOException {

		String file = "src/main/resources/words.txt";

		long totalWordCount = Files.lines(Paths.get(file)).flatMap(line -> Arrays.stream(line.split(" "))).count();
		System.out.println("Total Words : " + totalWordCount);
		long distinctWordCount = Files.lines(Paths.get(file)).flatMap(line -> Arrays.stream(line.split(" "))).distinct()
				.count();
		System.out.println("Distinct Words : " + distinctWordCount);
		
		long largeWordCount = Files.lines(Paths.get(file)).flatMap(line -> Arrays.stream(line.split(" ")))
				.filter(w -> w.length() >= 7).count();
		System.out.println("Words with >=7 letters : " + largeWordCount);
		
		long smallWordCount = Files.lines(Paths.get(file)).flatMap(line -> Arrays.stream(line.split(" ")))
				.filter(w -> w.length() < 7).count();
		System.out.println("Words with < 7 letters : " + smallWordCount);
		
		double avgLength = Files.lines(Paths.get(file)).flatMap(line -> Arrays.stream(line.split(" "))).mapToLong(w->w.length()).average().orElse(0);
				
		System.out.println("Average Word Count : " + NumberFormat.getInstance().format(avgLength));
	}

}
