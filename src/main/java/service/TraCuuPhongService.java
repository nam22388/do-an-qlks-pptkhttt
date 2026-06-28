package service;

import dao.TraCuuPhongDAO;
import model.PhongView;

import java.util.List;

public class TraCuuPhongService {

    private final TraCuuPhongDAO dao;

    public TraCuuPhongService() {
        this.dao = new TraCuuPhongDAO();
    }

    // =========================
    // LẤY PHÒNG THEO KHÁCH SẠN + LOẠI PHÒNG
    // =========================
    public List<PhongView> getPhong(int maKhachSan, Integer maLoaiPhong) {

        if (maKhachSan <= 0) {
            return List.of();
        }

        return dao.getPhongView(maKhachSan, maLoaiPhong);
    }

    // =========================
    // LẤY TẤT CẢ PHÒNG
    // =========================
    public List<PhongView> getAll(int maKhachSan) {

        if (maKhachSan <= 0) {
            return List.of();
        }

        return dao.getAllByKhachSan(maKhachSan);
    }
}