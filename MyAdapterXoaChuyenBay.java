package edu.huflit.aappdatvemaybay;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapterXoaChuyenBay extends BaseAdapter {

    LayoutInflater inflater;
    TextView textView;
    ImageView imageView;

    Button button;
    CheckBox checkBox;
    Context context;

    public MyAdapterXoaChuyenBay(Context context)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return XoaChuyenBay.flights.size();
    }

    @Override
    public Object getItem(int i) {
        return XoaChuyenBay.flights.get(i);
    }

    @Override
    public long getItemId(int i) {
        return XoaChuyenBay.flights.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_list_xoa_chuyen_bay, null);
        textView = (TextView) view.findViewById(R.id.tv_item_di_xoa);
        textView.setText(XoaChuyenBay.flights.get(i).getDiem_di());
        textView = (TextView) view.findViewById(R.id.tv_item_den_xoa);
        textView.setText(XoaChuyenBay.flights.get(i).getDiem_den());
        textView = (TextView) view.findViewById(R.id.tv_item_tgdi_xoa);
        textView.setText(XoaChuyenBay.flights.get(i).getTime_di());
        textView = (TextView) view.findViewById(R.id.tv_item_tgden_xoa);
        textView.setText(XoaChuyenBay.flights.get(i).getTime_den());
        imageView = (ImageView) view.findViewById(R.id.img_item_xoa);
        imageView.setImageResource(R.drawable.iconmaybay1);
        button = (Button) view.findViewById(R.id.btn_item_xoa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    XoaChuyenBay.database.xoaFligths(XoaChuyenBay.flights.get(i));
                    XoaChuyenBay.flights.remove(i);
                    notifyDataSetChanged();
            }
        });
        return view;
    }
}
