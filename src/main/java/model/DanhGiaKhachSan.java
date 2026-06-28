package model;

import java.sql.Date;

public class DanhGiaKhachSan {

    private int maDanhGia;
    private String noiDung;
    private Date ngayTao;
    private int maKhachHang;
    private int maKhachSan;

    public DanhGiaKhachSan() {
    }

    public DanhGiaKhachSan(int maDanhGia, String noiDung,
                           Date ngayTao, int maKhachHang,
                           int maKhachSan) {

        this.maDanhGia = maDanhGia;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
        this.maKhachHang = maKhachHang;
        this.maKhachSan = maKhachSan;
    }

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(int maKhachSan) {
        this.maKhachSan = maKhachSan;
    }
}