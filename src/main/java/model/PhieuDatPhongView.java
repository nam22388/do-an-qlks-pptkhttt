package model;

import java.sql.Date;

public class PhieuDatPhongView {

    private int maPhieuDatPhong;
    private String tenKhachSan;
    private int soPhong;
    private String tenLoaiPhong;
    private int giaDatPhong;
    private Date ngayDen;
    private Date ngayDi;
    private String trangThai;

    // getter setter
    public int getMaPhieuDatPhong() { return maPhieuDatPhong; }
    public void setMaPhieuDatPhong(int maPhieuDatPhong) { this.maPhieuDatPhong = maPhieuDatPhong; }

    public String getTenKhachSan() { return tenKhachSan; }
    public void setTenKhachSan(String tenKhachSan) { this.tenKhachSan = tenKhachSan; }

    public int getSoPhong() { return soPhong; }
    public void setSoPhong(int soPhong) { this.soPhong = soPhong; }

    public String getTenLoaiPhong() { return tenLoaiPhong; }
    public void setTenLoaiPhong(String tenLoaiPhong) { this.tenLoaiPhong = tenLoaiPhong; }

    public int getGiaDatPhong() { return giaDatPhong; }
    public void setGiaDatPhong(int giaDatPhong) { this.giaDatPhong = giaDatPhong; }

    public Date getNgayDen() { return ngayDen; }
    public void setNgayDen(Date ngayDen) { this.ngayDen = ngayDen; }

    public Date getNgayDi() { return ngayDi; }
    public void setNgayDi(Date ngayDi) { this.ngayDi = ngayDi; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}