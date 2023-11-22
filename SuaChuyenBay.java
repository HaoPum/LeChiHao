package edu.huflit.aappdatvemaybay;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SuaChuyenBay extends AppCompatActivity {

    public static ListView listView;
    public static ArrayList<Flights> flights;
    public static MyDatabase database;

    private EditText edt_diem_di, edt_diem_den, edt_time_di, edt_time_den, edt_gia_ve;
    private TextView tv_id_chuyen_bay;
    String diem_di, diem_den, time_di, time_den, gia_ve, id_flights;
    Button btn_sua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_chuyen_bay);
        tv_id_chuyen_bay = (TextView) findViewById(R.id.tv_id_flights);
        edt_diem_di = (EditText) findViewById(R.id.edt_sua_diem_di);
        edt_diem_den = (EditText) findViewById(R.id.edt_sua_diem_den);
        edt_time_di = (EditText) findViewById(R.id.edt_sua_time_di);
        edt_time_den = (EditText) findViewById(R.id.edt_sua_time_den);
        edt_gia_ve = (EditText) findViewById(R.id.edt_sua_gia_ve);
        btn_sua = (Button) findViewById(R.id.btn_sua_chuyen_bay);
        listView = (ListView) findViewById(R.id.lv_sua_chuyen_bay);
        database = new MyDatabase(this);
        flights = new ArrayList<Flights>();

        capNhatDuLieuFlights();
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sua(listView);
                capNhatDuLieuFlights();
            }
        });
    }
    public void sua(View view)
    {
        Flights flights1 = layDuLieuFligths();
        if (flights1 != null)
        {
            database.suaFlights(flights1);
            capNhatDuLieuFlights();
            listView.invalidateViews();
            edt_diem_di.setText(null);
            edt_diem_den.setText(null);
            edt_time_di.setText(null);
            edt_time_den.setText(null);
            edt_gia_ve.setText(null);
        }
    }
    public Flights layDuLieuFligths()
    {
        id_flights = tv_id_chuyen_bay.getText().toString();
        diem_di = edt_diem_di.getText().toString();
        diem_den = edt_diem_den.getText().toString();
        time_di = edt_time_di.getText().toString();
        time_den = edt_time_den.getText().toString();
        gia_ve = edt_gia_ve.getText().toString();
        if (diem_di.trim().length() == 0 || diem_den.trim().length() == 0 || time_di.trim().length() == 0|| time_den.trim().length() == 0|| gia_ve.trim().length() == 0)
        {
            return null;
        }
        Flights flights1 = new Flights();
        flights1.setId(Integer.parseInt(id_flights));
        flights1.setDiem_di(diem_di);
        flights1.setDiem_den(diem_den);
        flights1.setTime_di(time_di);
        flights1.setTime_den(time_den);
        flights1.setGia_ve(Double.parseDouble(gia_ve));
        return flights1;
    }
    public void capNhatDuLieuFlights()
    {
        if (flights == null)
        {
            flights = new ArrayList<>();
        }
        else
        {
            flights.removeAll(flights);
        }
        Cursor cursor = database.layDuLieuFlights();
        if (cursor != null)
        {
            int idIndex = cursor.getColumnIndex(DBHelper.ID_FLIGHT);
            int diemdiIndex = cursor.getColumnIndex(DBHelper.DIEM_DI);
            int diemdenIndex = cursor.getColumnIndex(DBHelper.DIEM_DEN);
            int timediIndex = cursor.getColumnIndex(DBHelper.TIME_DI);
            int timedenIndex = cursor.getColumnIndex(DBHelper.TIME_DEN);
            int giaveIndex = cursor.getColumnIndex(DBHelper.GIA_VE);
            while (cursor.moveToNext())
            {
                Flights flights1 = new Flights();
                if (idIndex != -1)
                {
                    flights1.setId(Integer.parseInt(cursor.getString(idIndex)));
                }
                if (diemdiIndex != -1)
                {
                    flights1.setDiem_di(cursor.getString(diemdiIndex));
                }
                if (diemdenIndex != -1)
                {
                    flights1.setDiem_den(cursor.getString(diemdenIndex));
                }
                if (timediIndex != -1)
                {
                    flights1.setTime_di(cursor.getString(timediIndex));
                }
                if (timedenIndex != -1)
                {
                    flights1.setTime_den(cursor.getString(timedenIndex));
                }
                if (giaveIndex != -1)
                {
                    flights1.setGia_ve(Double.parseDouble(cursor.getString(giaveIndex)));
                }
                flights.add(flights1);
            }
        }
        if (flights != null)
        {
            listView.setAdapter(new MyAdapterSuaChuyenBay(getApplicationContext()));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_id_chuyen_bay.setText(Long.toString(flights.get(i).getId()));
                edt_diem_di.setText(flights.get(i).getDiem_di());
                edt_diem_den.setText(flights.get(i).getDiem_den());
                edt_time_di.setText(flights.get(i).getTime_di());
                edt_time_den.setText(flights.get(i).getTime_den());
                edt_gia_ve.setText(Double.toString(flights.get(i).getGia_ve()));
            }
        });
    }
}