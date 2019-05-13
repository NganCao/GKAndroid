package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.model.Diem;
import com.example.myapplication.model.Lop;
import com.example.myapplication.model.MonHoc;
import com.example.myapplication.model.SinhVien;

import java.util.ArrayList;

public class DBManager_Lop extends SQLiteOpenHelper {

    private static String DB_NAME = "dbSinhVien.db";
    private static int DB_VERSION = 1;

    private static final String TB_LOPS = "tbLop";
    private static final String COL_LOP_MA = "lop_ma";
    private static final String COL_LOP_TEN = "lop_ten";

    private static String TABLE_MONHOC = "tbMonHoc";
    private static String COL_MA_MONHOC = "ma_monhoc";
    private static String COL_TEN_MONHOC = "ten_monhoc";
    private static String COL_HOCKY_MONHOC = "hocky_monhoc";

    private static String TABLE_SINHVIEN = "tbSinhVien";
    private static String COL_MASV = "masv";
    private static String COL_HO = "ho";
    private static String COL_TEN = "ten";
    private static String COL_PHAI = "phai";
    private static String COL_NOISINH = "noisinh";
    private static String COL_NGAYSINH = "ngaysinh";
    private static String COL_MALOP = "lop_ma";

    private static String TABLE_DIEM = "tbDiem";
    private static String COL_MASV_DIEM = "masv";
    private static String COL_MAMH_DIEM = "ma_monhoc";
    private static String COL_DIEM = "diem";

    private Context context;
    private String SQLQuery = "CREATE TABLE " + TB_LOPS + " (" +
            COL_LOP_MA + " TEXT PRIMARY KEY NOT NULL, " +
            COL_LOP_TEN + " TEXT)";

    private static String SQLQueryMonHoc = "CREATE TABLE " + TABLE_MONHOC
            + " (" + COL_MA_MONHOC + " TEXT PRIMARY KEY NOT NULL, "
            + COL_TEN_MONHOC + " TEXT, "
            + COL_HOCKY_MONHOC + " INTEGER)";

    private static String SQLQuerySV = "CREATE TABLE " + TABLE_SINHVIEN
            + " (" + COL_MASV + " TEXT PRIMARY KEY NOT NULL, "
            + COL_HO + " TEXT, "
            + COL_TEN + " TEXT, "
            + COL_PHAI + " TEXT, "
            + COL_NOISINH + " TEXT, "
            + COL_NGAYSINH + " TEXT, "
            + COL_MALOP + " TEXT NOT NULL, " +
            "CONSTRAINT FK_SV_Lop FOREIGN KEY ("+ COL_MALOP +") REFERENCES " +  TB_LOPS +"("+ COL_LOP_MA +"))";

    private static String SQLQueryDiem = "CREATE TABLE " + TABLE_DIEM
            + " (" + COL_MASV_DIEM + " TEXT NOT NULL, "
            + COL_MAMH_DIEM + " TEXT NOT NULL,"
            + COL_DIEM + " FLOAT, CONSTRAINT check_Diem CHECK (" + COL_DIEM + " between 0 and 10),"
            + "CONSTRAINT PK_Diem PRIMARY KEY (" + COL_MASV_DIEM + ", " + COL_MAMH_DIEM + "), "
            + "CONSTRAINT FK_SV_Diem FOREIGN KEY ("+ COL_MASV_DIEM +") REFERENCES " +  TABLE_SINHVIEN +"("+ COL_MASV +"),"
            + "CONSTRAINT FK_MH_Diem FOREIGN KEY ("+ COL_MAMH_DIEM +") REFERENCES " +  TABLE_MONHOC +"("+ COL_MA_MONHOC +"))";


