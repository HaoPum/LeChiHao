package edu.huflit.aappdatvemaybay;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class QuanLyUsers extends AppCompatActivity {

    public static ListView listView;
    public static ArrayList<Users> users;
    public static MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_users);
        listView = (ListView) findViewById(R.id.lv_quan_ly_Users);
        database = new MyDatabase(this);
        users = new ArrayList<Users>();
        capNhatDuLieuFlights();
        listView.invalidateViews();
    }
    public void capNhatDuLieuFlights()
    {
        if (users == null)
        {
            users = new ArrayList<>();
        }
        else
        {
            users.removeAll(users);
        }
        Cursor cursor = database.layDuLieuUsers();
        if (cursor != null)
        {
            int idUserIndex = cursor.getColumnIndex(DBHelper.ID_USER);
            int usernameIndex = cursor.getColumnIndex(DBHelper.USER_NAME);

            while (cursor.moveToNext())
            {
                Users users1 = new Users();
                if (idUserIndex != -1)
                {
                    users1.setId(Integer.parseInt(cursor.getString(idUserIndex)));
                }
                if (usernameIndex != -1)
                {
                    users1.setUserName(cursor.getString(usernameIndex));
                }
                users.add(users1);
            }
        }
        if (users != null)
        {
            listView.setAdapter(new MyAdapterQuanLyUsers(getApplicationContext()));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}