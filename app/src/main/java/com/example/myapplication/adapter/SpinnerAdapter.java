package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Lop;
import com.example.myapplication.model.SinhVien;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Lop> {
    Context context;
    int layoutid;
    List<Lop> data;

    public SpinnerAdapter(Context context, int layoutid, List<Lop> data) {
        super(context, layoutid, data);
        this.context = context;
        this.layoutid = layoutid;
        this.data = data;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_layout_spinner, parent, false);

        TextView maLop = (TextView) row.findViewById(R.id.textviewSpinner);

        Lop lop = data.get(position);
        maLop.setText(lop.getMaLop());

        return row;
    }
}
