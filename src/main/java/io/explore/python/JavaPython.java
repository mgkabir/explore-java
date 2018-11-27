package io.explore.python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaPython {

    public static void main(String args[]) throws Exception {
        long start = System.currentTimeMillis();
        printToConsole(runPythonInProcess());
        writeResultToFile(runPythonInProcess());
        System.out.println(String.format("Time taken : %s ms", System.currentTimeMillis() - start));
    }

    private static BufferedReader runPythonInProcess() throws Exception {

        ProcessBuilder pb = new ProcessBuilder("python","src/main/resources/numbers.py");
        Process p = pb.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return br;
    }

    private static void printToConsole(BufferedReader br) {
        br.lines().forEach(line -> {
            System.out.println(line);
        });
    }

    private static void writeResultToFile(BufferedReader br) throws IOException {
        StringBuffer strBuff = new StringBuffer();
        br.lines().forEach(line -> {
            strBuff.append(line).append(" ");
        });

        Path path = Paths.get("result.txt");
        Files.write(path, strBuff.toString().getBytes());
    }
}
