package com.smile.smileview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Objects;

/**
 * Created by qq272 on 2016/3/9.
 */
public class MyViewAdapter extends BaseAdapter {
    private List<ItemBean> itemBeans;
    private Context context;
    private LayoutInflater inflater;

    public MyViewAdapter( List<ItemBean> itemBeans, Context context) {
        this.itemBeans=itemBeans;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return itemBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHold hold=null;
        if(convertView==null){
            hold=new ViewHold();
            convertView=inflater.inflate(R.layout.item,null);
            hold.title= (TextView) convertView.findViewById(R.id.id_text_tv);
            hold.myView= (MyView) convertView.findViewById(R.id.id_myview);
            hold.delete= (Button) convertView.findViewById(R.id.id_delete);
            convertView.setTag(hold);
        }else
             {
            hold= (ViewHold) convertView.getTag();
        }
            hold.title.setText(itemBeans.get(position).getTitle());
            //自定义view的点击事件
            hold.myView.change_Status(itemBeans.get(position).getStatus());
        final ViewHold finalHold = hold;
        final ViewHold finalHold1 = hold;
        final ViewHold finalHold2 = hold;
        //自定义view的点击事件
        hold.myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalHold1.myView.get_Status()==true){
                        finalHold1.myView.change_Status(false);
                    }else if (finalHold1.myView.get_Status()==false){
                        finalHold1.myView.change_Status(true);
                        finalHold1.myView.invalidate();
                    }
                    //Log.i("smile","我执行了");
                }
            });
            hold.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //移除数据 刷新数据
                    itemBeans.remove(position);
                    notifyDataSetChanged();

                }
            });

        return convertView;
    }
    class ViewHold{
        private TextView title;
        private MyView myView;
        private Button delete;
    }

}
