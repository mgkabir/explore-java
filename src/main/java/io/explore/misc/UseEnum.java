package io.explore.misc;

public class UseEnum {
    private static final double NUM1 = 4;
    private static final double NUM2 = 3;

    public static void main(String[] args) {
        for (Operation op : Operation.values()) {
            System.out.println(NUM1 + " " + op.getSymbol() + " " + NUM2 + " = " + op.apply(4, 3));
        }

        System.out.println(Operation.TIME.toString());

        System.out.println(Operation.valueOf(Operation.TIME.toString()).getSymbol());
    }
}
