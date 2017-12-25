package com.example.teacher.t2017101102;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by kevin on 2017/11/16.
 */

public class DataHandlerTWCode5 extends DefaultHandler {

    boolean isZipCode = false;
    boolean isCity = false;
    boolean isRoad = false;//行政區名
    boolean isRoadNumber;
    boolean flag = false;//控制只取桃園的資料


    public ArrayList<RSSNewsItem> data = new ArrayList<>();
    RSSNewsItem item;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("欄位1")) {
            item = new RSSNewsItem();
            isZipCode = true;
        }
        if (qName.equals("欄位4")) isCity = true;
        if (qName.equals("欄位2")) isRoad = true;
        if (qName.equals("欄位3")) isRoadNumber = true;
    }
//    public static final String COL_TW5CODE="zipcode,city,road,roadNumber";

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("欄位1")) isZipCode = false;
        if (qName.equals("欄位2")) isRoad = false;
        if (qName.equals("欄位3")) isRoadNumber = false;
        if (qName.equals("欄位4")) {
            isCity = false;
            flag = false;
        }
    }



    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //目前只取出桃園的區碼
        String s="";
        if (isCity) {
            s = new String(ch, start, length);
            if (s.startsWith("桃園")) {
                item.city = new String(ch, start, length);
                flag = true;
            }
        }
        if (isRoad)item.road = new String(ch, start, length);
        if (isZipCode) item.zipcode = new String(ch, start, length);
        if (isRoadNumber) item.roadNumber = new String(ch, start, length);

        if (flag) data.add(item);


    }
}

