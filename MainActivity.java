package edu.huflit.aappdatvemaybay;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton thong_tin_tai_khoan, go_to_login, tim_kiem_chuyen_bay, chuyen_bay_da_dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thong_tin_tai_khoan = (ImageButton) findViewById(R.id.btn_tai_khoan);
        go_to_login = (ImageButton) findViewById(R.id.goToLogin);
        tim_kiem_chuyen_bay = (ImageButton) findViewById(R.id.btnSearch);
        chuyen_bay_da_dat = (ImageButton) findViewById(R.id.btn_chuyen_bay_da_dat);

        //
        chuyen_bay_da_dat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LichSuDatVe.class);
                startActivity(intent);
            }
        });
        //TÌM KIẾM CHUYẾN BAY
        tim_kiem_chuyen_bay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TimKiemChuyenBay.class);
                startActivity(intent);
            }
        });

        //BUTTON CHUYỂN TỚI TRANG CHỨA THÔNG TIN TÀI KHOẢN
        thong_tin_tai_khoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThongTinTaiKhoan.class);
                startActivity(intent);

            }
        });
        //ĐĂNG NHẬP
        go_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("thongtintaikhoan", MODE_PRIVATE);
                Boolean kt = sharedPreferences.getBoolean("isLoginAdmin", false);
                if (kt == false)
                {
                    AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Admin");
                    builder.setMessage("Vui lòng đăng nhập với quyền Admin");
                    builder.setNegativeButton("Đến đăng nhập", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), LoginAdmin.class);
                            startActivity(intent);
                        }
                    });
                    builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), DisplayAdmin.class);
                    startActivity(intent);
                }
            }
        });

    }

    //MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenumain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.itemDangNhap:
            {
                Intent intent = new Intent(getApplicationContext(), DangNhap.class);
                startActivity(intent);
                break;
            }
            case R.id.itemAdmin:
            {
                Intent intent = new Intent(getApplicationContext(), LoginAdmin.class);
                startActivity(intent);
                break;
            }
            case R.id.itemDangXuat:
            {
                SharedPreferences sharedPreferences = getSharedPreferences("thongtintaikhoan", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("iduser", null);
                editor.putString("username", null);
                editor.putString("fullname", null);
                editor.putString("email", null);
                editor.putString("phone", null);
                editor.putString("address", null);
                editor.putBoolean("isLoginUser", false);
                editor.putBoolean("isLoginAdmin", false);
                editor.apply();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}