package com.example.myapplication.model;

public class MonHoc {
    private String maMH;
    private String tenMH;
    private int hocKyMH;
    private boolean selected = false;

    public MonHoc() {
    }

    public MonHoc(String maMH, String tenMH, int hocKyMH) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.hocKyMH = hocKyMH;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public int getHocKyMH() {
        return hocKyMH;
    }

    public void setHocKyMH(int hocKyMH) {
        this.hocKyMH = hocKyMH;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
