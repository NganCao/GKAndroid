package com.example.myapplication.model;

import java.io.Serializable;

public class Diem implements Serializable {
    private static final long serialVersion = 1L;
    private String masv;
    private String mamh;
    private float diem;

    public Diem() {
    }

    public Diem(String masv, String mamh, float diem) {
        this.masv = masv;
        this.mamh = mamh;
        this.diem = diem;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
}
