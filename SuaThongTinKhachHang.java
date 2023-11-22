package edu.huflit.aappdatvemaybay;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SuaThongTinKhachHang extends AppCompatActivity {

    MyDatabase database;
    private String idUsers, username, fullname, email, phone, address;

    private EditText edit_fullname, edit_email, edit_phone, edit_address;
    private TextView user_name, id_user;
    Button cap_nhat_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin_khach_hang);
        database = new MyDatabase(this);

        edit_fullname = (EditText) findViewById(R.id.edt_sua_fullname);
        edit_email = (EditText) findViewById(R.id.sua_email);
        edit_phone = (EditText) findViewById(R.id.sua_phone);
        edit_address = (EditText) findViewById(R.id.sua_address);
        user_name = (TextView) findViewById(R.id.tv_show_username_edit);
        id_user = (TextView) findViewById(R.id.id_edit) ;
        cap_nhat_edit = (Button) findViewById(R.id.btn_cap_nhat_sua);

        SharedPreferences sharedPreferences = getSharedPreferences("thongtintaikhoan", MODE_PRIVATE);
        fullname = sharedPreferences.getString("fullname", null);
        email = sharedPreferences.getString("email", null);
        phone = sharedPreferences.getString("phone", null);
        address = sharedPreferences.getString("address", null);
        username = sharedPreferences.getString("username", null);
        idUsers = sharedPreferences.getString("iduser", null);

        edit_fullname.setText(fullname);
        edit_email.setText(email);
        edit_phone.setText(phone);
        edit_address.setText(address);
        user_name.setText(username);
        id_user.setText(idUsers);



        cap_nhat_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("thongtintaikhoan", MODE_PRIVATE);
                String edit_ho_ten, edit_mail, edit_sdt, edit_dia_chi, id, uname;
                edit_ho_ten = edit_fullname.getText().toString();
                edit_mail = edit_email.getText().toString();
                edit_sdt = edit_phone.getText().toString();
                edit_dia_chi = edit_address.getText().toString();
                id = id_user.getText().toString();
                uname = user_name.getText().toString();

                Users users = new Users();
                users.setId(Integer.parseInt(id));
                users.setUserName(uname);
                users.setFullName(edit_ho_ten);
                users.setEmail(edit_mail);
                users.setPhone(edit_sdt);
                users.setAddress(edit_dia_chi);
                database.suaUsers(users);

                SharedPreferences sharedPreferences_update = getSharedPreferences("thongtintaikhoan", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", users.getUserName());
                editor.putString("fullname", users.getFullName());
                editor.putString("email", users.getEmail());
                editor.putString("phone", users.getPhone());
                editor.putString("address", users.getAddress());
                editor.apply();

                AlertDialog.Builder builder = new AlertDialog.Builder(SuaThongTinKhachHang.this);
                builder.setMessage("Cập nhật thông tin thành công");
                builder.setNegativeButton("Quay về", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), ThongTinTaiKhoan.class);
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });
    }

    ////

}