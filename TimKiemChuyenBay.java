package edu.huflit.aappdatvemaybay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class TimKiemChuyenBay extends AppCompatActivity {

    EditText edt_diem_di, edt_diem_den;
    Button tim_kiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_chuyen_bay);
        edt_diem_di = (EditText) findViewById(R.id.edt_di);
        edt_diem_den = (EditText) findViewById(R.id.edt_den);
        tim_kiem = (Button) findViewById(R.id.btnTimKiem);



        tim_kiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String di = edt_diem_di.getText().toString();
                String den = edt_diem_den.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("timkiem", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("diemdi", di);
                editor.putString("diemden", den);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), DanhSachChuyenBayTimKiem.class);
                startActivity(intent);
            }
        });

    }
}