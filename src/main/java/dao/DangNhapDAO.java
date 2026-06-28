package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utils.TestConnection;

public class DangNhapDAO {

    public User login(String tenUser, String matKhau) {

        String sql = "SELECT * FROM `User` WHERE tenUser=? AND matKhau=?";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenUser);
            ps.setString(2, matKhau);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    User u = new User();

                    u.setMaUser(rs.getInt("maUser"));
                    u.setTenUser(rs.getString("tenUser"));
                    u.setMatKhau(rs.getString("matKhau"));
                    u.setMaKhachHang(rs.getInt("maKhachHang"));

                    return u;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}