package com.test.mainli;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mainli.adapterlib.recyclerView.RViewHolder;
import com.mainli.adapterlib.recyclerView.TitleGroupRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Mainli on 2016/4/13.
 */
public class TestActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        List<String> list1 = new ArrayList<String>();
        for (int i = 0; i < 8; i++) {
            list1.add("组1:" + i);
        }
        List<String> list2 = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            list2.add("组2:" + i);
        }
        List<String> list3 = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            list3.add("组3:" + i);
        }
        Map<String, List<String>> map = new TreeMap<String, List<String>>();
        map.put("组1", list1);
        map.put("组2", list2);
        map.put("组3", list3);
        mRecycleView = (RecyclerView) findViewById(R.id.recycleView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //--------------1.普通的适配器--------------------------------------------------
//        mRecycleView.setAdapter(new RecyclerAdapter<String>(list1, R.layout.item) {
//
//            @Override
//            public void onBindObject2View(RViewHolder vh, String s, int position) {
//                vh.setText(R.id.text, s);
//            }
//        });
        //--------------3.带title的适配器--------------------------------------------------
        mRecycleView.setAdapter(new TitleGroupRecyclerAdapter<String, String>(map) {
            @Override
            public void onBindObject2View(RViewHolder vh, String s, String s2, int position) {
                if (vh.getItemViewType() == ITEM_LAYOUT_TYOE) {
                    vh.setText(R.id.text, s2);
                } else {
                    vh.setText(R.id.text, s);
                }
            }

            @Override
            public int getLayoutResources(int viewType) {
                if (viewType == this.ITEM_LAYOUT_TYOE)
                    return R.layout.item;
                else return R.layout.item1;
            }
        });
    }
}
