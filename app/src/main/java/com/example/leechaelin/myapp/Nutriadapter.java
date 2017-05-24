package com.example.leechaelin.myapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by leechaelin on 2017. 5. 24..
 */

public class Nutriadapter extends BaseAdapter{
    ArrayList<Nutrient_Data> input;
    Context c;
    Boolean delete = false;
    public Nutriadapter(Context c,ArrayList<Nutrient_Data>input,boolean delete){
        this.c = c;
        this.input=input;
        this.delete = delete;
    }
    @Override
    public int getCount() {
        return 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
