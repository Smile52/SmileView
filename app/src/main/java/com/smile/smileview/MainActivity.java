package com.smile.smileview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyView myView;
    private Button change_btn;
    private ListView mListView;
    private List<ItemBean> beanList=new ArrayList<ItemBean>();
    MyViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intdata();
        mListView= (ListView) findViewById(R.id.id_listview);
       adapter =new MyViewAdapter(beanList,getApplicationContext());
        mListView.setAdapter(adapter);

    }

    private void intdata() {
        for(int i=0;i<15;i++){
            ItemBean bean=new ItemBean();
            bean.setTitle("德玛西亚"+i);
            bean.setStatus(true);
            beanList.add(bean);
        }
    }



}
