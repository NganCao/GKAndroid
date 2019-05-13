package com.example.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LopAdapter;
import com.example.myapplication.adapter.SpinnerAdapter;
import com.example.myapplication.data.DBManager_Lop;
import com.example.myapplication.model.Lop;
import com.example.myapplication.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class ThemSVActivity extends AppCompatActivity {

    EditText txtMasv, txtHo, txtTen, txtNgaysinh, txtNoisinh;
    RadioButton rbNam, rbNu;
    Spinner spinnerMalop;
    Button btnCancle, btnSave, btnReset;
    RadioGroup radioGroup;
    ImageView img;
    ArrayList<Lop> listLop;
    SpinnerAdapter adapter;
    int index;
    SinhVien sv;

    DBManager_Lop dbManager_lop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sv);
        dbManager_lop = new DBManager_Lop(this);

        setControl();
        setEvent();
    }

    private void setEvent() {
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv = new SinhVien();
                String malop = listLop.get(index).getMaLop();
                if (rbNam.isChecked()){
                    sv.setPhai("Nam");
                }
                else {
                    sv.setPhai("Nữ");
                }
                try {
                    sv.setHo(txtHo.getText().toString());
                    sv.setTen(txtTen.getText().toString());
                    sv.setMasv(txtMasv.getText().toString());
                    sv.setNgaysinh(txtNgaysinh.getText().toString());
                    sv.setNoisinh(txtNoisinh.getText().toString());
                    sv.setMalop(malop);

                    dbManager_lop.addSV(sv);
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        listLop = new ArrayList<>();
        listLop = dbManager_lop.getAllLop();

        adapter = new SpinnerAdapter(this, R.layout.custom_layout_spinner,listLop);
        spinnerMalop.setAdapter(adapter);
        spinnerMalop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setControl() {
        txtMasv = (EditText) findViewById(R.id.editMasv);
        txtHo = (EditText) findViewById(R.id.editHo);
        txtTen = (EditText) findViewById(R.id.editTen);
        txtNoisinh = (EditText) findViewById(R.id.editNoisinh);
        txtNgaysinh = (EditText) findViewById(R.id.dateNgaysinh);

        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnSave = (Button) findViewById(R.id.btnSave);

        spinnerMalop = (Spinner) findViewById(R.id.spinnerMalop);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbNam = (RadioButton) findViewById(R.id.rbNam);
        rbNu = (RadioButton) findViewById(R.id.rbNu);

        img = (ImageView) findViewById(R.id.img);
//        toolbarSV = (Toolbar) findViewById(R.id.toolbarEditSV);
    }

    private void checkInsert(){
        if (txtMasv.getText().toString().equals("")){
            Toast.makeText(this, "Bạn chưa nhập mã sv!", Toast.LENGTH_SHORT);
        }
        if (txtHo.getText().toString().equals("")){
            Toast.makeText(this, "Bạn chưa nhập họ!", Toast.LENGTH_SHORT);
        }
        if (txtTen.getText().toString().equals("")) {
            Toast.makeText(this, "Bạn chưa nhập tên!", Toast.LENGTH_SHORT);
        }
        if (txtNgaysinh.getText().toString().equals("")){
            Toast.makeText(this, "Bạn chưa nhập ngày sinh!", Toast.LENGTH_SHORT);
        }
        if (txtNoisinh.getText().toString().equals("")){
            Toast.makeText(this, "Bạn chưa nhập nơi sinh!", Toast.LENGTH_SHORT);
        }
    }
}
