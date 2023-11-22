package edu.huflit.aappdatvemaybay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManHinhDangNhap extends AppCompatActivity {
    Button mloginUser, mloginAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);
        mloginUser = (Button) findViewById(R.id.btnLoginUser);
        mloginAdmin = (Button) findViewById(R.id.btnLoginAdmin);

        mloginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangNhap.class);
                startActivity(intent);
            }
        });
        mloginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), LoginAdmin.class);
                startActivity(intent2);
            }
        });
    }
}