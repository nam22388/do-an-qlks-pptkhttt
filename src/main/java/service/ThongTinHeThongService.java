package service;

import dao.ThongTinHeThongDAO;
import model.KhachSan;
import model.LoaiPhong;
import model.TienIch;
import model.UserProfileView;
import java.util.ArrayList;

public class ThongTinHeThongService {

    private final ThongTinHeThongDAO dao;

    public ThongTinHeThongService() {
        this.dao = new ThongTinHeThongDAO();
    }

    // =========================
    // 1. LẤY TẤT CẢ KHÁCH SẠN
    // =========================
    public ArrayList<KhachSan> getAllKhachSan() {
        return dao.getAllKhachSan();
    }

    // =========================
    // 2. SEARCH KHÁCH SẠN
    // =========================
    public ArrayList<KhachSan> searchKhachSan(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return dao.getAllKhachSan();
        }

        return dao.findKhachSanByName(keyword.trim());
    }

    // =========================
    // 3. AUTOCOMPLETE GỢI Ý
    // =========================
    public ArrayList<String> suggestKhachSan(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return dao.suggestKhachSan(keyword.trim());
    }

    // =========================
    // 4. LẤY KHÁCH SẠN THEO ID
    // =========================
    public KhachSan getKhachSanById(int maKhachSan) {

        if (maKhachSan <= 0) return null;

        return dao.getKhachSanById(maKhachSan);
    }

    // =========================
    // 5. LOẠI PHÒNG THEO KHÁCH SẠN
    // =========================
    public ArrayList<LoaiPhong> getLoaiPhongByKhachSan(int maKhachSan) {

        if (maKhachSan <= 0) {
            return new ArrayList<>();
        }

        return dao.getLoaiPhongByKhachSan(maKhachSan);
    }

    // =========================
    // 6. TIỆN ÍCH THEO PHÒNG
    // =========================
    public ArrayList<TienIch> getTienIchByPhong(int maPhong) {

        if (maPhong <= 0) {
            return new ArrayList<>();
        }

        return dao.getTienIchByPhong(maPhong);
    }
    
   
    public UserProfileView getProfile(int maKhachHang) {
        return dao.getProfile(maKhachHang);
    }
}