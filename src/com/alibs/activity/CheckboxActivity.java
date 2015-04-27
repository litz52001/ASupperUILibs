package com.alibs.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.alibs.R;
import com.alibs.Adapter.CheckBoxAdapter;
import com.alibs.Adapter.CheckBoxAdapter.ViewHolder;

/**
 * checkbox and ListView 
 * @author Administrator
 */
public class CheckboxActivity extends Activity {  
    private ListView lv;  
    private CheckBoxAdapter mAdapter;  
    private ArrayList<String> list;  
    private Button bt_selectall;  
    private Button bt_cancel;  
    private Button bt_deselectall;  
    private int checkNum; // 记录选中的条目数量  
    private TextView tv_show;// 用于显示选中的条目数量  
  
    /** Called when the activity is first created. */  
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.select_checkbox);  
        /* 实例化各个控件 */  
        lv = (ListView) findViewById(R.id.lv);  
        bt_selectall = (Button) findViewById(R.id.bt_selectall);  
        bt_cancel = (Button) findViewById(R.id.bt_cancleselectall);  
        bt_deselectall = (Button) findViewById(R.id.bt_deselectall);  
        tv_show = (TextView) findViewById(R.id.tv);  
        list = new ArrayList<String>();  
        initDate();  
        mAdapter = new CheckBoxAdapter(list, this);  
        lv.setAdapter(mAdapter);  
  
        // 全选按钮的回调接口  
        bt_selectall.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                // 遍历list的长度，将MyAdapter中的map值全部设为true  
                for (int i = 0; i < list.size(); i++) {  
                    CheckBoxAdapter.getIsSelected().put(i, true);  
                }  
                checkNum = list.size();  
                dataChanged();  
            }  
        });  
  
        // 反选按钮的回调接口  
        bt_cancel.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                // 遍历list的长度，将已选的设为未选，未选的设为已选  
                for (int i = 0; i < list.size(); i++) {  
                    if (CheckBoxAdapter.getIsSelected().get(i)) {  
                        CheckBoxAdapter.getIsSelected().put(i, false);  
                        checkNum--;  
                    } else {  
                        CheckBoxAdapter.getIsSelected().put(i, true);  
                        checkNum++;  
                    }  
                }  
                dataChanged();  
            }  
        });  
  
        // 取消按钮的回调接口  
        bt_deselectall.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                // 遍历list的长度，将已选的按钮设为未选  
                for (int i = 0; i < list.size(); i++) {  
                    if (CheckBoxAdapter.getIsSelected().get(i)) {  
                        CheckBoxAdapter.getIsSelected().put(i, false);  
                        checkNum--;// 数量减1  
                    }  
                }  
                dataChanged();  
            }  
        });  
  
        // 绑定listView的监听器  
        lv.setOnItemClickListener(new OnItemClickListener() {  
            @Override  
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
                    long arg3) {  
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤  
                ViewHolder holder = (ViewHolder) arg1.getTag();  
                // 改变CheckBox的状态  
                holder.cb.toggle();  
                // 将CheckBox的选中状况记录下来  
                CheckBoxAdapter.getIsSelected().put(arg2, holder.cb.isChecked());  
                // 调整选定条目  
                if (holder.cb.isChecked() == true) {  
                    checkNum++;  
                } else {  
                    checkNum--;  
                }  
                // 用TextView显示  
                tv_show.setText("已选中" + checkNum + "项");  
            }  
        });  
    }  
  
    private void initDate() {  
        for (int i = 0; i < 15; i++) {  
            list.add("data" + " " + i);  
        }  
        ;  
    }  
    
    // 刷新listview和TextView的显示  
    private void dataChanged() {  
        mAdapter.notifyDataSetChanged();  
        tv_show.setText("已选中" + checkNum + "项");  
    };  
}  