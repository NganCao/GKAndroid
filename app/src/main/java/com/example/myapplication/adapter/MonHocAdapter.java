package com.example.myapplication.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Lop;
import com.example.myapplication.model.MonHoc;

import java.util.ArrayList;
import java.util.List;

public class MonHocAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutResourceId;
    ArrayList<MonHoc> data = null;

    public MonHocAdapter(Context context, ArrayList<MonHoc> data) {
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
        MonHocViewHolder holder;

        if (convertView == null){
            convertView = layoutResourceId.inflate(R.layout.item_monhoc_listview, null);
            holder = new MonHocViewHolder();

            holder.item_maMH = (TextView) convertView.findViewById(R.id.textviewMaMH);
            holder.item_tenMH = (TextView) convertView.findViewById(R.id.textviewTenMH);
            holder.item_hocKyMH = (TextView) convertView.findViewById(R.id.textviewHocKyMH);
            holder.item_checkMH = (CheckBox) convertView.findViewById(R.id.checkboxMonHoc);

            convertView.setTag(holder);
        }else {
            holder = (MonHocViewHolder) convertView.getTag();
        }

        MonHoc monHoc = data.get(position);

        holder.item_maMH.setText(monHoc.getMaMH());
        holder.item_tenMH.setText(monHoc.getTenMH());
        holder.item_hocKyMH.setText(String.valueOf(monHoc.getHocKyMH()));
        holder.item_checkMH.setSelected(false);

        return convertView;
    }

    static class MonHocViewHolder{
        TextView item_maMH;
        TextView item_tenMH;
        TextView item_hocKyMH;
        CheckBox item_checkMH;
    }
}
