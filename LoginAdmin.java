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

public class LoginAdmin extends AppCompatActivity {

    MyDatabase database;
    EditText username, password;
    String ten_tk, mat_khau;
    String tenTK, hoTen, mail, sdt, diaChi, id_user;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        username = (EditText) findViewById(R.id.edtUsernameAdmin);
        password = (EditText) findViewById(R.id.edtPasswordAdmin);
        btnLogin = (Button) findViewById(R.id.btnLoginAdmin);
        database = new MyDatabase(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangNhap();
            }
        });
    }


    public boolean checkThongTin() {
        if (username.length() == 0) {
            String user = "<font color='#FF0000'>Tên đăng nhập không được để trống!</font>";
            username.setHint(Html.fromHtml(user));
            return false;
        } else if (password.length() == 0) {
            String pass = "<font color='#FF0000'>Mật khẩu không được để trống!</font>";
            password.setHint(Html.fromHtml(pass));
            return false;
        }
        return true;
    }
    public void dangNhap()
    {
        if (checkThongTin() == false)
        {
            return;
        }
        ten_tk = username.getText().toString();
        mat_khau = password.getText().toString();
        if (database.checkPhanQuyen(ten_tk, mat_khau) == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginAdmin.this);
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
        Cursor cursor = database.layThongTinLogin(ten_tk);
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
            editor.putBoolean("isLoginUser", false);
            editor.putBoolean("isLoginAdmin", true);
            editor.apply();
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginAdmin.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Đăng nhập thành công");
            builder.setNegativeButton("Tiếp tục", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getApplicationContext(), DisplayAdmin.class);
                    startActivity(intent);
                }
            });
            builder.create().show();
        }
    }
}