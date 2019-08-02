package io.explore.jdbc.oracle;

import java.sql.*;
import java.time.Instant;

public class DateTimeExp {
    public static void main(String[] args) {


        try {
            System.out.println(String.format("Instant.now().toString() : %s", Instant.now().toString()));

            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle12c:1521/ORCL", "patient", "patient");

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select FIRSTNAME,BIRTHDAY from PATIENT ");

            while (rs.next()) {

                Object birthdayObj = rs.getObject("BIRTHDAY");
                if (null != birthdayObj) {
                    System.out.println(birthdayObj.toString().trim().replace("00:00:00.0", "").trim());
                }

                String firstName = rs.getString("FIRSTNAME");
                Timestamp birthDayAsTimeStamp = rs.getTimestamp("BIRTHDAY");
                if (null != birthDayAsTimeStamp) {
                    String birthDayStr = birthDayAsTimeStamp.toInstant().toString();
                    System.out.println(String.format("FirstName : %s BirthDay : %s", firstName, birthDayStr));
                } else {
                    System.out.println(String.format("FirstName : %s BirthDay : N/A ", firstName));
                }
            }

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
