/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LEGION
 */
public class ChiTietTienIchPhong {
    private int maPhong;
    private int maTienIch;

    public ChiTietTienIchPhong() {
    }

    public ChiTietTienIchPhong(int maPhong, int maTienIch) {
        this.maPhong = maPhong;
        this.maTienIch = maTienIch;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getMaTienIch() {
        return maTienIch;
    }

    public void setMaTienIch(int maTienIch) {
        this.maTienIch = maTienIch;
    }
}
