import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

/**
 * Created by jeff on 3/24/17.
 */
public class DBConnection {
    public static final String dbUrl = "jdbc:mysql://localhost:3306/store?autoReconnect=true&useSSL=false";
    public static final String dbUser = "storeuser";
    public static final String dbPassword = "*fad!@plo*";

    public static int dbGetRecordCountForTable(String table) {
        try {
            Connection con = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement myStmt = con.createStatement();
            ResultSet rs = myStmt.executeQuery("SELECT COUNT(*) FROM " + table);
            while (rs.next()) {
                return rs.getInt(1);
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return -1;
    }

    public static ResultSet dbGetColumnDataFromTable(String table, String columns) {
        try {
            Connection con = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement myStmt = con.createStatement();
            return myStmt.executeQuery("SELECT " + columns + " FROM " + table);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static ResultSet dbSelectAllFromTable(String table) {
        try {
            Connection con = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement myStmt = con.createStatement();
            return myStmt.executeQuery("SELECT * FROM " + table);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static ResultSet dbSelectAllFromTableWhere(String table, String constraint) {
        try {
            Connection con = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement myStmt = con.createStatement();
            return myStmt.executeQuery("SELECT * FROM " + table + " WHERE " + constraint);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static ResultSet dbSelectAllFromTableOrderBy(String table, String orderBy) {
        try {
            Connection con = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement myStmt = con.createStatement();
            return myStmt.executeQuery("SELECT * FROM " + table + " ORDER BY " + orderBy);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static boolean dbInsertInto(String tableAndCols, String values) {
        try {
            Connection con = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement myStmt = con.createStatement();
            DecimalFormat dec = new DecimalFormat("#.00");
            myStmt.executeUpdate("INSERT INTO " + tableAndCols + " VALUES (" + values + ")");
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }

    public static boolean dbUpdateRecord(String table, String setValue, String constraint) {
        try {
            Connection con = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement myStmt = con.createStatement();
            DecimalFormat dec = new DecimalFormat("#.00");
            myStmt.executeUpdate("UPDATE " + table + " SET " + setValue + " WHERE " + constraint);
            return true;
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }
}
