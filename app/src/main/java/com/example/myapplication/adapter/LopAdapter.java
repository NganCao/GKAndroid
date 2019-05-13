package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;


import com.example.myapplication.R;
import com.example.myapplication.model.Lop;

import java.util.ArrayList;

public class LopAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutResourceId;
    ArrayList<Lop> data = null;

    public LopAdapter(Context context, ArrayList<Lop> data) {
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
        LopViewHolder holder;

        if (convertView == null){
            convertView = layoutResourceId.inflate(R.layout.item_lop_listview, null);
            holder = new LopViewHolder();

            holder.item_malop = (TextView) convertView.findViewById(R.id.textviewMaLop);
            holder.item_tenlop = (TextView) convertView.findViewById(R.id.textviewTenLop);
            holder.isCheckbox = (CheckBox) convertView.findViewById(R.id.checkboxLop);

            //holder.isCheckbox = (CheckBox) convertView.findViewById(R.id.checkboxLop);

            convertView.setTag(holder);
        }else {
            holder = (LopViewHolder) convertView.getTag();
        }

        Lop lop = data.get(position);

        holder.item_malop.setText(lop.getMaLop());
        holder.item_tenlop.setText(lop.getTenLop());
        holder.isCheckbox.setSelected(false);

        return convertView;
    }

    static class LopViewHolder{
        TextView item_malop;
        TextView item_tenlop;
        CheckBox isCheckbox;
    }
}