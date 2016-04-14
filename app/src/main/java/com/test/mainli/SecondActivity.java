package com.test.mainli;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mainli.adapterlib.adapter.AbstractBaseAdapter;
import com.mainli.adapterlib.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mainli on 2016/4/14.
 */
public class SecondActivity extends AppCompatActivity {
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mListView = (ListView) findViewById(R.id.listview);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            list.add("test:" + i);
        }
        mListView.setAdapter(new AbstractBaseAdapter<String>(list) {
            @Override
            public int getItemResource() {
                return R.layout.item;
            }

            @Override
            public View getItemView(int position, View convertView, String s) {
                TextView textView = ViewHolder.get(convertView, R.id.text, 1);
                textView.setText(s);
                return convertView;
            }
        });
    }
}
