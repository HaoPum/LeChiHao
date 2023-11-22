package edu.huflit.aappdatvemaybay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapterThemChuyenBay extends BaseAdapter {
    LayoutInflater inflater;
    TextView textView;
    ImageView imageView;
    Context context;

    public MyAdapterThemChuyenBay(Context context)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return ThemChuyenBay.flights.size();
    }

    @Override
    public Object getItem(int i) {
        return ThemChuyenBay.flights.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ThemChuyenBay.flights.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_list_them_chuyen_bay, null);
        textView = (TextView) view.findViewById(R.id.tv_diem_di);
        textView.setText(ThemChuyenBay.flights.get(i).getDiem_di());
        textView = (TextView) view.findViewById(R.id.tv_diem_den);
        textView.setText(ThemChuyenBay.flights.get(i).getDiem_den());
        textView = (TextView) view.findViewById(R.id.tv_time_di);
        textView.setText(ThemChuyenBay.flights.get(i).getTime_di());
        textView = (TextView) view.findViewById(R.id.tv_time_den);
        textView.setText(ThemChuyenBay.flights.get(i).getTime_den());
        textView = (TextView) view.findViewById(R.id.tv_gia_ve);
        textView.setText(Double.toString(ThemChuyenBay.flights.get(i).getGia_ve()));
        imageView = (ImageView) view.findViewById(R.id.img_item_list);
        imageView.setImageResource(R.drawable.iconmaybay1);
        imageView = (ImageView) view.findViewById(R.id.img_item_list_money);
        imageView.setImageResource(R.drawable.iconmoney);
        return view;
    }
}
