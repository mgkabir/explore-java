package io.explore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResource {
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/words.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}