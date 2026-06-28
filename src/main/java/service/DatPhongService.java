package service;

import dao.DatPhongDAO;
import model.PhieuDatPhong;
import model.PhieuDatPhongView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DatPhongService {

    private final DatPhongDAO dao = new DatPhongDAO();

    public boolean datPhong(PhieuDatPhong dp) {
        if (dp == null) return false;
        if (dp.getMaKhachHang() <= 0 || dp.getMaPhong() <= 0) return false;
        if (dp.getNgayDen() == null || dp.getNgayDi() == null) return false;

        LocalDate den = dp.getNgayDen().toLocalDate();
        LocalDate di  = dp.getNgayDi().toLocalDate();

        // Ngày đi phải SAU ngày đến
        if (!di.isAfter(den)) return false;

        long soNgay = ChronoUnit.DAYS.between(den, di);

        int giaPhong = dao.getGiaPhongByMaPhong(dp.getMaPhong());
        if (giaPhong <= 0) return false;

        // ✅ FIX: nhân đúng số ngày * giá phòng (bỏ / 2.0 sai trước đây)
        int giaDat = (int) (soNgay * giaPhong);
        dp.setGiaDatPhong(giaDat);

        return dao.insertDatPhong(dp);
    }

    public ArrayList<PhieuDatPhongView> getLichSuByKhachHang(int maKhachHang) {
        return dao.getByKhachHang(maKhachHang);
    }

    public boolean huyDatPhong(int maPhieuDatPhong) {
        return dao.cancelDatPhong(maPhieuDatPhong);
    }
}