package io.explore.misc;

public class JavaLang {
    private static String conditionText = " ExpDotTable.patient.firstname NOT LIKE  'xyz' ";
    private static String tableName = "ExpDotTable";
    private static final String QUOTE = "\"";
    private static final String DOT = ".";
    private static final String SINGLE_SPACE = " ";
    private static final String Q_DOT_Q = QUOTE + DOT + QUOTE;

    public static void main(String[] args) {
        String trimmedCondition = conditionText.trim();
        StringBuffer quotedCondition = new StringBuffer(trimmedCondition);

        quotedCondition
                .insert(0, QUOTE)
                .insert(tableName.length() + 1, QUOTE)
                .insert(tableName.length() + 3, QUOTE)
                .insert(trimmedCondition.indexOf(SINGLE_SPACE) + 3, QUOTE);

        System.out.println("BEFORE :" + conditionText);
        System.out.println("Trimmed :" + trimmedCondition);
        System.out.println("AFTER :" + quotedCondition);

        System.out.println("================================================");

        String dboTableColumn = "\"PATIENT\".\"INSURANCE\".\"COMPANY.NAME\" ";
        System.out.println(dboTableColumn);
        String[] splits = dboTableColumn.split(Q_DOT_Q);

        for (String s : splits) {
            System.out.println(s.replace("\"",""));
        }

    }

    // "PATIENT"."INSURANCE"."COMPANY.NAME"

}
