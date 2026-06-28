package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.KhachHang;
import model.User;
import utils.TestConnection;

public class DangKyDAO {

    // =========================
    // Thêm khách hàng và trả về ID
    // =========================
    public int insertKhachHang(Connection conn, KhachHang kh) {

        String sql = "INSERT INTO KhachHang(tenKhachHang, soCCCD, soDienThoai, email) VALUES(?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, kh.getTenKhachHang());
            ps.setString(2, kh.getSoCCCD());
            ps.setString(3, kh.getSoDienThoai());
            ps.setString(4, kh.getEmail());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // =========================
    // Thêm user
    // =========================
    public boolean insertUser(Connection conn, User user) {

        String sql = "INSERT INTO `User`(tenUser, matKhau, maKhachHang) VALUES(?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getTenUser());
            ps.setString(2, user.getMatKhau());
            ps.setInt(3, user.getMaKhachHang());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // Check username tồn tại
    // =========================
    public boolean checkTenUser(String tenUser) {

        String sql = "SELECT 1 FROM `User` WHERE tenUser = ?";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenUser);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // REGISTER (QUAN TRỌNG - có transaction)
    // =========================
    public boolean register(KhachHang kh, User user) {

        Connection conn = null;

        try {
            conn = TestConnection.getJDBCConnection();
            conn.setAutoCommit(false); // bắt đầu transaction

            // 1. Insert khách hàng
            int maKH = insertKhachHang(conn, kh);

            if (maKH == -1) {
                conn.rollback();
                return false;
            }

            // 2. set FK cho user
            user.setMaKhachHang(maKH);

            // 3. Insert user
            boolean ok = insertUser(conn, user);

            if (!ok) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}