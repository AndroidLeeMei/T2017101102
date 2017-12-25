package com.example.teacher.t2017101102;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddHouseDataActivity extends AppCompatActivity {
    private Spinner sp;//第一個下拉選單
    private Spinner sp2;//第二個下拉選單
    private Context context;

    ArrayAdapter<String> adapter ;
    ArrayAdapter<String> adapter2;

    private ArrayList<String> alrRoadDown=new ArrayList<>();
    private ArrayList<String> alrCity=new ArrayList<>();
    private ArrayList<String> alrRoad=new ArrayList<>();
    private DB mDbHelper;
    private Cursor mNotesCursor;

    EditText edtName,edtTel,edtAddr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house_data);

        context = this;
        findViews();
//
//        mDbHelper = new DB(this).open();
//        mNotesCursor = mDbHelper.getAll(RSSNewsItem.TBL_TW5CODE);
//
//        if (mNotesCursor!=null)
//            mNotesCursor.moveToFirst();
//        while (!mNotesCursor.isAfterLast()){
//            if (!alrCity.contains(mNotesCursor.getString(2))&&(mNotesCursor.getString(2).length()==6)) {
//                alrCity.add(mNotesCursor.getString(2));
//            }
//            if (mNotesCursor.getString(2).trim().equals(alrCity.get(0))&&(mNotesCursor.getString(3).trim().length()>2))
//                alrRoad.add(mNotesCursor.getString(3));
//            mNotesCursor.moveToNext();
//        }
//        mNotesCursor.close();
//        mDbHelper.close();

//        Log.d("col0",alrCity.toString()+"");
//        Log.d("col0",alrRoad.toString()+"");

        //程式剛啟始時載入第一個下拉選單
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, alrCity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp = (Spinner) findViewById(R.id.spinnerCity);
        sp.setAdapter(adapter);

//
        //因為下拉選單第一個為中壢，所以先載入中壢群組進第二個下拉選單
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, alrRoad);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2 = (Spinner) findViewById(R.id.spinnerRoad);
        sp2.setAdapter(adapter2);

        setlisenter();
//        edtAddr.setText("桃園市中壢區");

    }

//    //第一個下拉類別的監看式
//    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener(){
//        public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
//
//            int pos = sp.getSelectedItemPosition();
//            switch (parent.getId()){
//                case R.id.spinnerCity:
//                    Log.d("col==","spinnerCity");
//                    //讀取第一個下拉選單是選擇第幾個
//
//                    alrRoadDown=new ArrayList<>();
//                    edtAddr.setText(alrCity.get(pos));
//
//                    mDbHelper = new DB(OrderActivity.this).open();
//                    mNotesCursor = mDbHelper.getData(
//                            "select distinct road from " + RSSNewsItem.TBL_TW5CODE
//                                    + " where city= '" +alrCity.get(pos)+ "' order by road;");
//
//                    if (mNotesCursor!=null)
//                        mNotesCursor.moveToFirst();
//                    while (!mNotesCursor.isAfterLast()){
//                        if (mNotesCursor.getString(0).trim().length()>2)
//                            alrRoadDown.add(mNotesCursor.getString(0));
//                        mNotesCursor.moveToNext();
//                    }
//                    mNotesCursor.close();
//                    mDbHelper.close();
//
//                    //重新產生新的Adapter，用的是二維陣列type2[pos]
//                    adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,alrRoadDown);
//                    //載入第二個下拉選單Spinner
//                    sp2.setAdapter(adapter2);
//                    break;
//                case R.id.spinnerRoad:
////                    Log.d("col2==","spinnerRoad" );
////                    Log.d("col2==",sp2.getSelectedItem()+"");
//                    break;
//            }
//            edtAddr.setText(""+sp.getSelectedItem() + sp2.getSelectedItem() );
//
//        }
//
//        public void onNothingSelected(AdapterView<?> arg0){
//
//        }
//
//    };


    private void findViews(){
        edtName=(EditText)findViewById(R.id.edtName);
        edtAddr=(EditText)findViewById(R.id.edtAddr);
        edtTel=(EditText)findViewById(R.id.edtTel);

    }

    private void setlisenter() {
//        sp.setOnItemSelectedListener(selectListener);
//        sp2.setOnItemSelectedListener(selectListener);
    }
    //    FirebaseDatabase database;
//    DatabaseReference myRef;
    boolean flagFirst ;
    String strBuffer,value = "";

    public void sentOrder(View target){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("customer");
        Log.d("fire 0",myRef.getKey());//customer
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                value = dataSnapshot.getValue(String.class);
//                Log.d("firebase", "Value is: " + value);

//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    User user = ds.getValue(User.class);
//                    Log.d("FireBaseTraining", "name = " + user.getName() + " , Age = " + user.getAge());
//                }
//                strBuffer = value;
//                flagFirst=true;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });


//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("customer");
//        strBuffer = strBuffer + et.getText().toString();
        strBuffer = strBuffer + "訂單編號3";
        myRef.setValue(strBuffer);
//        et.setTex?…t("");

    }




}

