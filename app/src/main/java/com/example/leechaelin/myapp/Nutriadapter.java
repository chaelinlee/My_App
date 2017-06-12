package com.example.leechaelin.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by leechaelin on 2017. 5. 24..
 */

public class Nutriadapter extends BaseAdapter{
    ArrayList<Nutrient_Data> input;
    ArrayList<String> filteredinput;
    Context c;
    Boolean check = false;
    public Nutriadapter(Context c,ArrayList<Nutrient_Data>input,ArrayList<String> filteredinput,boolean check){
        this.c = c;
        this.input=input;
        this.filteredinput=filteredinput;
        this.check = check;
    }
    @Override
    public int getCount() {
        return input.size();
    }

    @Override
    public Object getItem(int position) {
        return input.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(c);
        if(convertView==null)
            convertView=inflater.inflate(R.layout.activity_things_on_list,null);
        ImageView i = (ImageView)convertView.findViewById(R.id.nut_image);
        TextView t1 =  (TextView)convertView.findViewById(R.id.nut_name);
        TextView t2 =  (TextView)convertView.findViewById(R.id.nut_nur1);
        TextView t3 =  (TextView)convertView.findViewById(R.id.nut_nur2);
        CheckBox c1 = (CheckBox)convertView.findViewById(R.id.c1);

        t1.setText(input.get(position).getName());
        t2.setText(input.get(position).getNu1());
        t3.setText(input.get(position).getNu2());

        if(input.get(position).getCate().equals("1")){
            i.setImageResource(R.drawable.fruit);
        }else if(input.get(position).getCate().equals("2")){
            i.setImageResource(R.drawable.grain);
        }else if(input.get(position).getCate().equals("3")){
            i.setImageResource(R.drawable.dairy);
        }else if(input.get(position).getCate().equals("4")){
            i.setImageResource(R.drawable.meat);
        }

        if(check){
            c1.setVisibility(View.VISIBLE);
            c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        input.get(position).setIsCheck(1);

                    }
                    else {
                        input.get(position).setIsCheck(0);

                    }
                }
            });
        }else{
            c1.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }
    public void setCheck(boolean check){
        this.check=check;
        this.notifyDataSetChanged();
    }


}
