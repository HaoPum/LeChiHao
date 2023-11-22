package edu.huflit.aappdatvemaybay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DisplayAdmin extends AppCompatActivity {

    Button next_them_chuyen_bay, next_xoa_chuyen_bay, next_sua_chuyen_bay, next_quan_ly_bookings, next_quan_ly_users, next_voucher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_admin);
        next_them_chuyen_bay = (Button) findViewById(R.id.btn_next_them);
        next_xoa_chuyen_bay = (Button) findViewById(R.id.btn_next_xoa);
        next_sua_chuyen_bay = (Button) findViewById(R.id.btn_next_sua);
        next_quan_ly_bookings = (Button) findViewById(R.id.btn_bookings);
        next_quan_ly_users = (Button) findViewById(R.id.btn_users);
        next_voucher = (Button) findViewById(R.id.next_them_vouhcer);

        next_voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThemVoucher.class);
                startActivity(intent);
            }
        });
        next_quan_ly_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuanLyUsers.class);
                startActivity(intent);
            }
        });
        next_quan_ly_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuanLyBookings.class);
                startActivity(intent);
            }
        });

        next_sua_chuyen_bay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SuaChuyenBay.class);
                startActivity(intent);
            }
        });
        next_them_chuyen_bay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThemChuyenBay.class);
                startActivity(intent);
            }
        });
        next_xoa_chuyen_bay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), XoaChuyenBay.class);
                startActivity(intent);
            }
        });
    }
}