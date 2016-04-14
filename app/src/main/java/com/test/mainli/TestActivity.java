package com.test.mainli;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mainli.adapterlib.adapter.RecyclerAdapter;
import com.mainli.adapterlib.viewholder.RViewHolder;

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
        mRecycleView.setAdapter(new RecyclerAdapter<String>(list1) {
            @Override
            public int getLayoutRessources(int viewType) {
                return R.layout.item;
            }

            @Override
            public void onBindObject2View(RViewHolder vh, String s, int position) {
                vh.setText(R.id.text, s);
            }
        });
        //--------------2.带title的适配器--------------------------------------------------
//        mRecycleView.setAdapter(new MultiRecyclerAdapter<String>(list2) {
//
//            @Override
//            public int getItemViewType(String s, int position) {
//                return Integer.valueOf(s.substring(s.length() - 1, s.length())) % 2 == 0 ? 0 : 1;
//            }
//
//            @Override
//            public int getLayoutRessources(int viewType) {
//                return viewType == 0 ? R.layout.item : R.layout.item1;
//            }
//
//            @Override
//            public void onBindObject2View(RViewHolder vh, String s, int position) {
//                vh.setText(vh.getViewTypeflag() == 0 ? R.id.text : R.id.text1, s);
//            }
//        });
        //--------------3.带title的适配器--------------------------------------------------
//        mRecycleView.setAdapter(new TitleGroupRecyclerAdapter<String, String>(map) {
//            @Override
//            public void onBindObject2View(RViewHolder vh, String s, String s2, int position) {
//                if (vh.getViewTypeflag() == ITEM_LAYOUT_TYOE) {
//                    vh.setText(R.id.text, s2);
//                } else {
//                    vh.setText(R.id.text1, s);
//                }
//            }
//
//            @Override
//            public int getLayoutRessources(int viewType) {
//                if (viewType == this.ITEM_LAYOUT_TYOE)
//                    return R.layout.item;
//                else return R.layout.item1;
//            }
//        });
    }
}
