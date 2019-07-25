package io.explore.jdbc.oracle;

import java.sql.*;

public class Oracle101 {
    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle12c:1521/ORCL", "patient", "patient");

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select object_name from user_objects where object_type = 'TABLE'");

            while (rs.next()) {
                String tableName = rs.getString(1);
                System.out.println("Table Name : " + tableName);
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
