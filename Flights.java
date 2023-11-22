package edu.huflit.aappdatvemaybay;

public class Flights {
    private long id;
    private String diem_di;
    private String diem_den;
    private String time_di;
    private String time_den;
    private Double gia_ve;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiem_di() {
        return diem_di;
    }

    public void setDiem_di(String diem_di) {
        this.diem_di = diem_di;
    }

    public String getDiem_den() {
        return diem_den;
    }

    public void setDiem_den(String diem_den) {
        this.diem_den = diem_den;
    }

    public String getTime_di() {
        return time_di;
    }

    public void setTime_di(String time_di) {
        this.time_di = time_di;
    }

    public String getTime_den() {
        return time_den;
    }

    public void setTime_den(String time_den) {
        this.time_den = time_den;
    }

    public Double getGia_ve() {
        return gia_ve;
    }

    public void setGia_ve(Double gia_ve) {
        this.gia_ve = gia_ve;
    }
}
