package com.example.teacher.t2017101102;

        import android.database.Cursor;
//        import android.support.v4.widget.SimpleCursorAdapter;
        import android.support.v4.widget.SimpleCursorAdapter;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.Toast;

public class ManagementClass extends AppCompatActivity implements ListView.OnItemClickListener {

    ListView listView;
    private DB mDbHelper;
    private Cursor mNotesCursor;
    ImageButton imgDelete,imgEdit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        listView = (ListView) findViewById(R.id.listView);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setOnItemClickListener(this);
        mDbHelper = new DB(this).open();
        setAdapter();

        imgEdit=(ImageButton)findViewById(R.id.imageBtnedit);
        imgDelete=(ImageButton)findViewById(R.id.imageBtndel);
//        imgDelete.setOnClickListener(listener);
    }
//
//    private View.OnClickListener listener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
////            int id = mNotesCursor.getInt(mNotesCursor.getColumnIndex(adapter.id));
//        }
//    };

//    public void editClass(View target){
//        Toast.makeText(this,listView.getChildAt(2).toString(),Toast.LENGTH_SHORT).show();
////        Log.d("===",listView.getChildAt(2).toString());
//    }
//
//    public void delClass(View target){
//        Log.d("===","del");
//    }



    SimpleCursorAdapter adapter;
    private void setAdapter() {
        mNotesCursor = mDbHelper.getAll();
        if (mNotesCursor != null)
            mNotesCursor.moveToFirst();
        startManagingCursor(mNotesCursor);
        String[] from = new String[]{"showorder", "classname","created"};
        int[] to = new int[]{R.id.text1, R.id.text2, R.id.text3};
//        adapter = new SimpleCursorAdapter(this,
//                R.layout.simple_list_item_1, mNotesCursor, from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        MySimpleCursorAdapter adapter = new MySimpleCursorAdapter(this,
                R.layout.simple_list_item_1, mNotesCursor, from, to, MySimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "新增").setIcon(android.R.drawable.ic_menu_add)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        menu.add(0, 2, 0, "刪除").setIcon(android.R.drawable.ic_menu_delete)
//                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        menu.add(0, 3, 0, "修改").setIcon(android.R.drawable.ic_menu_edit)
//                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    int count;
    long rowId;
    AlertDialog dialog; //讓自定Layout可有關閉功能
    View root;
    EditText etClass,etOrder;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("rowId=",rowId+"");
        switch (item.getItemId()) {
            case 1:
                root = getLayoutInflater().inflate(R.layout.dialog_layout, null);//找出根源樹,
                etOrder = (EditText) root.findViewById(R.id.et_order);  //若不使用root,則它會去找主畫面的layout的元件
                etClass = (EditText) root.findViewById(R.id.et_class);
                Button confirm1 = (Button) root.findViewById(R.id.btn_confirm);
                Button cancel1 = (Button) root.findViewById(R.id.btn_cancel);
                confirm1.setOnClickListener(dialoglistener);
                cancel1.setOnClickListener(dialoglistener);
                openOptionsDialog();

                break;

            case 2:
                if (rowId<=0) {
                    Toast.makeText(this, "請先點選欲刪修的項目" + rowId + "", Toast.LENGTH_SHORT).show();

                }else {
                    mDbHelper.delete(rowId);
                    setAdapter();
//                    rowId=-1;
                }
                break;
            case 3://修改
                if (rowId<=0) {
                    Toast.makeText(this, "請先點選欲刪修的項目" + rowId + "", Toast.LENGTH_SHORT).show();

                }else {
                    root = getLayoutInflater().inflate(R.layout.dialog_layout, null);//找出根源樹,
                    etClass = (EditText) root.findViewById(R.id.et_class);  //若不使用root,則它會去找主畫面的layout的元件
                    Button confirm = (Button) root.findViewById(R.id.btn_confirm);
                    Button cancel = (Button) root.findViewById(R.id.btn_cancel);
                    confirm.setOnClickListener(dialoglistener);
                    cancel.setOnClickListener(dialoglistener);
                    openOptionsDialog();
//                    rowId=-1;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        rowId = id;
        Log.d(" rowId=" , rowId+"");
        Toast.makeText(this,"rowId="+rowId,Toast.LENGTH_SHORT).show();

    }


    //
//                mDbHelper.create("主類別 " + count, count);
//                setAdapter();
    View.OnClickListener dialoglistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Log.d("v.getContext()",((Button)v).getText().toString());
            switch (v.getId()){
                case R.id.btn_confirm:
//                    mDbHelper.update(rowId,et.getText().toString());
                    mDbHelper.create(Integer.parseInt(etOrder.getText().toString()),etClass.getText().toString());
                    setAdapter();
                    dialog.dismiss();
                    break;
                case R.id.btn_cancel:
                    dialog.dismiss();
                    break;
            }

        }


    };

    void openOptionsDialog() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setView(root);
        dialog = ab.show();
    }



}



