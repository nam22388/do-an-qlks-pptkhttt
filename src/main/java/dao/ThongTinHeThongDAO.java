package dao;

import java.sql.*;
import java.util.ArrayList;

import model.KhachSan;
import model.LoaiPhong;
import model.TienIch;
import utils.TestConnection;

public class ThongTinHeThongDAO {

    // =========================
    // 1. LẤY TẤT CẢ KHÁCH SẠN
    // =========================
    public ArrayList<KhachSan> getAllKhachSan() {

        ArrayList<KhachSan> list = new ArrayList<>();

        String sql = "SELECT * FROM KhachSan ORDER BY tenKhachSan ASC";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapKhachSan(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // 2. SEARCH KHÁCH SẠN (THÔNG MINH HƠN)
    // =========================
    public ArrayList<KhachSan> findKhachSanByName(String keyword) {

        ArrayList<KhachSan> list = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllKhachSan();
        }

        String sql =
            "SELECT * FROM KhachSan " +
            "WHERE LOWER(tenKhachSan) LIKE LOWER(?) " +
            "OR LOWER(thanhPho) LIKE LOWER(?) " +
            "ORDER BY tenKhachSan ASC";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String k = "%" + keyword.trim() + "%";

            ps.setString(1, k);
            ps.setString(2, k);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapKhachSan(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // 3. AUTOCOMPLETE (GỢI Ý SEARCH)
    // =========================
    public ArrayList<String> suggestKhachSan(String keyword) {

        ArrayList<String> list = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return list;
        }

        String sql =
            "SELECT tenKhachSan FROM KhachSan " +
            "WHERE tenKhachSan LIKE ? " +
            "ORDER BY tenKhachSan ASC LIMIT 5";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, keyword.trim() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("tenKhachSan"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // 4. LẤY KHÁCH SẠN THEO ID
    // =========================
    public KhachSan getKhachSanById(int maKhachSan) {

        String sql = "SELECT * FROM KhachSan WHERE maKhachSan = ?";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maKhachSan);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapKhachSan(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // 5. LOẠI PHÒNG THEO KHÁCH SẠN
    // =========================
    public ArrayList<LoaiPhong> getLoaiPhongByKhachSan(int maKhachSan) {

        ArrayList<LoaiPhong> list = new ArrayList<>();

        String sql =
            "SELECT DISTINCT lp.maLoaiPhong, lp.tenLoaiPhong, lp.giaPhong " +
            "FROM LoaiPhong lp " +
            "JOIN Phong p ON lp.maLoaiPhong = p.maLoaiPhong " +
            "WHERE p.maKhachSan = ?";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maKhachSan);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapLoaiPhong(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // 6. TIỆN ÍCH THEO PHÒNG
    // =========================
    public ArrayList<TienIch> getTienIchByPhong(int maPhong) {

        ArrayList<TienIch> list = new ArrayList<>();

        String sql =
            "SELECT ti.maTienIch, ti.tenTienIch " +
            "FROM TienIch ti " +
            "JOIN ChiTietTienIchPhong ct ON ti.maTienIch = ct.maTienIch " +
            "WHERE ct.maPhong = ?";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhong);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapTienIch(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public model.UserProfileView getProfile(int maKhachHang) {

        String sql =
            "SELECT u.tenUser, u.matKhau, " +
            "kh.tenKhachHang, kh.soDienThoai, kh.email " +
            "FROM User u " +
            "JOIN KhachHang kh ON u.maKhachHang = kh.maKhachHang " +
            "WHERE u.maKhachHang = ?";

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maKhachHang);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                model.UserProfileView vp = new model.UserProfileView();

                vp.setUsername(rs.getString("tenUser"));
                vp.setPassword(rs.getString("matKhau"));

                vp.setHoTen(rs.getString("tenKhachHang"));
                vp.setSoDienThoai(rs.getString("soDienThoai"));
                vp.setEmail(rs.getString("email"));

                return vp;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // MAPPING
    // =========================
    private KhachSan mapKhachSan(ResultSet rs) throws SQLException {
        KhachSan ks = new KhachSan();
        ks.setMaKhachSan(rs.getInt("maKhachSan"));
        ks.setTenKhachSan(rs.getString("tenKhachSan"));
        ks.setDiaChi(rs.getString("diaChi"));
        ks.setThanhPho(rs.getString("thanhPho"));
        ks.setAnh(rs.getString("anh"));
        return ks;
    }

    private LoaiPhong mapLoaiPhong(ResultSet rs) throws SQLException {
        LoaiPhong lp = new LoaiPhong();
        lp.setMaLoaiPhong(rs.getInt("maLoaiPhong"));
        lp.setTenLoaiPhong(rs.getString("tenLoaiPhong"));
        lp.setGiaPhong(rs.getInt("giaPhong"));
        return lp;
    }

    private TienIch mapTienIch(ResultSet rs) throws SQLException {
        TienIch ti = new TienIch();
        ti.setMaTienIch(rs.getInt("maTienIch"));
        ti.setTenTienIch(rs.getString("tenTienIch"));
        return ti;
    }
}