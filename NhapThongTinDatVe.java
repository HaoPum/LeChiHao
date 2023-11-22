package edu.huflit.aappdatvemaybay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NhapThongTinDatVe extends AppCompatActivity {

    EditText ten_hanh_khach, cmnd_hanh_khach, so_luong_ve, nhap_voucher;

    MyDatabase database;
    Button next;
    Float khuyen_mai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thong_tin_dat_ve);
        database = new MyDatabase(this);
        ten_hanh_khach = (EditText) findViewById(R.id.edt_ten_hanh_khach);
        cmnd_hanh_khach = (EditText) findViewById(R.id.edt_cmnd_hanh_khach);
        so_luong_ve = (EditText) findViewById(R.id.edt_so_luong_ve);
        nhap_voucher = (EditText) findViewById(R.id.voucher) ;
        next = (Button) findViewById(R.id.btn_next_xac_nhan);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten, cmnd, soluongve, voucher;
                ten = ten_hanh_khach.getText().toString();
                cmnd = cmnd_hanh_khach.getText().toString();
                soluongve = so_luong_ve.getText().toString();
                voucher = nhap_voucher.getText().toString();

                if (ten.trim().length() == 0)
                {
                        String tb = "<font color='#FF0000'>Không được để trống!</font>";
                        ten_hanh_khach.setHint(Html.fromHtml(tb));
                        return;
                } else if (cmnd.trim().length() == 0) {
                    String tb = "<font color='#FF0000'>Không được để trống!</font>";
                    cmnd_hanh_khach.setHint(Html.fromHtml(tb));
                    return;
                }
                else if (soluongve.trim().length() == 0)
                {
                    String tb = "<font color='#FF0000'>Không được để trống!</font>";
                    so_luong_ve.setHint(Html.fromHtml(tb));
                    return;
                }

                //lấy mức giảm của voucher
                if (voucher.trim().length() == 0)
                {
                    khuyen_mai = 1.0f;
                }
                else
                {
                    Cursor cursor = database.layMucGiamGia(voucher);
                    if (cursor != null)
                    {
                        Vouchers vouchers = new Vouchers();
                        while (cursor.moveToNext())
                        {
                            int muc_khuyen_mai = cursor.getColumnIndex(DBHelper.KHUYEN_MAI);
                            if (muc_khuyen_mai != -1)
                            {
                                vouchers.setKhuyen_mai(Float.parseFloat(cursor.getString(muc_khuyen_mai)));
                                khuyen_mai = vouchers.getKhuyen_mai();
                            }
                        }
                    }
                }

                SharedPreferences sharedPreferences = getSharedPreferences("thongtinhanhkhach", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("tenhanhkhach", ten);
                editor.putString("cmnd", cmnd);
                editor.putString("soluongve", soluongve);
                editor.putFloat("voucher", khuyen_mai);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), XacNhanDatVe.class);
                startActivity(intent);
            }
        });

    }
}