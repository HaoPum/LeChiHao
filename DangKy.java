package edu.huflit.aappdatvemaybay;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangKy extends AppCompatActivity {

    EditText edt_username, edt_password, edt_full_name, edt_email, edt_phone, edt_address;
    private String username, password, full_name, email, phone, address;
    Button btn_DangKy;
    MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        anhXa();
        database = new MyDatabase(this);
        //ẤN BUTTON ĐĂNG KÝ
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkNhapThongTin();
            }
        });

    }
    private void anhXa()
    {
        edt_username = (EditText) findViewById(R.id.dangkyUserName);
        edt_password = (EditText) findViewById(R.id.dangkyPassWord);
        edt_full_name = (EditText) findViewById(R.id.dangkyFullName);
        edt_email = (EditText) findViewById(R.id.dangkyEmail);
        edt_phone = (EditText) findViewById(R.id.dangkyPhone);
        edt_address = (EditText) findViewById(R.id.dangkyAddress);
        btn_DangKy = (Button) findViewById(R.id.btnDangKy);
    }
    public void checkNhapThongTin()
    {
        if(edt_username.length() == 0)
        {
            String user = "<font color='#FF0000'>Tên đăng nhập không được để trống!</font>";
            edt_username.setHint(Html.fromHtml(user));
        } else if (edt_password.length() == 0)
        {
            String pass = "<font color='#FF0000'>Mật khẩu không được để trống!</font>";
            edt_password.setHint(Html.fromHtml(pass));
        }
        else if (edt_full_name.length() == 0)
        {
            String fullname = "<font color='#FF0000'>Họ tên không được để trống!</font>";
            edt_full_name.setHint(Html.fromHtml(fullname));
        }
        else if (edt_email.length() == 0)
        {
            String email = "<font color='#FF0000'>Email không được để trống!</font>";
            edt_email.setHint(Html.fromHtml(email));
        }
        else if (edt_phone.length() == 0)
        {
            String sdt = "<font color='#FF0000'>Số điện thoại không được để trống!</font>";
            edt_phone.setHint(Html.fromHtml(sdt));
        }
        else if (edt_address.length() == 0)
        {
            String address = "<font color='#FF0000'>Địa chỉ không được để trống!</font>";
            edt_address.setHint(Html.fromHtml(address));
        }
        else
        {
            themDangKy();
        }
    }
    public Users layDuLieu()
    {
        username = edt_username.getText().toString().trim();
        password = edt_password.getText().toString().trim();
        full_name = edt_full_name.getText().toString().trim();
        email = edt_email.getText().toString().trim();
        phone = edt_phone.getText().toString().trim();
        address = edt_address.getText().toString().trim();
        //KIỂM TRA USERNAME TỒN TẠI CHƯA
        if (database.checkUserName(username) == false)
        {
            edt_username.setText("");
            String tb = "<font color='#FF0000'>Username tồn tại </font>";
            edt_username.setHint(Html.fromHtml(tb));
            return null;
        }
        Users users = new Users();
        users.setUserName(username);
        users.setPassWord(password);
        users.setFullName(full_name);
        users.setEmail(email);
        users.setPhone(phone);
        users.setAddress(address);
        return users;
    }
    public void themDangKy()
    {
        Users users = layDuLieu();
        if (users != null)
        {
            if (database.addUser(users) != -1)
            {
                capNhatDuLieu();
                AlertDialog.Builder builder = new AlertDialog.Builder(DangKy.this);
                builder.setTitle("Đăng ký");
                builder.setMessage("Đăng ký thành công");
                builder.setNegativeButton("Quay về trang chủ ", new DialogInterface.OnClickListener() {
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

    public void capNhatDuLieu()
    {
        Cursor cursor = database.layDuLieuUsers();
        if (cursor != null)
        {
            int idIndex = cursor.getColumnIndex(DBHelper.ID_USER);
            int usernameIndex = cursor.getColumnIndex(DBHelper.USER_NAME);
            int passwordIndex = cursor.getColumnIndex(DBHelper.PASSWORD);
            int fullnameIndex = cursor.getColumnIndex(DBHelper.FULL_NAME);
            int emailIndex = cursor.getColumnIndex(DBHelper.EMAIL);
            int phoneIndex = cursor.getColumnIndex(DBHelper.PHONE);
            int addressIndex = cursor.getColumnIndex(DBHelper.ADDRESS);
            while (cursor.moveToNext())
            {
                Users users = new Users();
                if (idIndex != -1)
                {
                    users.setId(Integer.parseInt(cursor.getString(idIndex)));
                }
                if (usernameIndex != -1)
                {
                    users.setUserName(cursor.getString(usernameIndex));
                }
                if (passwordIndex != -1)
                {
                    users.setPassWord(cursor.getString(passwordIndex));
                }
                if (fullnameIndex != -1)
                {
                    users.setFullName(cursor.getString(fullnameIndex));
                }
                if (emailIndex != -1)
                {
                    users.setEmail(cursor.getString(emailIndex));
                }
                if (phoneIndex != -1)
                {
                    users.setPhone(cursor.getString(phoneIndex));
                }
                if(addressIndex != -1)
                {
                    users.setAddress(cursor.getString(addressIndex));
                }
            }
        }
    }
}