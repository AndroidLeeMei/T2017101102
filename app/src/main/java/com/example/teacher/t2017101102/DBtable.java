package com.example.teacher.t2017101102;

/**
 * Created by kevin on 2017/11/19.
 */

public class DBtable {
    public static final String COL_MAINCLASS="_id,showorder,classname,note,created ";
    public static final String[] COLARY_MAINCLASS={"_id","showorder","classname","note","created"};
    public static final String TBL_Main_Class="mainclass";
    public static final String SQL_CREATE_TABLE_MAINCLASS =
            "CREATE TABLE IF NOT EXISTS  " + TBL_Main_Class
                    + " (  _id INTEGER PRIMARY KEY "
                    + " , created varchar(3)"
                    + " , showorder varchar(10) "
                    + " , classname varchar(10) "
                    + "  ,note varchar(20)"
                    + " , buffer1 varchar(20) "
                    + " , buffer2 varchar(20) "
                    + " , buffer3 varchar(20) "
                    + " , buffer4 varchar(20) "
                    + " , buffer5 varchar(20) "
                    + ");";
    public String uid;
    public String create_time;
    public String showorder;
    public String classname;
    public String note;



    public static final String COL_TW_CODE_GOS="code,area,longitude,latitude,tgosURL";
    public static final String TBL_TW_CODE_GOS="twcodegos";
    public static final String SQL_CREATE_TABLE_TWCODEGOS =
            "CREATE TABLE IF NOT EXISTS  " + TBL_TW_CODE_GOS
                    + " (  uid int(10) "
                    + " , area varchar(10) "
                    + " ,code varchar(3)"
                    + "  ,longitude varchar(20)"
                    + " , latitude varchar(20) "
                    + " , tgosURL varchar(50)"
                    + " , buffer1 varchar(20) "
                    + " , buffer2 varchar(20) "
                    + " , buffer3 varchar(20) "
                    + " , buffer4 varchar(20) "
                    + " , buffer5 varchar(20) "
                    + ");";

    public String code;
    public String area;
    public String latitude;
    public String longitude;
    public String tgosURL;

    //    Zip Code(區域號碼)、City(縣、市名)、Area(鄉、鎮、市及區名)、Road及Scope(街、路段巷名)
    public static final String COL_TW5CODE="zipcode,city,road,roadNumber";
    public static final String TBL_TW5CODE="twzipcode";
    public static final String SQL_CREATE_TABLE_TW5CODE =
            "CREATE TABLE IF NOT EXISTS  " + TBL_TW5CODE
                    + " (  uid int(10) "
                    + " ,zipcode varchar(5)"
                    + "  ,city varchar(20)"
                    + " , road varchar(10) "
                    + " , roadNumber varchar(20) "
                    + " , buffer1 varchar(20) "
                    + " , buffer2 varchar(20) "
                    + " , buffer3 varchar(20) "
                    + " , buffer4 varchar(20) "
                    + " , buffer5 varchar(20) "
                    + ");";
    public String zipcode;
    public String city;
    public String road;
    public String roadNumber;
}
