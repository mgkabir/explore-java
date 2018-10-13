package io.explore;

public class NumberTest {

    public static void main(String[] args) {
	Double dNum = new Double("45.0");
	
	Integer intNum = dNum.intValue();
	
	String strInt = intNum.toString();
	
	System.out.println(strInt);

    }

}
