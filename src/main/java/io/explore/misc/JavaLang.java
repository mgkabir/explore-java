package io.explore.misc;

public class JavaLang {
    private static String conditionText = " ExpDotTable.patient.first.name NOT LIKE  'xyz' and ExpDotTable.patient.patient.id >= 5 and ExpDotTable.patient.lastname LIKE 'last' ";
    private static String tableName = "ExpDotTable";
    private static final String QUOTE = "\"";
    private static final String DOT = ".";
    private static final String SINGLE_SPACE = " ";
    private static final String Q_DOT_Q = QUOTE + DOT + QUOTE;

    public static void main(String[] args) {

        String conditionsArray[] = conditionText.split(" and ");
        System.out.println(conditionsArray.length);
        StringBuffer combinedCondition = new StringBuffer();
        short count = 0;
        for (String s : conditionsArray) {
            String trimmedCondition = s.trim();

            StringBuffer quotedCondition = new StringBuffer(trimmedCondition);

            quotedCondition
                    .insert(0, QUOTE)
                    .insert(tableName.length() + 1, QUOTE)
                    .insert(tableName.length() + 3, QUOTE)
                    .insert(trimmedCondition.indexOf(SINGLE_SPACE) + 3, QUOTE);

            combinedCondition.append(quotedCondition);
            if (count < conditionsArray.length - 1) combinedCondition.append(" AND ");
            count++;
        }

        System.out.println("=====================================");
        System.out.println("BEFORE : " + conditionText);
        System.out.println("AFTER : " + combinedCondition);

    }

}
