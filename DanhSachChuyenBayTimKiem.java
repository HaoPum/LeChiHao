package edu.huflit.aappdatvemaybay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DanhSachChuyenBayTimKiem extends AppCompatActivity {

    public static ListView listView;
    public static ArrayList<Flights> flights;
    public static MyDatabase database;

    private String di, den;
    String diem_di, diem_den, time_di, time_den, gia_ve, id_flights;
    TextView thong_bao_tim_kiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thong_bao_tim_kiem = (TextView) findViewById(R.id.tv_thong_bao_tim_kiem);
        setContentView(R.layout.activity_danh_sach_chuyen_bay_tim_kiem);listView = (ListView) findViewById(R.id.lv_danh_sach_tim_kiem);
        database = new MyDatabase(this);
        flights = new ArrayList<Flights>();
        //capNhatDuLieuFlights();
        timKiem();
        listView.invalidateViews();
    }

    ///LẤY DỮ LIỆU ĐIỂM ĐI ĐIỂM ĐẾN ĐỂ TÌM KIẾM CHUYẾN BAY
    public void timKiem()
    {
        if (flights == null)
        {
            flights = new ArrayList<>();
        }
        else
        {
            flights.removeAll(flights);
        }
        SharedPreferences sharedPreferences_tim_kiem = getSharedPreferences("timkiem", MODE_PRIVATE);
        di = sharedPreferences_tim_kiem.getString("diemdi", null);
        den = sharedPreferences_tim_kiem.getString("diemden", null);
        Cursor cursor = database.searchFlights(di, den);

        if (cursor != null)
        {
            int idIndex = cursor.getColumnIndex(DBHelper.ID_FLIGHT);
            int diemdiIndex = cursor.getColumnIndex(DBHelper.DIEM_DI);
            int diemdenIndex = cursor.getColumnIndex(DBHelper.DIEM_DEN);
            int timediIndex = cursor.getColumnIndex(DBHelper.TIME_DI);
            int timedenIndex = cursor.getColumnIndex(DBHelper.TIME_DEN);
            int giaveIndex = cursor.getColumnIndex(DBHelper.GIA_VE);
            while (cursor.moveToNext())
            {

                Flights flights1 = new Flights();
                if (idIndex != -1)
                {
                    flights1.setId(Integer.parseInt(cursor.getString(idIndex)));
                }
                if (diemdiIndex != -1)
                {
                    flights1.setDiem_di(cursor.getString(diemdiIndex));
                }
                if (diemdenIndex != -1)
                {
                    flights1.setDiem_den(cursor.getString(diemdenIndex));
                }
                if (timediIndex != -1)
                {
                    flights1.setTime_di(cursor.getString(timediIndex));
                }
                if (timedenIndex != -1)
                {
                    flights1.setTime_den(cursor.getString(timedenIndex));
                }
                if (giaveIndex != -1)
                {
                    flights1.setGia_ve(Double.parseDouble(cursor.getString(giaveIndex)));
                }
                flights.add(flights1);
            }
        }
        if (flights != null)
        {
            listView.setAdapter(new MyAdapterDanhSachChuyenBayTimKiem(getApplicationContext()));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                diem_di = flights.get(i).getDiem_di();
                diem_den = flights.get(i).getDiem_den();
                time_di = flights.get(i).getTime_di();
                time_den = flights.get(i).getTime_den();
                gia_ve = Double.toString(flights.get(i).getGia_ve());
                id_flights = Long.toString(flights.get(i).getId());
                SharedPreferences sharedPreferences_tk = getSharedPreferences("thongtinchuyenbay", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences_tk.edit();
                editor.putString("diemdi", diem_di);
                editor.putString("diemden", diem_den);
                editor.putString("timedi", time_di);
                editor.putString("timeden", time_den);
                editor.putString("giave", gia_ve);
                editor.putString("idchuyenbay", id_flights);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), NhapThongTinDatVe.class);
                startActivity(intent);
            }
        });
;    }

    //Lấy dữ liệu Fligths

    public void capNhatDuLieuFlights()
    {
        if (flights == null)
        {
            flights = new ArrayList<>();
        }
        else
        {
            flights.removeAll(flights);
        }
        Cursor cursor = database.layDuLieuFlights();
        if (cursor != null)
        {
            int idIndex = cursor.getColumnIndex(DBHelper.ID_FLIGHT);
            int diemdiIndex = cursor.getColumnIndex(DBHelper.DIEM_DI);
            int diemdenIndex = cursor.getColumnIndex(DBHelper.DIEM_DEN);
            int timediIndex = cursor.getColumnIndex(DBHelper.TIME_DI);
            int timedenIndex = cursor.getColumnIndex(DBHelper.TIME_DEN);
            int giaveIndex = cursor.getColumnIndex(DBHelper.GIA_VE);
            while (cursor.moveToNext())
            {
                Flights flights1 = new Flights();
                if (idIndex != -1)
                {
                    flights1.setId(Integer.parseInt(cursor.getString(idIndex)));
                }
                if (diemdiIndex != -1)
                {
                    flights1.setDiem_di(cursor.getString(diemdiIndex));
                }
                if (diemdenIndex != -1)
                {
                    flights1.setDiem_den(cursor.getString(diemdenIndex));
                }
                if (timediIndex != -1)
                {
                    flights1.setTime_di(cursor.getString(timediIndex));
                }
                if (timedenIndex != -1)
                {
                    flights1.setTime_den(cursor.getString(timedenIndex));
                }
                if (giaveIndex != -1)
                {
                    flights1.setGia_ve(Double.parseDouble(cursor.getString(giaveIndex)));
                }
                flights.add(flights1);
            }
        }
        if (flights != null)
        {
            listView.setAdapter(new MyAdapterDanhSachChuyenBayTimKiem(getApplicationContext()));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                diem_di = flights.get(i).getDiem_di();
                diem_den = flights.get(i).getDiem_den();
                time_di = flights.get(i).getTime_di();
                time_den = flights.get(i).getTime_den();
                gia_ve = Double.toString(flights.get(i).getGia_ve());
                id_flights = Long.toString(flights.get(i).getId());
                SharedPreferences sharedPreferences_tk = getSharedPreferences("thongtinchuyenbay", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences_tk.edit();
                editor.putString("diemdi", diem_di);
                editor.putString("diemden", diem_den);
                editor.putString("timedi", time_di);
                editor.putString("timeden", time_den);
                editor.putString("giave", gia_ve);
                editor.putString("idchuyenbay", id_flights);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), NhapThongTinDatVe.class);
                startActivity(intent);
            }
        });
    }
}