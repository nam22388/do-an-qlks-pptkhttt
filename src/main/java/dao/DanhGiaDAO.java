package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DanhGiaKhachSan;
import utils.TestConnection;

public class DanhGiaDAO {

    // =========================
    // Thêm bình luận
    // =========================
    public boolean themDanhGia(DanhGiaKhachSan dg) {

        String sql = "INSERT INTO DanhGiaKhachSan(noiDung, ngayTao, maKhachHang, maKhachSan) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dg.getNoiDung());
            ps.setDate(2, dg.getNgayTao());
            ps.setInt(3, dg.getMaKhachHang());
            ps.setInt(4, dg.getMaKhachSan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // Lấy danh sách bình luận theo khách sạn
    // =========================
    public ArrayList<DanhGiaKhachSan> getDanhGiaByKhachSan(int maKhachSan) {

        ArrayList<DanhGiaKhachSan> list = new ArrayList<>();

        String sql = "SELECT * FROM DanhGiaKhachSan WHERE maKhachSan = ? ORDER BY ngayTao DESC";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maKhachSan);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(mapDanhGia(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // Xóa bình luận
    // =========================
    public boolean xoaDanhGia(int maDanhGia) {

        String sql = "DELETE FROM DanhGiaKhachSan WHERE maDanhGia = ?";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDanhGia);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // Mapping
    // =========================
    private DanhGiaKhachSan mapDanhGia(ResultSet rs) throws SQLException {

        DanhGiaKhachSan dg = new DanhGiaKhachSan();

        dg.setMaDanhGia(rs.getInt("maDanhGia"));
        dg.setNoiDung(rs.getString("noiDung"));
        dg.setNgayTao(rs.getDate("ngayTao"));
        dg.setMaKhachHang(rs.getInt("maKhachHang"));
        dg.setMaKhachSan(rs.getInt("maKhachSan"));

        return dg;
    }
}