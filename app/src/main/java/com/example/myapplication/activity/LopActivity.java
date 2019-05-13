package com.example.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LopAdapter;
import com.example.myapplication.data.DBManager_Lop;
import com.example.myapplication.model.Lop;

import java.util.ArrayList;

public class LopActivity extends AppCompatActivity {

    private Toolbar toolbarLop;
    private EditText edt_maLop, edt_tenLop;
    private Button btnNhapLop;
    private ListView lv_Lop;
    private ImageButton imgbutton_xoaLop;

    ArrayList<Lop> data_lop;
    LopAdapter lopAdapter;

    Lop selected_lop;
    int index = -1;
    DBManager_Lop dbManager_lop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop);
        dbManager_lop = new DBManager_Lop(this);

        setControl();
        ActionBar();
        data_lop = dbManager_lop.getAllLop();
        setAdapter();
        registerForContextMenu(lv_Lop);

        btnNhapLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lop lop = createLop();
                int search = 0;
                if (edt_maLop.getText().toString().equals("")){
                    edt_maLop.hasFocus(); // đưa con trỏ chuột về vị trí này???
                    return;
                }
                if (edt_tenLop.getText().toString().equals("")){
                    edt_tenLop.hasFocus();
                    return;
                }

                // lỗi ở hàm searchMaLop rồi
                search = dbManager_lop.searchMaLop(edt_maLop.getText().toString());
                if (search == 1){

                    int result = dbManager_lop.updateLop(lop);
                    if (result > 0){

                        btnNhapLop.setText("Nhập lớp");
                        edt_maLop.setEnabled(true);
                    }
                }
                else{
                    //Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
                        dbManager_lop.addLop(lop);


                }
                edt_maLop.setText("");
                edt_tenLop.setText("");
                uploadList();
            }
        });

        lv_Lop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lop lop = data_lop.get(position);
                edt_maLop.setText(lop.getMaLop());
                edt_tenLop.setText(lop.getTenLop());
                btnNhapLop.setText("Save");
                edt_maLop.setEnabled(false);

            }
        });

        lv_Lop.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
                index = position;
                selected_lop = data_lop.get(position);
                return false;
            }
        });

        imgbutton_xoaLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LopActivity.this);
                builder.setTitle("Hỏi xóa");
                builder.setMessage("Bạn chắc chắn muốn xóa các Lớp đã chọn?");
                //builder.setCancelable(false);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteNhieuLop();

                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();

            }
        });
    }

    private void deleteNhieuLop(){
        int dem = 0;
        for (int i = lv_Lop.getChildCount()-1; i >= 0; i--){
            View v = lv_Lop.getChildAt(i);
            CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkboxLop);
            if (checkBox.isChecked()){
                int result = dbManager_lop.deleteLop(data_lop.get(i).getMaLop());
                if (result > 0){
                    dem++;
                    //data_lop.get(i).setSelected(false);
                }
            }
        }
        uploadList();
        Toast.makeText(getApplicationContext(), "Đã xóa " + dem + " lớp.", Toast.LENGTH_LONG).show();
    }

    private void uploadList(){
        data_lop.clear();
        data_lop.addAll(dbManager_lop.getAllLop());
        setAdapter();
    }
    private void setControl() {
        toolbarLop = (Toolbar) findViewById(R.id.toolbarLop);
        edt_maLop = (EditText) findViewById(R.id.edittextMaLop);
        edt_tenLop = (EditText) findViewById(R.id.edittextTenLop);
        btnNhapLop = (Button) findViewById(R.id.buttonNhapLop);
        lv_Lop = (ListView) findViewById(R.id.listviewLop);
        imgbutton_xoaLop = (ImageButton) findViewById(R.id.imagebuttonXoaLop);

        edt_maLop.setFocusable(true);
    }

    private void ActionBar(){
        toolbarLop.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbarLop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LopActivity.this, MainActivity.class));
            }
        });
    }

    private Lop createLop(){
        String maMH = edt_maLop.getText().toString();
        String tenMH = edt_tenLop.getText() + "";

        Lop lop = new Lop(maMH, tenMH);
        return lop;
    }

    private void setAdapter(){
        if (lopAdapter == null){
            lopAdapter = new LopAdapter(this, data_lop);
            lv_Lop.setAdapter(lopAdapter);
        }else {
            lopAdapter.notifyDataSetChanged();
            lv_Lop.setSelection(lopAdapter.getCount()-1);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_lop,menu);
        menu.getItem(0).setTitle("Thêm sinh viên cho lớp " + selected_lop.getTenLop());
        menu.getItem(1).setTitle("Danh sách sinh viên lớp " + selected_lop.getTenLop());
        menu.getItem(2).setTitle("Xóa lớp " + selected_lop.getTenLop());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_themSV:
                // gọi phương thức thêm sinh viên của Ngân
                Intent intent = new Intent(LopActivity.this, ThemSVActivity.class);
                intent.putExtra("malop",selected_lop.getMaLop());
                startActivity(intent);
                break;

            case R.id.menu_dssv:
                // gọi phương thức danh sách sinh viên của Ngân
                Intent intent1 = new Intent(LopActivity.this, DSSVActivity.class);
                intent1.putExtra("malop", selected_lop.getMaLop());
                startActivity(intent1);
                break;

            case R.id.menu_xoaLop:
                // phương thức xóa lớp - Hiến
                deleteLop();

                break;
        }
        return super.onContextItemSelected(item);
    }
    private void deleteLop(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LopActivity.this);
        builder.setTitle("Hỏi xóa");
        builder.setMessage("Bạn chắc chắn muốn xóa Lớp?");
        //builder.setCancelable(false);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int result = dbManager_lop.deleteLop(selected_lop.getMaLop());
                if (result > 0){
                    Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    uploadList();
                }else {
                    Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
