package io.explore.python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaPython {
    private static String pyScriptShowNum = "src/main/resources/numbers.py";
    private static String pyScriptAddNum = "src/main/resources/add_numbers.py";
    public static void main(String args[]) throws Exception {
        long start = System.currentTimeMillis();
        printToConsole(runPythonInProcess(pyScriptShowNum));
        writeResultToFile(runPythonInProcess(pyScriptShowNum),"displayNum.txt");
        printToConsole(runPythonInProcess(pyScriptAddNum, "7", "9"));
        writeResultToFile(runPythonInProcess(pyScriptAddNum,"7","9"),"addNum.txt");
        System.out.println(String.format("Time taken : %s ms", System.currentTimeMillis() - start));
    }

    private static BufferedReader runPythonInProcess(String pySource, String... param) throws Exception {

        ProcessBuilder pb = new ProcessBuilder();
        if(param.length != 2){
            pb.command("python",pySource);
        }else{
            pb.command("python",pySource,param[0],param[1]);
        }

        Process p = pb.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return br;
    }

    private static void printToConsole(BufferedReader br) {
        br.lines().forEach(line -> {
            System.out.println(line);
        });
    }

    private static void writeResultToFile(BufferedReader br, String fileName) throws IOException {
        StringBuffer strBuff = new StringBuffer();

        br.lines().forEach(line -> {
            strBuff.append(line).append(" ");
        });

        Path path = Paths.get(fileName);
        Files.write(path, strBuff.toString().getBytes());
    }
}