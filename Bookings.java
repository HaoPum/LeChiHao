package edu.huflit.aappdatvemaybay;

public class Bookings {
    private long id;
    private long id_user;
    private long id_flight;
    private long so_ve;

    private Double tong_tien;

    private String name_khach_hang;

    private String cmnd_khach_hang;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public long getId_flight() {
        return id_flight;
    }

    public void setId_flight(long id_flight) {
        this.id_flight = id_flight;
    }

    public long getSo_ve() {
        return so_ve;
    }

    public void setSo_ve(long so_ve) {
        this.so_ve = so_ve;
    }

    public String getName_khach_hang() {
        return name_khach_hang;
    }

    public void setName_khach_hang(String name_khach_hang) {
        this.name_khach_hang = name_khach_hang;
    }

    public String getCmnd_khach_hang() {
        return cmnd_khach_hang;
    }


    public void setCmnd_khach_hang(String cmnd_khach_hang) {
        this.cmnd_khach_hang = cmnd_khach_hang;
    }

    public Double getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(Double tong_tien) {
        this.tong_tien = tong_tien;
    }
}
