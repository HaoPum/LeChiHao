package edu.huflit.aappdatvemaybay;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Currency;

public class ThemVoucher extends AppCompatActivity {

    MyDatabase database;
    EditText edt_ma_voucher, edt_khuyen_mai;

    String ma_voucher, muc_khuyen_mai;
    Button them_voucher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_voucher);
        edt_ma_voucher = (EditText) findViewById(R.id.ma_voucher);
        edt_khuyen_mai = (EditText) findViewById(R.id.khuyen_mai);
        them_voucher = (Button) findViewById(R.id.btn_them_voucher);
        database = new MyDatabase(this);

        them_voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt_ma_voucher.length() == 0) {
                    String user = "<font color='#FF0000'>Hãy nhập mã voucher</font>";
                    edt_ma_voucher.setHint(Html.fromHtml(user));
                    return;
                } else if (edt_khuyen_mai.length() == 0) {
                    String pass = "<font color='#FF0000'>Không được để trống</font>";
                    edt_khuyen_mai.setHint(Html.fromHtml(pass));
                    return;
                }

                ma_voucher = edt_ma_voucher.getText().toString();
                muc_khuyen_mai = edt_khuyen_mai.getText().toString();
                //Kiểm tra mã voucher nhập vào có tồn tại hay chưa
                if (database.checkVoucher(ma_voucher) == false)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ThemVoucher.this);
                    builder.setTitle("Thêm voucher");
                    builder.setMessage("Mã voucher đã tồn tại");
                    builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                    return;
                }

                Vouchers vouchers = new Vouchers();
                vouchers.setMa_voucher(ma_voucher);
                vouchers.setKhuyen_mai(Float.parseFloat(muc_khuyen_mai));
                database.themVoucher(vouchers);
                AlertDialog.Builder builder = new AlertDialog.Builder(ThemVoucher.this);
                builder.setTitle("Thêm voucher");
                builder.setMessage("Tạo voucher thành công");
                builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });
    }
}