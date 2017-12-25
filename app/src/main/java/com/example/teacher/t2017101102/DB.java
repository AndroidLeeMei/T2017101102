package com.example.teacher.t2017101102;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/**
 * Created by abc on 2017/11/7.
 */

public class DB {
    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 1;//Version must be >= 1
//    private static final String DATABASE_TABLE = "twzipcode";
//    private static final String DATABASE_CREATE =
//            "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "(_id INTEGER PRIMARY KEY, note TEXT NOT NULL, created INTEGER);";
//    private static final String DATABASE_CREATE =
//            "CREATE TABLE IF NOT EXISTS  " + DATABASE_TABLE
//                    + " (  uid int(10) "
//                    + " , road varchar(10) "
//                    + " ,code varchar(10)"
//                    + "  ,longitude varchar(20)"
//                    + " , latitude varchar(20) "
//                    + " , tgosURL varchar(50)"
//                    + ");";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        Context mCtx;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mCtx = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DBtable.SQL_CREATE_TABLE_TWCODEGOS);
            db.execSQL(DBtable.SQL_CREATE_TABLE_TW5CODE);
            db.execSQL(DBtable.SQL_CREATE_TABLE_MAINCLASS);
        }

//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("DROP TABLE IF EXISTS " + RSSNewsItem.TBL_TW_CODE_GOS);
//            onCreate(db);
//        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DBtable.TBL_TW_CODE_GOS);
            db.execSQL("DROP TABLE IF EXISTS " + DBtable.TBL_TW5CODE);
            db.execSQL("DROP TABLE IF EXISTS " + DBtable.TBL_Main_Class);

            onCreate(db);
        }
    }

    private Context mCtx = null;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DB(Context ctx) {
        this.mCtx = ctx;
    }

    public DB open() throws SQLException {
        dbHelper = new DatabaseHelper(mCtx);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOTE = "classname";
    public static final String KEY_CREATED = "created";

    public Cursor getAll() {
//        return db.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
//        _id,showorder,classname,note,create_time
        return db.query(DBtable.TBL_Main_Class, DBtable.COLARY_MAINCLASS, null, null, null, null, null);
    }

    public Cursor getAll(String tableName) {
        return db.rawQuery("SELECT * FROM " + tableName, null);
//        return db.rawQuery("SELECT * FROM " + RSSNewsItem.TBL_TW_CODE_GOS, null);
//        return db.query(DATABASE_TABLE, strCols, null, null, null, null, null);
    }

    public Cursor getData(String sql){
        return db.rawQuery(sql, null);
    }

    public long create(String noteName) {
        Date now = new Date();
        ContentValues args = new ContentValues();
//        args.put(KEY_NOTE, noteName);
//        args.put(KEY_CREATED, now.getTime());

        args.put("classname", noteName);
        args.put("created", now.getTime());
        return db.insert(DBtable.TBL_Main_Class, null, args);
    }

    public long create(int showOrder,String noteName) {
        Date now = new Date();
        ContentValues args = new ContentValues();
//        args.put(KEY_NOTE, noteName);
//        args.put(KEY_CREATED, now.getTime());

        args.put("classname", noteName);
        args.put("created", now.getYear()+1900 +"-"+now.getMonth()+"-"+now.getDay()
                +" " +now.getHours()+":" +now.getMinutes()+":" +now.getSeconds());
        args.put("showorder", showOrder);

        Log.d("now.getYear()=",now.getYear()+1900+"");
        return db.insert(DBtable.TBL_Main_Class, null, args);
    }

    public void insert(String code,String road,String longitude, String latitude, String tgosURL){
//        db.execSQL("insert into " + DATABASE_TABLE +" (mailcode) values " + mailcode);
        db.execSQL("insert into " + RSSNewsItem.TBL_TW_CODE_GOS + " (code,road,longitude,latitude,tgosURL) values ('"
                + code + "','" + road + "','" + longitude+"','"+ latitude+"','"+tgosURL+"');");

    }
    public void insert(String tblName,String colName,String ... colvalue){
        StringBuilder sqlInsert=new StringBuilder();
        sqlInsert.append("insert into " + tblName + " ("+ colName + ") values (");
        int i=0;
        for (String s:colvalue){
            if (i>0) sqlInsert.append(",");
            sqlInsert.append("'"+s+"'");
            i++;
        }
        sqlInsert.append( ");");
        db.execSQL(sqlInsert.toString());
    }



    public void insert(String sql){
//        db.execSQL("insert into " + DATABASE_TABLE +" (mailcode) values " + mailcode);
        db.execSQL("insert into " + RSSNewsItem.TBL_TW_CODE_GOS + " (mailcode,city,country,road) values ("+ sql+");");


    }



    public boolean delete(long rowId) {
        if (rowId > 0)
            return db.delete(DBtable.TBL_Main_Class, KEY_ROWID + "=" + rowId, null) > 0;
        else
            return db.delete(DBtable.TBL_Main_Class, null, null) > 0;
    }

    public boolean delete() {
        return delete(-1);
    }

    public boolean update(long rowId, String note) {
        ContentValues args = new ContentValues();
        args.put("classname", note);
        return db.update(DBtable.TBL_Main_Class, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

}
