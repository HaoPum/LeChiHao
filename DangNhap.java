package edu.huflit.aappdatvemaybay;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DangNhap extends AppCompatActivity {

    EditText edt_username, edt_password;
    String username, password;
    String tenTK, hoTen, mail, sdt, diaChi, id_user;
    Button btn_dangnhap, btn_next_dangky;
    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        database = new MyDatabase(this);
        anhXa();
        //BUTTON ẤN ĐĂNG NHẬP
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangNhap();
            }
        });
        ;
        //BUTTON CHUYỂN TỚI TRANG ĐĂNG KÝ
        btn_next_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangKy.class);
                startActivity(intent);
            }
        });
    }

    //ÁNH XẠ
    private void anhXa() {
        edt_username = (EditText) findViewById(R.id.edtUserName);
        edt_password = (EditText) findViewById(R.id.edtPassWord);
        btn_dangnhap = (Button) findViewById(R.id.btnDangNhap);
        btn_next_dangky = (Button) findViewById(R.id.btnNextDangKy);
    }

    //ĐĂNG NHẬP
    //kiểm tra xem đã nhập user và pass chưa
    public boolean checkThongTin() {
        if (edt_username.length() == 0) {
            String user = "<font color='#FF0000'>Tên đăng nhập không được để trống!</font>";
            edt_username.setHint(Html.fromHtml(user));
            return false;
        } else if (edt_password.length() == 0) {
            String pass = "<font color='#FF0000'>Mật khẩu không được để trống!</font>";
            edt_password.setHint(Html.fromHtml(pass));
            return false;
        }
        return true;
    }

    public void dangNhap() {
        //Kiểm tra đã nhập user và pass hay chưa
        if (checkThongTin() == false) {
            return;
        }
        username = edt_username.getText().toString();
        password = edt_password.getText().toString();
        if (database.checkLogin(username, password) == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(DangNhap.this);
            builder.setTitle("Đăng nhập thất bại");
            builder.setMessage("Tên tài khoản hoặc mật khẩu không chính xác!");
            builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
            return;
        }

        Cursor cursor = database.layThongTinLogin(username);
        if (cursor != null) {
            int idIndex = cursor.getColumnIndex(DBHelper.ID_USER);
            int usernameIntdex = cursor.getColumnIndex(DBHelper.USER_NAME);
            int fullnameIndex = cursor.getColumnIndex(DBHelper.FULL_NAME);
            int emailIndex = cursor.getColumnIndex(DBHelper.EMAIL);
            int phoneIndex = cursor.getColumnIndex(DBHelper.PHONE);
            int addressIndex = cursor.getColumnIndex(DBHelper.ADDRESS);
            while (cursor.moveToNext()) {
                id_user = cursor.getString(idIndex);
                tenTK = cursor.getString(usernameIntdex);
                hoTen = cursor.getString(fullnameIndex);
                mail = cursor.getString(emailIndex);
                sdt = cursor.getString(phoneIndex);
                diaChi = cursor.getString(addressIndex);
            }
            SharedPreferences sharedPreferences = getSharedPreferences("thongtintaikhoan", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("iduser", id_user);
            editor.putString("username", tenTK);
            editor.putString("fullname", hoTen);
            editor.putString("email", mail);
            editor.putString("phone", sdt);
            editor.putString("address", diaChi);
            editor.putBoolean("isLoginUser", true);
            editor.apply();
            AlertDialog.Builder builder = new AlertDialog.Builder(DangNhap.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Đăng nhập thành công");
            builder.setNegativeButton("Tiếp tục", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            builder.create().show();
        }
    }
}

