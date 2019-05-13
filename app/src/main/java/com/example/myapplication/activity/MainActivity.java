package com.example.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.data.DBManager_Lop;
import com.example.myapplication.model.Diem;
import com.example.myapplication.model.Lop;
import com.example.myapplication.model.MonHoc;
import com.example.myapplication.model.SinhVien;

public class MainActivity extends AppCompatActivity {

    ImageButton btnLop, btnMonhoc;
    //ImageButton btnSV, btnDiem;

    private DBManager_Lop dbManager_lop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager_lop = new DBManager_Lop(this);
        insetTinhLop();
        insetTinhMonHoc();
        insetTinhSinhVien();
        insetTinhDiem();
        setControl();
        setEvent();
    }

    public void setControl(){
        btnLop = (ImageButton) findViewById(R.id.btnLop);
        btnMonhoc = (ImageButton) findViewById(R.id.btnMH);
//        btnSV = (ImageButton) findViewById(R.id.btnSV);
//        btnDiem = (ImageButton) findViewById(R.id.btnDiem);
    }

    public void setEvent(){

        btnLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LopActivity.class);
                startActivity(intent);
            }
        });

        btnMonhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MonHocActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insetTinhLop(){
        Lop lop1 = new Lop("cntt", "cong nghe thong tin");
        Lop lop2 = new Lop("mmt", "mang may tinh");
        dbManager_lop.addLop(lop1);
        dbManager_lop.addLop(lop2);
    }
    private void insetTinhMonHoc(){
        MonHoc monHoc1 = new MonHoc("trr", "toan roi rac", 1);
        MonHoc monHoc2 = new MonHoc("xstk", "xac suat thong ke", 2);
        MonHoc monHoc3 = new MonHoc("csdl", "co so du lieu", 1);
        dbManager_lop.insertMonHoc(monHoc1);
        dbManager_lop.insertMonHoc(monHoc2);
        dbManager_lop.insertMonHoc(monHoc3);
    }
    private void insetTinhSinhVien(){
        SinhVien sinhVien = new SinhVien("n072", "luong", "hien", "nu", "dong nai", "21/5/2000", "cp01");
        SinhVien sv2 = new SinhVien("n073", "luong", "hien", "nam", "dong nai", "21/5/2000", "cp01");
        SinhVien sv3 = new SinhVien("n074", "luong", "hien", "nu", "dong nai", "21/5/2000", "cp01");
        SinhVien sv4 = new SinhVien("n075", "luong", "hien", "nam", "dong nai", "21/5/2000", "cp01");
        dbManager_lop.addSV(sinhVien);
        dbManager_lop.addSV(sv2);
        dbManager_lop.addSV(sv3);
        dbManager_lop.addSV(sv4);
    }
    private void insetTinhDiem(){
        Diem diem1 = new Diem("n071", "cntt", 9);
        Diem diem2 = new Diem("n072", "mmt", 6);
        Diem diem3 = new Diem("n073", "cntt", 5);
        Diem diem4 = new Diem("n074", "mmt", 1);

        dbManager_lop.addDiem(diem1);
        dbManager_lop.addDiem(diem2);
        dbManager_lop.addDiem(diem3);
        dbManager_lop.addDiem(diem4);
    }
}
