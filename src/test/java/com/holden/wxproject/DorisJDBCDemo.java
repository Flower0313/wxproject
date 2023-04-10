package com.holden.wxproject;

/**
 * @ClassName wxproject-DorisJDBCDemo
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年3月09日17:22 - 周四
 * @Describe
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DorisJDBCDemo {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL_PATTERN = "jdbc:mysql://8.130.49.69:3306/spider_base?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8";
    private static final String HOST = "8.130.49.69"; // Leader Node host
    private static final int PORT = 3306;   // query_port of Leader Node
    private static final String DB = "spider_base";
    private static final String TBL = "df_a_stock_detail";
    private static final String USER = "root";
    private static final String PASSWD = "w654646";

    private static final int INSERT_BATCH_SIZE = 10000;

    public static void main(String[] args) {
        insert();
    }

    private static void insert() {
        // 注意末尾不要加 分号 ";"
        String query = "insert into " + TBL + " values(?, ?)";
        // 设置 Label 以做到幂等。
        // String query = "insert into " + TBL + " WITH LABEL my_label values(?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        String dbUrl = String.format(DB_URL_PATTERN, HOST, PORT, DB);
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(dbUrl, USER, PASSWD);
            stmt = conn.prepareStatement(query);

            for (int i =0; i < INSERT_BATCH_SIZE; i++) {
                stmt.setInt(1, i);
                stmt.setInt(2, i * 100);
                stmt.addBatch();
            }

            int[] res = stmt.executeBatch();
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
