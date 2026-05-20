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
public class DanhGiaKhachSan {
    private int maDanhGia;
    private String comment;
    private Date ngayTao;
    private int maKhachHang;
    private int maKhachSan;

    public DanhGiaKhachSan() {
    }

    public DanhGiaKhachSan(int maDanhGia, String comment,
                           Date ngayTao, int maKhachHang,
                           int maKhachSan) {

        this.maDanhGia = maDanhGia;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
