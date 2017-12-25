package com.example.teacher.t2017101102;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by teacher on 2017/10/11.
 */

public class MyDataHandler extends DefaultHandler {

    boolean isCode = false;
    boolean isLongitude = false;
    boolean isArea = false;//行政區名
    boolean isLatitude;
    boolean isTgosURL;

    public ArrayList<RSSNewsItem> data = new ArrayList<>();
    RSSNewsItem item;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("行政區名")) {
            item = new RSSNewsItem();
            isArea = true;
        }
        if (qName.equals("_x0033_碼郵遞區號")) isCode = true;
        if (qName.equals("中心點經度")) isLongitude = true;
        if (qName.equals("中心點緯度")) isLatitude = true;
        if (qName.equals("TGOS_URL")) isTgosURL = true;

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (qName.equals("中心點經度")) isLongitude = false;
        if (qName.equals("行政區名")) isArea = false;
        if (qName.equals("_x0033_碼郵遞區號")) isCode = false;
        if (qName.equals("中心點緯度")) isLatitude = false;
        if (qName.equals("TGOS_URL")) {
            isTgosURL = false;
            flag = false;
        }

    }

    boolean flag = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //目前只取出桃園的區碼
        if (isArea) {
            String s = new String(ch, start, length);
            if (s.startsWith("桃園")) {
                item.area = new String(ch, start, length);
                flag = true;
            }
        }
        if (isCode && flag)item.code = new String(ch, start, length);
        if (isLongitude && flag)item.longitude = new String(ch, start, length);
        if (isLatitude && flag) item.latitude = new String(ch, start, length);
        if (isTgosURL && flag) {
            item.tgosURL = new String(ch, start, length);
            data.add(item);
        }

    }
}

