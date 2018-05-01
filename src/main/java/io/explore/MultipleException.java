package io.explore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MultipleException {

	public static void main(String[] files) {/*
		try (FileReader inputFile = new FileReader(new File(files[0]))) { // #1
			inputFile.close(); // #2
		} catch (FileNotFoundException | IOException e) { // #3
			e.printStackTrace();
		}
	*/
		List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
		ints.removeIf(i -> (i % 2 ==0)); // LINE
		System.out.println(ints);	
	}
}
