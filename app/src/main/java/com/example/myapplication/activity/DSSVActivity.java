package com.example.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LopAdapter;
import com.example.myapplication.adapter.SinhVienAdapter;
import com.example.myapplication.data.DBManager_Lop;
import com.example.myapplication.model.Lop;
import com.example.myapplication.model.SinhVien;

import java.util.ArrayList;

public class DSSVActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ListView lv;

    ArrayList<SinhVien> data;
    SinhVienAdapter adapter;

    int index = -1;
    SinhVien sv_position;
    public static int RESULT_UPDATE = 123;
    private DBManager_Lop dbManager_lop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dssv);
        dbManager_lop = new DBManager_Lop(this);

        setControl();
        ActionBar();
        setEvent();
        loadlvSV();

    }

    private void ActionBar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DSSVActivity.this, MainActivity.class));
            }
        });
    }

    private void setEvent() {

        registerForContextMenu(lv);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                sv_position = data.get(position);
                return false;
            }
        });


    }

    private void setControl() {
        toolbar = (Toolbar) findViewById(R.id.toolbarDSSV);
        lv = (ListView) findViewById(R.id.lvSV);

        data = dbManager_lop.getAllSV();
        adapter = new SinhVienAdapter(this, data);
        lv.setAdapter(adapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_dssv,menu);
        menu.getItem(0).setTitle("Thêm sinh viên");
        menu.getItem(1).setTitle("Sửa sinh viên " + sv_position.getMasv());
        menu.getItem(2).setTitle("Xem danh sách điểm");
        menu.getItem(3).setTitle("Chuyển " + sv_position.getMasv() + " sang lớp khác");
        menu.getItem(4).setTitle("Xóa sinh viên");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.themSV:
                Intent i = new Intent(DSSVActivity.this, ThemSVActivity.class);
                startActivity(i);
                break;

            case R.id.suaSV: {
                Intent update = new Intent(getApplicationContext(), EditSVActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("SINHVIEN", sv_position);
                update.putExtra("DATA", b);
//                startActivity(update);
                startActivityForResult(update, RESULT_UPDATE);
            }
                break;

            case R.id.xemDiem:
                break;

            case R.id.chuyenLop:
                break;

            case R.id.xoaSV:
                xoaTinhSinhVien();
                loadlvSV();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private ArrayList<SinhVien> KhoiTao(){
        ArrayList<SinhVien> list = new ArrayList<>();
        SinhVien sv1 = new SinhVien("n072", "luong", "hien", "nu", "dong nai", "21/5/2000", "cp01");
        SinhVien sv2 = new SinhVien("n073", "luong", "hien", "nam", "dong nai", "21/5/2000", "cp01");
        SinhVien sv3 = new SinhVien("n074", "luong", "hien", "nu", "dong nai", "21/5/2000", "cp01");
        SinhVien sv4 = new SinhVien("n075", "luong", "hien", "nam", "dong nai", "21/5/2000", "cp01");
        list.add(sv1);
        list.add(sv2);
        list.add(sv3);
        list.add(sv4);
        return list;
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
    private void xoaTinhSinhVien(){
        SinhVien sv = new SinhVien();
        String masv = data.get(index).getMasv();
        int result = dbManager_lop.deleteSV(masv);
        if (result !=-1){
            Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadlvSV(){
        data = dbManager_lop.getAllSV();
        adapter = new SinhVienAdapter(this, data);
        lv.setAdapter(adapter);
    }

//    public void getDataFromMain()
//    {
//        Intent i=getIntent();
//        Bundle b=i.getBundleExtra("DATA");
//        pb= (PhongBan) b.getSerializable("PHONGBAN");
//        arrNhanvien=pb.getListNhanVien();
//        adapter=new NhanVienAdapter(this,
//                R.layout.layout_item_custom,
//                arrNhanvien);
//        lvNhanvien.setAdapter(adapter);
//        txtmsg.setText("DS nhân viên ["+pb.getTen()+"]");
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_UPDATE && resultCode == RESULT_OK){
            loadlvSV();
        }
    }
}