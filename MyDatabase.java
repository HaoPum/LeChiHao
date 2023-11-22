package edu.huflit.aappdatvemaybay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabase {
    SQLiteDatabase database;
    DBHelper helper;
    public MyDatabase(Context context)
    {
        helper = new DBHelper(context);
        database = helper.getWritableDatabase();
    }

    //CHỨC NĂNG ĐĂNG KÝ
    public long addUser(Users users)
    {
        ContentValues values = new ContentValues();
        values.put(DBHelper.USER_NAME, users.getUserName());
        values.put(DBHelper.PASSWORD, users.getPassWord());
        values.put(DBHelper.FULL_NAME, users.getFullName());
        values.put(DBHelper.EMAIL, users.getEmail());
        values.put(DBHelper.PHONE, users.getPhone());
        values.put(DBHelper.ADDRESS, users.getAddress());
        return database.insert(DBHelper.TABLE_USER, null, values);
    }
    public long addBookings(Bookings bookings)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.USER_ID, bookings.getId_user());
        contentValues.put(DBHelper.FLIGHT_ID, bookings.getId_flight());
        contentValues.put(DBHelper.SO_VE, bookings.getSo_ve());
        contentValues.put(DBHelper.TONG_TIEN, bookings.getTong_tien());
        contentValues.put(DBHelper.NAME_KHACH_HANG, bookings.getName_khach_hang());
        contentValues.put(DBHelper.CMND_KHACH_HANG, bookings.getCmnd_khach_hang());

        return database.insert(DBHelper.TABLE_BOOKING, null, contentValues);
    }
    public boolean checkUserName(String username)
    {
        String selectQuery = "SELECT * FROM " + DBHelper.TABLE_USER + " WHERE " + DBHelper.USER_NAME + " = '" + username + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            return false;
        }
        return true;
    }
    public Cursor layDuLieuUsers()
    {
        String[] cot = {DBHelper.ID_USER,
                DBHelper.USER_NAME,
                DBHelper.PASSWORD,
                DBHelper.FULL_NAME,
                DBHelper.EMAIL,
                DBHelper.PHONE,
                DBHelper.ADDRESS
        };
        Cursor cursor = database.query(DBHelper.TABLE_USER, cot, null, null, null, null, DBHelper.ID_USER);
        return cursor;
    }

    //ĐĂNG NHẬP
    public boolean checkLogin(String username, String password)
    {
        String query = "SELECT * FROM " + DBHelper.TABLE_USER + " WHERE " + DBHelper.USER_NAME + " = ? AND " + DBHelper.PASSWORD + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{username,password});
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }
    public Cursor layThongTinLogin(String username)
    {
        String query = "SELECT * FROM " + DBHelper.TABLE_USER + " WHERE " + DBHelper.USER_NAME + " = '" + username + "'";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
    //THÊM CHUYẾN BAY
    public Cursor layDuLieuFlights()
    {
        String[] cot = {DBHelper.ID_FLIGHT,
        DBHelper.DIEM_DI,
        DBHelper.DIEM_DEN,
        DBHelper.TIME_DI,
        DBHelper.TIME_DEN,
        DBHelper.GIA_VE};
        Cursor cursor = database.query(DBHelper.TABLE_FLIGHT, cot, null, null, null, null, null);
        return cursor;
    }
    public long themFlights(Flights flights)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.DIEM_DI, flights.getDiem_di());
        contentValues.put(DBHelper.DIEM_DEN, flights.getDiem_den());
        contentValues.put(DBHelper.TIME_DI, flights.getTime_di());
        contentValues.put(DBHelper.TIME_DEN, flights.getTime_den());
        contentValues.put(DBHelper.GIA_VE, flights.getGia_ve());
        return database.insert(DBHelper.TABLE_FLIGHT, null, contentValues);
    }
    //TÌM KIẾM CHUYẾN BAY
    public Cursor searchFlights(String diem_di, String diem_den)
    {
        String query = "SELECT * FROM " + DBHelper.TABLE_FLIGHT + " WHERE " + DBHelper.DIEM_DI + " LIKE " + "'%" + diem_di + "%'" + " AND " + DBHelper.DIEM_DEN + " LIKE " + "'%" + diem_den + "%'";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    //XÓA
    //xóa chuyến bay
    public long xoaFligths(Flights flights)
    {
        return database.delete(DBHelper.TABLE_FLIGHT, DBHelper.DIEM_DI + " = " + "'" + flights.getDiem_di() + "'", null);
    }
    //xóa bookings
    public long xoaBookings(Bookings bookings)
    {
        return database.delete(DBHelper.TABLE_BOOKING, DBHelper.CMND_KHACH_HANG + " = " + "'" + bookings.getCmnd_khach_hang() + "'", null);
    }
    //xóa user
    public long xoaUsers(Users users)
    {
        return database.delete(DBHelper.TABLE_USER, DBHelper.USER_NAME + " = " + "'" + users.getUserName() + "'", null);
    }
    //SỬA CHUYẾN BAY
    public long suaFlights(Flights flights)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.DIEM_DI, flights.getDiem_di());
        contentValues.put(DBHelper.DIEM_DEN, flights.getDiem_den());
        contentValues.put(DBHelper.TIME_DI, flights.getTime_di());
        contentValues.put(DBHelper.TIME_DEN, flights.getTime_den());
        contentValues.put(DBHelper.GIA_VE, flights.getGia_ve());
        return database.update(DBHelper.TABLE_FLIGHT, contentValues, DBHelper.ID_FLIGHT + " = " + flights.getId(), null);
    }
    public long suaUsers(Users users)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.FULL_NAME, users.getFullName());
        contentValues.put(DBHelper.EMAIL, users.getEmail());
        contentValues.put(DBHelper.PHONE, users.getPhone());
        contentValues.put(DBHelper.ADDRESS, users.getAddress());
        return database.update(DBHelper.TABLE_USER, contentValues, DBHelper.ID_USER + " = " + users.getId(), null);
    }

    //BOOKINGS
    public Cursor layDuLieuBookings()
    {
     String[] cot = {
             DBHelper.ID_BOOKING,
             DBHelper.USER_ID,
             DBHelper.FLIGHT_ID,
             DBHelper.NAME_KHACH_HANG,
             DBHelper.CMND_KHACH_HANG,
             DBHelper.SO_VE,
             DBHelper.TONG_TIEN
     };
     Cursor cursor = database.query(DBHelper.TABLE_BOOKING, cot, null, null, null, null, null);
     return cursor;
    }
    //LẤY DỮ LIỆU CHO LỊCH SỬ ĐẶT VÉ CỦA NGƯỜI DÙNG VỚI ID
    public Cursor lichSuDatVe(Integer id)
    {
        String query = "SELECT * FROM " + DBHelper.TABLE_BOOKING + " WHERE " + DBHelper.USER_ID + " = " + id;
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
    //lấy điểm đi và điểm đến
    public Cursor layDuLieuFlightChoLichSuVe(Long id_flight)
    {
        String query = "SELECT * FROM " + DBHelper.TABLE_FLIGHT + " WHERE " + DBHelper.ID_FLIGHT + " = " + id_flight;
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
    //ĐĂNG NHẬP ADMIN
    public boolean checkPhanQuyen(String username, String password)
    {
        String query = "SELECT * FROM " + DBHelper.TABLE_USER + " WHERE " + DBHelper.USER_NAME + " = ? AND " + DBHelper.PASSWORD + " = ?" + " AND " + DBHelper.ID_USER + " = " + 1;
        Cursor cursor = database.rawQuery(query, new String[]{username,password});
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }
    //Kiểm tra mã voucher tồn tại chưa
    public boolean checkVoucher(String voucher)
    {
        String selectQuery = "SELECT * FROM " + DBHelper.TABLE_VOUCHER + " WHERE " + DBHelper.MA_VOUCHER + " = '" + voucher + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            return false;
        }
        return true;
    }

    //THÊM VOUCHER
    public long themVoucher(Vouchers vouchers)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.MA_VOUCHER, vouchers.getMa_voucher());
        contentValues.put(DBHelper.KHUYEN_MAI, vouchers.getKhuyen_mai());
        return database.insert(DBHelper.TABLE_VOUCHER, null, contentValues);
    }
    public Cursor layMucGiamGia(String ma_voucher)
    {
        String query = "SELECT * FROM " + DBHelper.TABLE_VOUCHER + " WHERE " + DBHelper.MA_VOUCHER + " = '" + ma_voucher +"'";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
}
