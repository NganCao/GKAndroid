package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;

import com.example.myapplication.model.Diem;
import com.example.myapplication.model.SinhVien;


import java.util.ArrayList;

public class DiemAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutResourceId;
    ArrayList<Diem> data = null;

    public DiemAdapter(Context context, ArrayList<Diem> data) {
        this.context = context;
        this.data = data;
        layoutResourceId = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiemHolder holder;

        if (convertView == null){
            convertView = layoutResourceId.inflate(R.layout.item_row_core, null);
            holder = new DiemHolder();

            holder.txtMamh = (TextView) convertView.findViewById(R.id.txtMamh);
            holder.txtMasv = (TextView) convertView.findViewById(R.id.txtMasv);
            holder.txtDiem = (TextView) convertView.findViewById(R.id.txtDiem);

            convertView.setTag(holder);
        }else {
            holder = (DiemHolder) convertView.getTag();
        }

        Diem diem = data.get(position);

        holder.txtMamh.setText(diem.getMamh());
        holder.txtMasv.setText(diem.getMasv());
        holder.txtDiem.setText(String.valueOf(diem.getDiem()));

        return convertView;
    }

    static class DiemHolder{
        TextView txtMasv, txtMamh, txtDiem;
    }

}
