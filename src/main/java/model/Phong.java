/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LEGION
 */
public class Phong {
    private int maPhong;
    private int soPhong;
    private String tinhTrangPhong;
    private int maLoaiPhong;
    private int maKhachSan;

    public Phong() {
    }

    public Phong(int maPhong, int soPhong, String tinhTrangPhong,
                 int maLoaiPhong, int maKhachSan) {
        this.maPhong = maPhong;
        this.soPhong = soPhong;
        this.tinhTrangPhong = tinhTrangPhong;
        this.maLoaiPhong = maLoaiPhong;
        this.maKhachSan = maKhachSan;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public String getTinhTrangPhong() {
        return tinhTrangPhong;
    }

    public void setTinhTrangPhong(String tinhTrangPhong) {
        this.tinhTrangPhong = tinhTrangPhong;
    }

    public int getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(int maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public int getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(int maKhachSan) {
        this.maKhachSan = maKhachSan;
    }
}
