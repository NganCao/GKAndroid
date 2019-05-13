package com.example.myapplication.model;

import java.io.Serializable;
import java.util.Date;

public class SinhVien implements Serializable {

    private static final long serialVersion = 1L;
    private String masv;
    private String ho;
    private String ten;
    private String phai;
    private String noisinh;
    private String ngaysinh;
    private String malop;

    public SinhVien() {
    }

    public SinhVien(String masv, String ho, String ten, String phai, String noisinh, String ngaysinh, String malop) {
        this.masv = masv;
        this.ho = ho;
        this.ten = ten;
        this.phai = phai;
        this.noisinh = noisinh;
        this.ngaysinh = ngaysinh;
        this.malop = malop;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }
}
