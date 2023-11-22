package edu.huflit.aappdatvemaybay;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class QuanLyBookings extends AppCompatActivity {

    public static ListView listView;
    public static ArrayList<Bookings> bookings;
    public static MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_bookings);
        listView = (ListView) findViewById(R.id.lv_quan_ly_bookings);
        database = new MyDatabase(this);
        bookings = new ArrayList<Bookings>();
        capNhatDuLieuFlights();
        listView.invalidateViews();
    }

    public void capNhatDuLieuFlights()
    {
        if (bookings == null)
        {
            bookings = new ArrayList<>();
        }
        else
        {
            bookings.removeAll(bookings);
        }
        Cursor cursor = database.layDuLieuBookings();
        if (cursor != null)
        {
            //booking
            int idBookingIndex = cursor.getColumnIndex(DBHelper.ID_BOOKING);
            int idUserIndex = cursor.getColumnIndex(DBHelper.USER_ID);
            int idFlightIndex = cursor.getColumnIndex(DBHelper.FLIGHT_ID);
            int nameKHIndex = cursor.getColumnIndex(DBHelper.NAME_KHACH_HANG);
            int cmndKHIndex = cursor.getColumnIndex(DBHelper.CMND_KHACH_HANG);
            int soVeIndex = cursor.getColumnIndex(DBHelper.SO_VE);
            int tongTienIndex = cursor.getColumnIndex(DBHelper.TONG_TIEN);


            while (cursor.moveToNext())
            {
                Bookings bookings1 = new Bookings();
                if (idBookingIndex != -1)
                {
                    bookings1.setId(Integer.parseInt(cursor.getString(idBookingIndex)));
                }
                if (idUserIndex != -1)
                {
                    bookings1.setId_user(Integer.parseInt(cursor.getString(idUserIndex)));
                }
                if (idFlightIndex != -1)
                {
                    bookings1.setId_flight(Integer.parseInt(cursor.getString(idFlightIndex)));
                }
                if (nameKHIndex != -1)
                {
                    bookings1.setName_khach_hang(cursor.getString(nameKHIndex));
                }
                if (cmndKHIndex != -1)
                {
                    bookings1.setCmnd_khach_hang(cursor.getString(cmndKHIndex));
                }
                if (soVeIndex != -1)
                {
                    bookings1.setSo_ve(Integer.parseInt(cursor.getString(soVeIndex)));
                }
                if (tongTienIndex != -1)
                {
                    bookings1.setTong_tien(Double.parseDouble(cursor.getString(tongTienIndex)));
                }
                bookings.add(bookings1);
            }
        }
        if (bookings != null)
        {
            listView.setAdapter(new MyAdapterQuanLyBookings(getApplicationContext()));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}