package JavaFiles;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static User authenticate(String username, String password) {
        String hashedPassword = hashPassword(password);
        String sql = "SELECT * FROM users WHERE username = ? AND hashed_password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);  // Use hashed password for comparison
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getInt("role_id"),
                            rs.getInt("claim_id"),
                            rs.getString("claim_status"),
                            rs.getString("claim_description"),
                            rs.getDate("claim_date_filed"),
                            rs.getDate("claim_date_processed"),
                            rs.getInt("document_id"),
                            rs.getString("original_name"),
                            rs.getString("system_name"),
                            rs.getDate("document_upload_date"),
                            rs.getInt("beneficiary_id"),
                            rs.getString("relationship"),
                            rs.getDouble("dependent_rate"),
                            rs.getInt("log_id"),
                            rs.getString("action"),
                            rs.getString("entity_affected"),
                            rs.getTimestamp("log_timestamp"),
                            rs.getInt("payment_id"),
                            rs.getInt("payment_year"),
                            rs.getDouble("total_amount"),
                            rs.getString("role_name"),
                            rs.getInt("permission_id"),
                            rs.getString("permission")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
