package io.explore;

public class Memory {

    public static void main(String[] args) {
	memoryStat();
    }

    private static void memoryStat() {
	Runtime rt = Runtime.getRuntime();
	long maxMemory = rt.maxMemory(); // Xmx
	System.out.println(String.format("MaxMemory = %s  ", maxMemory / (1024*1024) , " MB"));
	long freeMemory = rt.freeMemory();
	System.out.println(String.format("FreeMemory = %s  ", freeMemory / (1024*1024) , " MB"));
	long totalMemory = rt.totalMemory();
	System.out.println(String.format("TotalMemory = %s  ", totalMemory / (1024*1024) , " MB"));
    }
}
