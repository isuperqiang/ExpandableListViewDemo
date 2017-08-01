package com.richie.expandable.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import com.richie.expandable.Constant;
import com.richie.expandable.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用 SimpleExpandableListAdapter 实现适配器
 */
public class SimpleExpandActivity extends AppCompatActivity {
    private static final String TAG = "SimpleExpandActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandable_list);

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, initGroupData(),
                R.layout.item_expand_group_normal, new String[]{Constant.BOOK_NAME}, new int[]{R.id.label_group_normal},
                initChildData(), R.layout.item_expand_child, new String[]{Constant.FIGURE_NAME}, new int[]{R.id.label_expand_child});
        listView.setAdapter(adapter);

        //  设置分组项的点击监听事件
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d(TAG, "onGroupClick: groupPosition:" + groupPosition + ", id:" + id);
                // 请务必返回 false，否则分组不会展开
                return false;
            }
        });

        //  设置子选项点击监听事件
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(SimpleExpandActivity.this, Constant.FIGURES[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    // 构建分组项显示的数据
    private List<Map<String, String>> initGroupData() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map;
        for (int i = 0; i < Constant.BOOKS.length; i++) {
            map = new HashMap<>();
            map.put(Constant.BOOK_NAME, Constant.BOOKS[i]);
            list.add(map);
        }
        return list;
    }

    // 构建子选项显示的数据
    private List<List<Map<String, String>>> initChildData() {
        List<List<Map<String, String>>> list = new ArrayList<>();
        List<Map<String, String>> child;
        Map<String, String> map;
        int row = Constant.FIGURES.length;
        int column = Constant.FIGURES[0].length;
        for (int i = 0; i < row; i++) {
            child = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                map = new HashMap<>();
                map.put(Constant.FIGURE_NAME, Constant.FIGURES[i][j]);
                child.add(map);
            }
            list.add(child);
        }
        return list;
    }

}
