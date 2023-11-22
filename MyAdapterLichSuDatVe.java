package edu.huflit.aappdatvemaybay;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapterLichSuDatVe extends BaseAdapter {

    LayoutInflater inflater;
    TextView textView;
    ImageView imageView;

    Button button;
    Context context;

    public MyAdapterLichSuDatVe(Context context)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return LichSuDatVe.bookings.size();
    }

    @Override
    public Object getItem(int i) {
        return LichSuDatVe.bookings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return LichSuDatVe.bookings.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_list_lich_su_dat_ve, null);
        textView = (TextView) view.findViewById(R.id.tv_diem_di_ls);
        textView = (TextView) view.findViewById(R.id.tv_diem_den_ls);
        textView = (TextView) view.findViewById(R.id.tv_ten_kh_ls);
        textView.setText(LichSuDatVe.bookings.get(i).getName_khach_hang());
        textView = (TextView) view.findViewById(R.id.tv_so_ve_ls);
        textView.setText(Long.toString(LichSuDatVe.bookings.get(i).getSo_ve()));
        textView = (TextView) view.findViewById(R.id.tv_tong_tien_ls);
        textView.setText(Double.toString(LichSuDatVe.bookings.get(i).getTong_tien()));
        button = (Button) view.findViewById(R.id.btn_huy_ve_ls);
        imageView = (ImageView) view.findViewById(R.id.img_one_ls);
        imageView.setImageResource(R.drawable.iconmaybay1);
        imageView = (ImageView) view.findViewById(R.id.img_two_ls);
        imageView.setImageResource(R.drawable.iconkhachhang);
        imageView = (ImageView) view.findViewById(R.id.img_three_ls);
        imageView.setImageResource(R.drawable.iconmoney);

        Long id_flight = LichSuDatVe.bookings.get(i).getId_flight();
        Cursor cursor = LichSuDatVe.database.layDuLieuFlightChoLichSuVe(id_flight);
        Flights flights1 = new Flights();
        if (cursor != null)
        {
            int diem_di_index = cursor.getColumnIndex(DBHelper.DIEM_DI);
            int diem_den_index = cursor.getColumnIndex(DBHelper.DIEM_DEN);
            while (cursor.moveToNext())
            {
                if (diem_di_index != -1)
                {
                    flights1.setDiem_di(cursor.getString(diem_di_index));
                }
                if (diem_den_index != -1)
                {
                    flights1.setDiem_den(cursor.getString(diem_den_index));
                }
            }
        }
        textView = (TextView) view.findViewById(R.id.tv_diem_di_ls);
        textView.setText(flights1.getDiem_di());
        textView = (TextView) view.findViewById(R.id.tv_diem_den_ls);
        textView.setText(flights1.getDiem_den());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LichSuDatVe.database.xoaBookings(LichSuDatVe.bookings.get(i));
                LichSuDatVe.bookings.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
