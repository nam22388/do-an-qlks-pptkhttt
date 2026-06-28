package dao;

import java.sql.*;
import java.util.ArrayList;

import model.PhieuDatPhong;
import model.PhieuDatPhongView;
import utils.TestConnection;

public class DatPhongDAO {

    // =========================
    // CHECK TRÙNG NGÀY
    // =========================
    private boolean isRoomAvailable(Connection conn, int maPhong, Date ngayDen, Date ngayDi)
            throws SQLException {

        String sql =
            "SELECT COUNT(*) FROM PhieuDatPhong " +
            "WHERE maPhong = ? AND trangThai = 'Đã đặt' " +
            "AND NOT (ngayDi <= ? OR ngayDen >= ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhong);
            ps.setDate(2, ngayDen);
            ps.setDate(3, ngayDi);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) == 0; // true = còn trống
        }
    }

    // =========================
    // INSERT ĐẶT PHÒNG
    // =========================
    public boolean insertDatPhong(PhieuDatPhong dp) {

        String insertSql =
            "INSERT INTO PhieuDatPhong " +
            "(maKhachHang, ngayDatPhong, trangThai, ngayDen, ngayDi, maPhong, giaDatPhong) " +
            "VALUES (?,?,?,?,?,?,?)";

        String updateRoom =
            "UPDATE Phong SET tinhTrangPhong = 'Đã đặt' WHERE maPhong = ?";

        try (Connection conn = TestConnection.getJDBCConnection()) {

            conn.setAutoCommit(false);

            if (dp == null || dp.getNgayDen() == null || dp.getNgayDi() == null)
                return false;

            if (dp.getNgayDen().after(dp.getNgayDi()))
                return false;

            // ✅ Check phòng còn trống
            if (!isRoomAvailable(conn, dp.getMaPhong(), dp.getNgayDen(), dp.getNgayDi()))
                return false;

            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setInt(1, dp.getMaKhachHang());
                ps.setDate(2, dp.getNgayDatPhong());
                ps.setString(3, "Đã đặt");
                ps.setDate(4, dp.getNgayDen());
                ps.setDate(5, dp.getNgayDi());
                ps.setInt(6, dp.getMaPhong());
                ps.setInt(7, dp.getGiaDatPhong());
                ps.executeUpdate();
            }

            try (PreparedStatement ps = conn.prepareStatement(updateRoom)) {
                ps.setInt(1, dp.getMaPhong());
                ps.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // GIÁ PHÒNG
    // =========================
    public int getGiaPhongByMaPhong(int maPhong) {

        String sql =
            "SELECT lp.giaPhong " +
            "FROM Phong p JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong " +
            "WHERE p.maPhong = ?";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // =========================
    // LỊCH SỬ
    // =========================
    public ArrayList<PhieuDatPhongView> getByKhachHang(int maKhachHang) {

        ArrayList<PhieuDatPhongView> list = new ArrayList<>();

        String sql =
            "SELECT p.*, ph.soPhong, lp.tenLoaiPhong, ks.tenKhachSan " +
            "FROM PhieuDatPhong p " +
            "JOIN Phong ph ON p.maPhong = ph.maPhong " +
            "JOIN LoaiPhong lp ON ph.maLoaiPhong = lp.maLoaiPhong " +
            "JOIN KhachSan ks ON ph.maKhachSan = ks.maKhachSan " +
            "WHERE p.maKhachHang = ? ORDER BY p.maPhieuDatPhong DESC";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maKhachHang);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhieuDatPhongView dp = new PhieuDatPhongView();
                dp.setMaPhieuDatPhong(rs.getInt("maPhieuDatPhong"));
                dp.setTrangThai(rs.getString("trangThai"));
                dp.setNgayDen(rs.getDate("ngayDen"));
                dp.setNgayDi(rs.getDate("ngayDi"));
                dp.setGiaDatPhong(rs.getInt("giaDatPhong"));
                dp.setSoPhong(rs.getInt("soPhong"));
                dp.setTenLoaiPhong(rs.getString("tenLoaiPhong"));
                dp.setTenKhachSan(rs.getString("tenKhachSan"));
                list.add(dp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // HỦY
    // =========================
    public boolean cancelDatPhong(int maPhieuDatPhong) {

        String getRoom   = "SELECT maPhong FROM PhieuDatPhong WHERE maPhieuDatPhong = ?";
        String updateBooking = "UPDATE PhieuDatPhong SET trangThai = 'Đã hủy' WHERE maPhieuDatPhong = ?";

        // ✅ FIX: chỉ reset phòng về Trống nếu không còn phiếu Đã đặt nào khác cho phòng đó
        String checkOther =
            "SELECT COUNT(*) FROM PhieuDatPhong " +
            "WHERE maPhong = ? AND trangThai = 'Đã đặt' AND maPhieuDatPhong <> ?";

        String updateRoom = "UPDATE Phong SET tinhTrangPhong = 'Trống' WHERE maPhong = ?";

        try (Connection conn = TestConnection.getJDBCConnection()) {

            conn.setAutoCommit(false);

            int maPhong = 0;

            try (PreparedStatement ps = conn.prepareStatement(getRoom)) {
                ps.setInt(1, maPhieuDatPhong);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) maPhong = rs.getInt(1);
            }

            if (maPhong == 0) return false;

            try (PreparedStatement ps = conn.prepareStatement(updateBooking)) {
                ps.setInt(1, maPhieuDatPhong);
                ps.executeUpdate();
            }

            // Chỉ reset phòng nếu không còn phiếu nào khác
            try (PreparedStatement ps = conn.prepareStatement(checkOther)) {
                ps.setInt(1, maPhong);
                ps.setInt(2, maPhieuDatPhong);
                ResultSet rs = ps.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) {
                    try (PreparedStatement ps2 = conn.prepareStatement(updateRoom)) {
                        ps2.setInt(1, maPhong);
                        ps2.executeUpdate();
                    }
                }
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}