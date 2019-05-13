//package com.example.myapplication.activity;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RadioButton;
//import android.widget.Spinner;
//import android.widget.Toolbar;
//
//import com.example.myapplication.R;
//
//public class SV extends AppCompatActivity {
//
//    EditText txtMasv, txtHo, txtTen, txtNgaysinh, txtNoisinh;
//    RadioButton rbNam, rbNu;
//    Spinner spinnerMalop;
//    Button btnCancle, btnSave, btnReset;
//    ImageView img;
//    Toolbar toolbar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sv);
//
//        setControl();
//        setEvent();
//    }
//
//    private void setEvent() {
//        toolbarAdd();
//    }
//
//    private void setControl() {
//        txtMasv = (EditText) findViewById(R.id.editMasv);
//        txtHo = (EditText) findViewById(R.id.editHo);
//        txtTen = (EditText) findViewById(R.id.editTen);
//        txtNoisinh = (EditText) findViewById(R.id.editNoisinh);
//        txtNgaysinh = (EditText) findViewById(R.id.editMasv);
//
//        btnCancle = (Button) findViewById(R.id.btnCancle);
//        btnReset = (Button) findViewById(R.id.btnReset);
//        btnSave = (Button) findViewById(R.id.btnSave);
//
//        spinnerMalop = (Spinner) findViewById(R.id.spinnerMalop);
//
//        rbNam = (RadioButton) findViewById(R.id.rbNam);
//        rbNu = (RadioButton) findViewById(R.id.rbNu);
//
//        img = (ImageView) findViewById(R.id.img);
//        toolbar = (Toolbar) findViewById(R.id.toolbarSV);
//    }
//
//    private void toolbarAdd(){
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SV.this, DSSV.class));
//            }
//        });
//    }
//
//
//}
