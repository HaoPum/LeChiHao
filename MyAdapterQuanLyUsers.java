package edu.huflit.aappdatvemaybay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapterQuanLyUsers extends BaseAdapter {
    LayoutInflater inflater;
    TextView textView;
    ImageView imageView;

    Button button;
    Context context;

    public MyAdapterQuanLyUsers(Context context)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return QuanLyUsers.users.size();
    }

    @Override
    public Object getItem(int i) {
        return QuanLyUsers.users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return QuanLyUsers.users.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_list_quan_ly_users, null);
        textView = (TextView) view.findViewById(R.id.tv_id_kh_users);
        textView.setText(Long.toString(QuanLyUsers.users.get(i).getId()));
        textView = (TextView) view.findViewById(R.id.tv_username_users);
        textView.setText(QuanLyUsers.users.get(i).getUserName());
        button = (Button) view.findViewById(R.id.btn_xoa_tai_khoan_users);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuanLyUsers.database.xoaUsers(QuanLyUsers.users.get(i));
                QuanLyUsers.users.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
