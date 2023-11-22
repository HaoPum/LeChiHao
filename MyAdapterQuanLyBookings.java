package edu.huflit.aappdatvemaybay;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapterQuanLyBookings extends BaseAdapter {

    LayoutInflater inflater;
    TextView textView;
    ImageView imageView;

    Button button;
    Context context;

    public MyAdapterQuanLyBookings(Context context)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return QuanLyBookings.bookings.size();
    }

    @Override
    public Object getItem(int i) {
        return QuanLyBookings.bookings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return QuanLyBookings.bookings.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_list_quan_ly_bookings, null);
        textView = (TextView) view.findViewById(R.id.tv_id_booking);
        textView.setText(Long.toString(QuanLyBookings.bookings.get(i).getId()));
        textView = (TextView) view.findViewById(R.id.tv_name_kh_bookings);
        textView.setText(QuanLyBookings.bookings.get(i).getName_khach_hang());
        textView = (TextView) view.findViewById(R.id.tv_so_ve_bookings);
        textView.setText(Long.toString(QuanLyBookings.bookings.get(i).getSo_ve()));
        textView = (TextView) view.findViewById(R.id.tv_tong_tien_bookings);
        textView.setText(Double.toString(QuanLyBookings.bookings.get(i).getTong_tien()));
        imageView = (ImageView) view.findViewById(R.id.img_one_bookings);
        imageView.setImageResource(R.drawable.iconmaybay1);
        imageView = (ImageView) view.findViewById(R.id.img_two_bookings);
        imageView.setImageResource(R.drawable.iconkhachhang);
        imageView = (ImageView) view.findViewById(R.id.img_three_bookings);
        imageView.setImageResource(R.drawable.iconmoney);
        button = (Button) view.findViewById(R.id.btn_huy_bookings);


        Long id_flight = QuanLyBookings.bookings.get(i).getId_flight();
        Cursor cursor = QuanLyBookings.database.layDuLieuFlightChoLichSuVe(id_flight);
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
        textView = (TextView) view.findViewById(R.id.tv_diem_di_bookings);
        textView.setText(flights1.getDiem_di());
        textView = (TextView) view.findViewById(R.id.tv_diem_den_bookings);
        textView.setText(flights1.getDiem_den());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuanLyBookings.database.xoaBookings(QuanLyBookings.bookings.get(i));
                QuanLyBookings.bookings.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
