package io.explore.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.MariaDbConnection;

/* Connect to MySQL database using mariadb connector*/
public class MySQL101 {

    public static void main(String[] args) throws SQLException {
	// TODO Auto-generated method stub
	Connection con = DriverManager.getConnection("jdbc:mariadb://mysql:3306/patient?user=patient&password=patient");
	if (con instanceof MariaDbConnection) {
	    MariaDbConnection mCon = (MariaDbConnection) con;
	    System.out.println(" Maria Con : " + mCon.getLowercaseTableNames());
	    System.out.println(" Maria Con : " + mCon.getCatalog());
	} else {
	    System.out.printf("MariaDB Connector Not used");
	}
    }
}