    public DBManager_Lop(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQueryMonHoc);
        db.execSQL(SQLQuerySV);
        db.execSQL(SQLQueryDiem);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_LOPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONHOC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SINHVIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIEM);


        // create new tables
        onCreate(db);
    }

    public void addLop(Lop lop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LOP_MA, lop.getMaLop());
        values.put(COL_LOP_TEN, lop.getTenLop());

        db.insert(TB_LOPS, null, values);
        db.close();
    }

    public ArrayList<Lop> getAllLop(){
        ArrayList<Lop> list = new ArrayList<>();

        String query = "SELECT * FROM " + TB_LOPS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Lop lop = new Lop();
                lop.setMaLop(cursor.getString(0));
                lop.setTenLop(cursor.getString(1));
                lop.setSelected(false);
                list.add(lop);
            }while (cursor.moveToNext());
        }
        db.close();

        return list;
    }

    public int updateLop(Lop lop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_LOP_MA, lop.getMaLop());
        values.put(COL_LOP_TEN, lop.getTenLop());

        return db.update(TB_LOPS, values, COL_LOP_MA + "=?", new String[]{String.valueOf(lop.getMaLop())});

    }

    public int searchMaLop(String malop){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TB_LOPS + " where lop_ma =?", new String[]{malop});
        if (c.moveToFirst()){
            return 1;
        }
        return 0;

    }
    public int deleteLop(String maLop){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TB_LOPS, COL_LOP_MA + "=?", new String[]{maLop});
    }

    public void insertMonHoc(MonHoc monHoc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MA_MONHOC, monHoc.getMaMH());
        values.put(COL_TEN_MONHOC, monHoc.getTenMH());
        values.put(COL_HOCKY_MONHOC, monHoc.getHocKyMH());

        db.insert(TABLE_MONHOC, null, values);
        db.close();
        Log.d("insertMonhoc: ", "thanh cong");
    }

    public ArrayList<MonHoc> getListMonHoc(){
        ArrayList<MonHoc> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MONHOC;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                MonHoc monHoc = new MonHoc();
                monHoc.setMaMH(cursor.getString(0));
                monHoc.setTenMH(cursor.getString(1));
                monHoc.setHocKyMH(cursor.getInt(2));
                monHoc.setSelected(false);
                list.add(monHoc);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int updateMonHoc(MonHoc monHoc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COL_MA_MONHOC, monHoc.getMaMH());
        values.put(COL_TEN_MONHOC, monHoc.getTenMH());
        values.put(COL_HOCKY_MONHOC, monHoc.getHocKyMH());

        return db.update(TABLE_MONHOC, values, COL_MA_MONHOC + "=?", new String[]{String.valueOf(monHoc.getMaMH())});
    }

    public int deleteMonHoc(String maMonhoc){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_MONHOC, COL_MA_MONHOC + "=?", new String[]{maMonhoc});
    }

    public int searchMonHoc(String maMonhoc){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MONHOC + " WHERE " + COL_MA_MONHOC + " =?", new String[]{maMonhoc});
        if (cursor.moveToFirst()){
            return 1;
        }
        return 0;
    }

    public void addSV (SinhVien sv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MASV, sv.getMasv());
        values.put(COL_HO, sv.getHo());
        values.put(COL_TEN, sv.getTen());
        values.put(COL_PHAI, sv.getPhai());
        values.put(COL_NOISINH, sv.getNoisinh());
        values.put(COL_NGAYSINH, sv.getNgaysinh());
        values.put(COL_MALOP, sv.getMalop());

        db.insert(TABLE_SINHVIEN, null, values);
        db.close();
    }

    public ArrayList<SinhVien> getAllSV(){
        ArrayList<SinhVien> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_SINHVIEN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                SinhVien sv =  new SinhVien();
                sv.setMasv(cursor.getString(0));
                sv.setHo(cursor.getString(1));
                sv.setTen(cursor.getString(2));
                sv.setPhai(cursor.getString(3));
                sv.setNoisinh(cursor.getString(4));
                sv.setNgaysinh(cursor.getString(5));

                sv.setMalop(cursor.getString(6));
                list.add(sv);

            }while (cursor.moveToNext());
        }
        db.close();

        return list;
    }

    public int updateSV(SinhVien sv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_HO, sv.getHo());
        values.put(COL_TEN, sv.getTen());
        values.put(COL_PHAI, sv.getPhai());
        values.put(COL_NOISINH, sv.getNoisinh());
        values.put(COL_NGAYSINH, sv.getNgaysinh());

        return db.update(TABLE_SINHVIEN, values, COL_MASV + "=?", new String[]{String.valueOf(sv.getMasv())});
    }

    public int deleteSV(String maSV){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_SINHVIEN, COL_MASV + "=?", new String[]{maSV});
    }

    public void addDiem (Diem diem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MASV_DIEM, diem.getMasv());
        values.put(COL_MAMH_DIEM, diem.getMamh());
        values.put(COL_DIEM, diem.getDiem());

        db.insert(TABLE_DIEM, null, values);
        db.close();
    }

    public ArrayList<Diem> getAllDiemByMasv(String masv){
        ArrayList<Diem> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_DIEM + "WHERE "+ COL_MASV_DIEM +"="+ masv;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Diem diem =  new Diem();
                diem.setMasv(cursor.getString(0));
                diem.setMamh(cursor.getString(1));
                diem.setDiem(cursor.getFloat(2));
                list.add(diem);

            }while (cursor.moveToNext());
        }
        db.close();

        return list;
    }

    public int updateDiem(Diem diem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DIEM, diem.getDiem());

        return db.update(TABLE_DIEM, values, COL_MASV_DIEM + "=? AND " + COL_MAMH_DIEM + "=?", new String[]{String.valueOf(diem.getMasv()), String.valueOf(diem.getMamh())});
    }

    public int deleteDiem(String masv, String mamh){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DIEM, COL_MASV_DIEM + "=? AND " + COL_MAMH_DIEM + "=?", new String[]{masv, mamh});
    }
}
