package edu.huflit.aappdatvemaybay;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThongTinTaiKhoan extends AppCompatActivity {

    TextView username, fullname, email, phone, address;
    String tenTk, hoTen, mail, sdt, diaChi;
    Button btnThoat, btn_sua_tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tai_khoan);
        anhXa();
        SharedPreferences sharedPreferences = getSharedPreferences("thongtintaikhoan", MODE_PRIVATE);
        tenTk = sharedPreferences.getString("username", "USERNAME");
        hoTen = sharedPreferences.getString("fullname", "FULLNAME");
        mail = sharedPreferences.getString("email", "EMAIL");
        sdt = sharedPreferences.getString("phone", "PHONE");
        diaChi = sharedPreferences.getString("address", "ADDRESS");

        username.setText(tenTk);
        fullname.setText(hoTen);
        email.setText(mail);
        phone.setText(sdt);
        address.setText(diaChi);
        btn_sua_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences1 = getSharedPreferences("thongtintaikhoan", MODE_PRIVATE);
                Boolean isLoginUser = sharedPreferences1.getBoolean("isLoginUser", false);
                Boolean isLoginAdmin = sharedPreferences1.getBoolean("isLoginAdmin", false);
                if (isLoginUser == false && isLoginAdmin == false)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinTaiKhoan.this);
                    builder.setMessage("Bạn chưa đăng nhập");
                    builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), SuaThongTinKhachHang.class);
                    startActivity(intent);
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void anhXa()
    {
        username = (TextView) findViewById(R.id.tv_username);
        fullname = (TextView) findViewById(R.id.tvfull_name);
        email = (TextView) findViewById(R.id.tv_email);
        phone = (TextView) findViewById(R.id.tv_phone);
        address = (TextView) findViewById(R.id.tv_address);
        btnThoat = (Button) findViewById(R.id.btnThoat);
        btn_sua_tt = (Button) findViewById(R.id.btn_sua_tt_kh);
    }
}