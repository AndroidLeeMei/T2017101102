package com.example.teacher.t2017101102;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yvtc on 2017/11/9.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    String str[];
    public boolean b[];
    public MyAdapter(Context context, String[] str)
    {
        this.context = context;
        this.str = str;
        b = new boolean[str.length];
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("GETVIEW", "position:" + position);
        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.simple_list_item_1, null);
            holder = new ViewHolder();
//            holder.tv = (TextView) convertView.findViewById(R.id.textView);
//            holder.btn = (Button) convertView.findViewById(R.id.button);
//            holder.chk = (CheckBox) convertView.findViewById(R.id.checkBox) ;
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imageBtnedit);
            holder.imgDel = (ImageView) convertView.findViewById(R.id.imageBtndel);
//            holder.chk = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
            final String msg = str[position];
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });


//            holder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                b[position] = isChecked;
//            }
//        });
//        holder.chk.setChecked(b[position]);
//        holder.tv.setText(str[position]);


        return convertView;
    }
    static class ViewHolder
    {
//        TextView tv;
//        Button btn;
//        CheckBox chk;
        ImageView imgEdit;
        ImageView imgDel;
    }
}
