package com.example.teacher.t2017101102;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> alr = new ArrayList();
    //    String[] str=new String[4];
    SQLiteDatabase db;
    String outFilename;
//    static int i=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);

    }

    public void click1(View v)

    {
        mDbHelper = new DB(this).open();
        new Thread() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://download.post.gov.tw/post/download/1050812_行政區經緯度(toPost).xml");
//                    url = new URL("https://udn.com/rssfeed/news/2/6638?ch=news");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream inputStream;
                    inputStream = conn.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    final StringBuilder sb = new StringBuilder();
                    String str;
                    while ((str = br.readLine()) != null) {
                        sb.append(str);
                    }
                    String result = sb.toString();
                    final MyDataHandler dataHandler = new MyDataHandler();
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    SAXParser sp = spf.newSAXParser();
                    XMLReader xr = sp.getXMLReader();
                    xr.setContentHandler(dataHandler);
                    xr.parse(new InputSource(new StringReader(result)));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<String> forAdapter = new ArrayList();

                            for (RSSNewsItem i : dataHandler.data) {
                                forAdapter.add(i.code + " " + i.area + " " + i.latitude + i.longitude);
                                Log.d("i.code=", i.code + " " + i.area);
//                                forAdapter.add(i.road+ " "+…i.road);
//                                clovalue=new String[5];
                                mDbHelper.insert(RSSNewsItem.TBL_TW_CODE_GOS, RSSNewsItem.COL_TW_CODE_GOS, (i.code + "," + i.area + "," + i.latitude + "," + i.longitude + "," + i.tgosURL).split(","));
//                                str[0]=i.code;

//                                alr.add("'"+i.code+"','"+i.road+"','"+i.city+"','"+i.addr_Number+"'");
//                                insertDB();
//                                mDbHelper.insert("1","1","1","1");
//                                forAdapter.add(i.road);
//                                forAdapter.add(i.city);
//                                forAdapter.add(i.addr_Number);
                            }
                            mDbHelper.close();
                            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, forAdapter);
                            lv.setAdapter(adapter);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    Intent it = new Intent(MainActivity.this, DetailActivity.class);
//                                    it.putExtra("link", dataHandler.data.get(i).link);
//                                    startActivity(it);
                                }
                            });
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }

        }.start();

    }

    public void click5code(View target) {
        mDbHelper = new DB(this).open();
        new Thread() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://download.post.gov.tw/post/download/Xml_10510.xml");
//                    url = new URL("http://download.post.gov.tw/post/download/1050812_行政區經緯度(toPost).xml");
//                    url = new URL("https://udn.com/rssfeed/news/2/6638?ch=news");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream inputStream;
                    inputStream = conn.getInputStream();
                    Log.d("1=", "1");
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    final StringBuilder sb = new StringBuilder();
                    String str;
                    while ((str = br.readLine()) != null) {
                        sb.append(str);
                    }
                    String result = sb.toString();
                    final DataHandlerTWCode5 dataHandler = new DataHandlerTWCode5();
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    SAXParser sp = spf.newSAXParser();
                    XMLReader xr = sp.getXMLReader();
                    xr.setContentHandler(dataHandler);
                    xr.parse(new InputSource(new StringReader(result)));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<String> forAdapter = new ArrayList();

                            for (RSSNewsItem i : dataHandler.data) {
//                                zipcode,city,road,roadNumber
                                forAdapter.add(i.zipcode + " " + i.city + " " + i.road + " " + i.roadNumber);
//                                Log.d("i.code=",i.road+ " "+i.road);
//                                forAdapter.add(i.road+ " "+…i.road);
//                                clovalue=new String[5];
                                mDbHelper.insert(RSSNewsItem.TBL_TW5CODE, RSSNewsItem.COL_TW5CODE, (i.zipcode + "," + i.city + "," + i.road + "," + i.roadNumber).split(","));
//                                str[0]=i.code;

//                                alr.add("'"+i.code+"','"+i.road+"','"+i.city+"','"+i.addr_Number+"'");
//                                insertDB();
//                                mDbHelper.insert("1","1","1","1");
//                                forAdapter.add(i.road);
//                                forAdapter.add(i.city);
//                                forAdapter.add(i.addr_Number);
                            }
                            mDbHelper.close();
                            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, forAdapter);
                            lv.setAdapter(adapter);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    Intent it = new Intent(MainActivity.this, DetailActivity.class);
//                                    it.putExtra("link", dataHandler.data.get(i).link);
//                                    startActivity(it);
                                }
                            });
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }

        }.start();
    }
