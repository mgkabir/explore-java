package io.explore.mysql;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MySQL_IMDB {

    public static void main(String[] args) {
        final String query_info_type = "SELECT info FROM info_type ";
        final String query_movie_info = "SELECT info FROM movie_info ";
        long start = System.currentTimeMillis();
        try {
            //Connection con = DriverManager.getConnection("jdbc:mariadb://mysql:3306/imdb?useCursorFetch=true&user=root&password=Abcd1234");
            Connection con = DriverManager.getConnection("jdbc:mariadb://mysql:3306/imdb?user=root&password=Abcd1234");
            runQuery(con, query_movie_info);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception happened after " + (System.currentTimeMillis() - start) / 1000 + " Sec");
        }
        System.out.println("Time Taken to Complete : " + (System.currentTimeMillis() - start) / 1000 + " Sec");
    }

    private static void runQuery(Connection con, String query) throws InterruptedException, SQLException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        if (null != con) {
            stmt = con.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            stmt.setFetchSize(Integer.MIN_VALUE);
            resultSet = stmt.executeQuery();
        }

        NumberFormat nf = new DecimalFormat("###,###,###");
        Random r = new Random();

        if (null != resultSet) {
            int count = 1;
            while (resultSet.next()) {
                count++;
                if (count % 1000 == 0) {
                    int delay = r.ints(0, 300).findFirst().getAsInt();
                    System.out.println(nf.format(count) + " (" + (count * 100 / 19395234) + " %) ... Taking NAP .. " + delay + " mSec .." + resultSet.getObject(1).toString());
                    TimeUnit.MILLISECONDS.sleep(delay);
                }
            }
        }
    }
}
