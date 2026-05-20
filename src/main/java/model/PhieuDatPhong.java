/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
/**
 *
 * @author LEGION
 */
public class PhieuDatPhong {
    private int maPhieuDatPhong;
    private int maKhachHang;
    private Date ngayDatPhong;
    private String trangThai;
    private Date ngayDen;
    private Date ngayDi;
    private int maPhong;
    private int giaDatPhong;

    public PhieuDatPhong() {
    }

    public PhieuDatPhong(int maPhieuDatPhong, int maKhachHang,
                         Date ngayDatPhong, String trangThai,
                         Date ngayDen, Date ngayDi,
                         int maPhong, int giaDatPhong) {

        this.maPhieuDatPhong = maPhieuDatPhong;
        this.maKhachHang = maKhachHang;
        this.ngayDatPhong = ngayDatPhong;
        this.trangThai = trangThai;
        this.ngayDen = ngayDen;
        this.ngayDi = ngayDi;
        this.maPhong = maPhong;
        this.giaDatPhong = giaDatPhong;
    }

    public int getMaPhieuDatPhong() {
        return maPhieuDatPhong;
    }

    public void setMaPhieuDatPhong(int maPhieuDatPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public Date getNgayDatPhong() {
        return ngayDatPhong;
    }

    public void setNgayDatPhong(Date ngayDatPhong) {
        this.ngayDatPhong = ngayDatPhong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getGiaDatPhong() {
        return giaDatPhong;
    }

    public void setGiaDatPhong(int giaDatPhong) {
        this.giaDatPhong = giaDatPhong;
    }
}
