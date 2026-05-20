/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class KhachSan {
    private int maKhachSan;
    private String tenKhachSan;
    private String diaChi;
    private String thanhPho;

    public KhachSan() {
    }

    public KhachSan(int maKhachSan, String tenKhachSan, String diaChi, String thanhPho) {
        this.maKhachSan = maKhachSan;
        this.tenKhachSan = tenKhachSan;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
    }

    public int getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(int maKhachSan) {
        this.maKhachSan = maKhachSan;
    }

    public String getTenKhachSan() {
        return tenKhachSan;
    }

    public void setTenKhachSan(String tenKhachSan) {
        this.tenKhachSan = tenKhachSan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }
}
