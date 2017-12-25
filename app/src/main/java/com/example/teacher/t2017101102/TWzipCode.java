package com.example.teacher.t2017101102;

/**
 * Created by auser on 2017/12/2.
 */

public class TWzipCode {
    public String zipcode;
    public String city;
    public String road;
    public String roadNumber;

    public TWzipCode(String zipcode, String road, String roadNumber) {
        this.zipcode = zipcode;
        this.road = road;
        this.roadNumber = roadNumber;
    }

    public TWzipCode(String zipcode, String city, String road, String roadNumber) {
        this.zipcode = zipcode;
        this.city = city;
        this.road = road;
        this.roadNumber = roadNumber;
    }

    public TWzipCode() {
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getRoad() {
        return road;
    }

    public String getRoadNumber() {
        return roadNumber;
    }
}
