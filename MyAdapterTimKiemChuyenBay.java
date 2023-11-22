package edu.huflit.aappdatvemaybay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapterTimKiemChuyenBay extends BaseAdapter {
    LayoutInflater inflater;
    TextView textView;
    ImageView imageView;

    Button button;
    Context context;

    public MyAdapterTimKiemChuyenBay(Context context)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return DanhSachChuyenBayTimKiem.flights.size();
    }

    @Override
    public Object getItem(int i) {
        return DanhSachChuyenBayTimKiem.flights.get(i);
    }

    @Override
    public long getItemId(int i) {
        return DanhSachChuyenBayTimKiem.flights.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_list_ds_chuyen_bay_tim_kiem, null);
        textView = (TextView) view.findViewById(R.id.tv_show_di);
        textView.setText(DanhSachChuyenBayTimKiem.flights.get(i).getDiem_di());
        textView = (TextView) view.findViewById(R.id.tv_show_den);
        textView.setText(DanhSachChuyenBayTimKiem.flights.get(i).getDiem_den());
        textView = (TextView) view.findViewById(R.id.tv_show_time_di);
        textView.setText(DanhSachChuyenBayTimKiem.flights.get(i).getTime_di());
        textView = (TextView) view.findViewById(R.id.tv_show_time_den);
        textView.setText(DanhSachChuyenBayTimKiem.flights.get(i).getTime_den());
        textView = (TextView) view.findViewById(R.id.tv_show_gia_ve);
        textView.setText(Double.toString(DanhSachChuyenBayTimKiem.flights.get(i).getGia_ve()));
        textView = (TextView) view.findViewById(R.id.tv_vt);
        imageView = (ImageView) view.findViewById(R.id.img_ds_tim_kiem);
        imageView.setImageResource(R.drawable.iconmaybay1);
        textView = (TextView) view.findViewById(R.id.tv_chon_item);
        return view;
    }
}
