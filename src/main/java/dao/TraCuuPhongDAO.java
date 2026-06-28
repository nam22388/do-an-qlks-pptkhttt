package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.PhongView;
import utils.TestConnection;

public class TraCuuPhongDAO {

    // =========================
    // LẤY PHÒNG THEO KHÁCH SẠN + LOẠI PHÒNG (FILTER)
    // =========================
    public List<PhongView> getPhongView(int maKhachSan, Integer maLoaiPhong) {

        List<PhongView> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.maPhong, p.soPhong, p.tinhTrangPhong, ");
        sql.append("lp.tenLoaiPhong, lp.giaPhong ");
        sql.append("FROM Phong p ");
        sql.append("JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong ");
        sql.append("WHERE p.maKhachSan = ? ");

        // filter loại phòng
        if (maLoaiPhong != null && maLoaiPhong > 0) {
            sql.append("AND p.maLoaiPhong = ? ");
        }

        try (Connection conn = TestConnection.getJDBCConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            ps.setInt(1, maKhachSan);

            if (maLoaiPhong != null && maLoaiPhong > 0) {
                ps.setInt(2, maLoaiPhong);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PhongView p = new PhongView();
                p.setMaPhong(rs.getInt("maPhong"));
                p.setSoPhong(rs.getInt("soPhong"));
                p.setTinhTrangPhong(rs.getString("tinhTrangPhong"));
                p.setTenLoaiPhong(rs.getString("tenLoaiPhong"));
                p.setGiaPhong(rs.getInt("giaPhong"));

                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // LẤY TẤT CẢ PHÒNG (không filter)
    // =========================
    public List<PhongView> getAllByKhachSan(int maKhachSan) {
        return getPhongView(maKhachSan, null);
    }
}