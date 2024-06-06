package dev.sandeep.EcomProductServiceDec23.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class GenerateAndInsertProducts {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sample";
    private static final String USER = "root";
    private static final String PASS = "12345678";
    private static final String CATEGORY_ID = "37d847e023ef11efbb94635890cb46fc";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Loop to generate and execute 1000 insert statements
            for (int i = 0; i < 1000; i++) {
                String id = UUID.randomUUID().toString().replace("-", "");
                double price = 10 + (990 * Math.random());
                double rating = 1 + (4 * Math.random());
                String description = "Sample product description";
                String imageurl = "http://example.com/image" + i + ".jpg";
                String title = "Sample Product " + i;

                // SQL insert statement
                String sql = "INSERT INTO `product` (`price`, `rating`, `created_at`, `updated_at`, `category_id`, `id`, `description`, `imageurl`, `title`) "
                        + "VALUES (?, ?, NOW(6), NOW(6), UNHEX(?), UNHEX(?), ?, ?, ?)";

                pstmt = conn.prepareStatement(sql);
                pstmt.setDouble(1, price);
                pstmt.setDouble(2, rating);
                pstmt.setString(3, CATEGORY_ID);
                pstmt.setString(4, id);
                pstmt.setString(5, description);
                pstmt.setString(6, imageurl);
                pstmt.setString(7, title);

                // Execute the insert statement
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

