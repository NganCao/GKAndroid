package com.example.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DiemAdapter;
import com.example.myapplication.data.DBManager_Lop;
import com.example.myapplication.model.Diem;

import java.util.ArrayList;

public class DiemActivity extends AppCompatActivity {

    private Toolbar toolbarDiem;
    private ListView lv_Diem;

    private ArrayList<Diem> diemArrayList;
    private DiemAdapter diemAdapter;

    private DBManager_Lop dbManager_lop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem);

        dbManager_lop = new DBManager_Lop(this);

        AnhXa();
        ActionBar();
    }

    private void ActionBar() {
        toolbarDiem.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbarDiem.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiemActivity.this, MainActivity.class));
            }
        });
    }

    private void AnhXa() {
        toolbarDiem = (Toolbar) findViewById(R.id.toolbarDiem);
        lv_Diem = (ListView) findViewById(R.id.listviewDiem);

        //deleteTinhDiem();
        //insetTinhDiem();
        diemArrayList = KhoiTao();
        diemAdapter = new DiemAdapter(this, diemArrayList);
        lv_Diem.setAdapter(diemAdapter);
    }

    private ArrayList<Diem> KhoiTao(){
        ArrayList<Diem> list = new ArrayList<>();
        Diem diem1 = new Diem("n071", "cntt", 9);
        Diem diem2 = new Diem("n072", "mmt", 6);
        Diem diem3 = new Diem("n073", "cntt", 5);
        Diem diem4 = new Diem("n074", "mmt", 1);

        list.add(diem1);
        list.add(diem2);
        list.add(diem3);
        list.add(diem4);

        return list;
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

    private void deleteTinhDiem(){
        int result = 0;
        int tam;
        tam = dbManager_lop.deleteDiem("n071", "cntt");
        result = result + tam;
        result += dbManager_lop.deleteDiem("n072", "mmt");
        result += dbManager_lop.deleteDiem("n073", "cntt");
        result += dbManager_lop.deleteDiem("n074", "mmt");
        Log.d("daxoa: ", String.valueOf(result));
    }
}
