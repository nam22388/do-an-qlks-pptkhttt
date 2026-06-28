package service;

import java.sql.Date;
import java.util.ArrayList;

import dao.DanhGiaDAO;
import model.DanhGiaKhachSan;

public class DanhGiaService {

    private final DanhGiaDAO danhGiaDAO;

    public DanhGiaService() {
        danhGiaDAO = new DanhGiaDAO();
    }

    // =========================
    // Thêm đánh giá
    // =========================
    public boolean themDanhGia(DanhGiaKhachSan dg) {

        if (dg == null) {
            return false;
        }

        if (dg.getNoiDung() == null || dg.getNoiDung().trim().isEmpty()) {
            return false;
        }

        if (dg.getMaKhachHang() <= 0 || dg.getMaKhachSan() <= 0) {
            return false;
        }

        // Nếu chưa có ngày tạo thì set tự động
        if (dg.getNgayTao() == null) {
            dg.setNgayTao(new Date(System.currentTimeMillis()));
        }

        return danhGiaDAO.themDanhGia(dg);
    }

    // =========================
    // Lấy danh sách đánh giá theo khách sạn
    // =========================
    public ArrayList<DanhGiaKhachSan> getDanhGiaByKhachSan(int maKhachSan) {

        if (maKhachSan <= 0) {
            return new ArrayList<>();
        }

        return danhGiaDAO.getDanhGiaByKhachSan(maKhachSan);
    }

    // =========================
    // Xóa đánh giá
    // =========================
    public boolean xoaDanhGia(int maDanhGia) {

        if (maDanhGia <= 0) {
            return false;
        }

        return danhGiaDAO.xoaDanhGia(maDanhGia);
    }
}