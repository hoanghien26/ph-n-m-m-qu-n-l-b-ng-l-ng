package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DbConnection {
    
    // Sử dụng driver mới nhất (MySQL Connector/J 8.0+)
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/payroll?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Hien*26102004";
    
    // Không cần lưu connection như biến static
    private DbConnection() {} // Private constructor để ngăn khởi tạo
    
    public static Connection getDbConnection() {
        try {
            // Sửa lại thành getConnection() thay vì getDbConnection()
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
        } catch (SQLException ex) {
            showErrorDialog("Database Connection Error", 
                          "Failed to connect to database:\n" + ex.getMessage());
            return null;
        }
    }
    
    // Phương thức đóng connection an toàn
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                showErrorDialog("Connection Close Error", 
                              "Failed to close connection:\n" + ex.getMessage());
            }
        }
    }
    
    // Phương thức hiển thị lỗi
    private static void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, 
                                    message, 
                                    title, 
                                    JOptionPane.ERROR_MESSAGE);
    }
}