//

    public void insertDB4(View t) {
//        SQLiteDatabase db = new SQLiteDatabase();
//        db.execSQL("insert into person(name, age) values('安桌', 4)");
//        db.close();
    }

    public void insertDB3(View target) {
        SQLiteDatabase db;
        String outFilename;

//        outFilename = getDatabasePath("restaruant.db").getAbsolutePath() + File.separator + "restaruant.db";
        outFilename = getDatabasePath("restaruant.db").getAbsolutePath();
        File f = new File(outFilename);
        Log.d("outFilename=", getDatabasePath("restaruant.db").getAbsolutePath());
//        Log.d("f.exists()=", f.exists()+"");
        try {
            if (!f.exists()) {
                copyDatabase();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = SQLiteDatabase.openOrCreateDatabase(outFilename, null);
//         Cursor c = db.rawQuery("select * from phone", new String[] {});
//        Cursor c = db.query("phone", new String[] {"ID", "Name", "Tel", "Addr"}, null, null, null, null, null);
//        if (c.moveToFirst())
//        {
//            do {
//                String n = c.getString(1);
//                Log.d("MYDB", n);
//            }while(c.moveToNext());
//        }
//        ContentValues cv = new ContentValues();
//        i++;
//        cv.put("ID", i);
//        cv.put("Name", "DDD");
//        cv.put("Tel", "12312312");
//        cv.put("Addr", "334455");
//        db.insert("phone", null, cv);
//
//        db.delete("phone", "ID=?", new String[] {"2"});
//
//        ContentValues cv2 = new ContentValues();
//        cv2.put("Name", "A1A2A3");
//        db.update("phone", cv2, "ID=?", new String[] {"1"});
    }


    private void copyDatabase() throws IOException {
        // Path to the empty database.


        // Open the empty database as the output stream.
        OutputStream outputDatabase = new FileOutputStream(outFilename);
        // Transfer bytes from the input file to the output file.
        byte[] buffer = new byte[1024];
        InputStream is = this.getAssets().open("students1.sqlite");
        int length;
        while ((length = is.read(buffer)) > 0) {
            outputDatabase.write(buffer, 0, length);
        }
        is.close();

        outputDatabase.flush();
        outputDatabase.close();

    }


    private DB mDbHelper;
    ;


    String sql;

    public void insertDB() {
//        mDbHelper  = new DB(this).open();
//        sql="insert into " + DATABASE_TABLE + " (mailcode,city,country,road) values ('"
////                + mailcode + "','" + city + "','" + country+"','"+ road+"');"
//        mDbHelper.insert("1234","1234","1234","1234");


//        setAdapter();
    }


    private Cursor mNotesCursor;
//    private void setAdapter() {
//        mNotesCursor = mDbHelper.getAll();
//        if (mNotesCursor != null)
//            mNotesCursor.moveToFirst();
//        startManagingCursor(mNotesCursor);
//        String[] from = new String[]{"code", "road"};
//        int[] to = new int[]{R.id.text1, R.id.text2};
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                R.layout.simple_list_item_1, mNotesCursor, from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
////        lv.setAdapter(adapter);
//    }

    //=========================================================================
    public void insetFirebase(View target) {
        new Thread() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://download.post.gov.tw/post/download/Xml_10510.xml");
//                    url = new URL("http://download.post.gov.tw/post/download/1050812_行政區經緯度(toPost).xml");
//                    url = new URL("https://udn.com/rssfeed/news/2/6638?ch=news");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream inputStream;
                    inputStream = conn.getInputStream();
                    Log.d("1=", "1");
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    final StringBuilder sb = new StringBuilder();
                    String str;
                    while ((str = br.readLine()) != null) {
                        sb.append(str);
                    }
                    String result = sb.toString();
                    final DataHandlerTWCode5 dataHandler = new DataHandlerTWCode5();
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    SAXParser sp = spf.newSAXParser();
                    XMLReader xr = sp.getXMLReader();
                    xr.setContentHandler(dataHandler);
                    xr.parse(new InputSource(new StringReader(result)));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            ArrayList<String> forAdapter = new ArrayList();
                            DatabaseReference mDatabase;
                            for (RSSNewsItem i : dataHandler.data) {
                                 mDatabase = FirebaseDatabase.getInstance().getReference();

//                                zipcode,city,road,roadNumber
                                TWzipCode tWzipCode=new TWzipCode(i.zipcode, i.road, i.roadNumber);
                                mDatabase = mDatabase.child("TWzipCode").child(i.city);
//                                forAdapter.add(i.zipcode + " " + i.city + " " + i.road + " " + i.roadNumber);
                                mDatabase.setValue(tWzipCode);
                            }

                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }

        }.start();

        Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
    }


}
