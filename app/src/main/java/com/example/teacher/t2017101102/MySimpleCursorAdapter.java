package com.example.teacher.t2017101102;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kevin on 2017/11/19.
 */

public class MySimpleCursorAdapter extends SimpleCursorAdapter {
    Context context;
    Cursor c;


    public MySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context=context;
        this.c=c;
    }

    static class ViewHolder {
        public ImageButton btndel;
        public ImageButton btnedit;
        public TextView text1,text2,text3;
//        public ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int a=position;
        View v = convertView;
        ViewHolder viewHolder;

        if(v == null ){
//            LayoutInflater inflater = context.getLayoutInflater();
            LayoutInflater inflater = LayoutInflater.from(context);
//            v = inflater.inflate( R.layout.add_program_ex_list_item, parent, false);
            v = inflater.inflate(R.layout.simple_list_item_1, null);
             viewHolder = new ViewHolder();
//            viewHolder.btn = (ImageView) v.findViewById(R.id.ImageView01);
            viewHolder.btndel = (ImageButton) v.findViewById(R.id.imageBtndel);
            viewHolder.btnedit = (ImageButton) v.findViewById(R.id.imageBtnedit);

            viewHolder.text1=(TextView)v.findViewById(R.id.text1);
            viewHolder.text2=(TextView)v.findViewById(R.id.text2);
            viewHolder.text3=(TextView)v.findViewById(R.id.text3);

            viewHolder.text1.setText(c.getString(0));
            viewHolder.text2.setText(c.getString(1));
            viewHolder.text3.setText(c.getString(2));
            v.setTag(viewHolder);
        }else{
            viewHolder =(ViewHolder) v.getTag();
        }
//        ViewHolder holder = (ViewHolder) v.getTag();
        // here you can get viewholder items
        // eg : holder.btn.setText("button");
//        holder.text1.setText();


        viewHolder.btndel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("btndel", "It works, pos=" + a);
            }
        });

        viewHolder.btnedit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("btnedit", "It works, pos=" + a);
            }
        });


        return v;
    }

}
