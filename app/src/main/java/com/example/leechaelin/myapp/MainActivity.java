package com.example.leechaelin.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview1;
    Button b1,b2,b3,b4;
    ArrayList<Nutrient_Data> input = new ArrayList<Nutrient_Data>();
    ArrayList<String> filteredinput = new ArrayList<String>();

    Nutriadapter adapter;
    Boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview1 = (ListView)findViewById(R.id.listview);
        b1 = (Button)findViewById(R.id.add);
        b2 = (Button)findViewById(R.id.select);
        b3 = (Button)findViewById(R.id.check);
        b4 = (Button)findViewById(R.id.networking);
        adapter = new Nutriadapter(this,input,filteredinput,false);
        listview1.setAdapter(adapter);
        btn_funtion();


    }

    public void btn_funtion(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,adding.class);
                //intent.putExtra("fjeiwof",filteredinput);
                startActivityForResult(intent,5);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(check){
                check = false;
                adapter.setCheck(false);
                adapter.notifyDataSetChanged();
            }else{
                check= true;
                adapter.setCheck(true);
                adapter.notifyDataSetChanged();

                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record();
                Intent intent2 = new Intent(MainActivity.this,Recording.class);
                intent2.putExtra("filteredinput",filteredinput);
                startActivityForResult(intent2 , 10);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Network.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==5){
            if (resultCode == RESULT_OK) {
                Nutrient_Data d1 = data.getParcelableExtra("Nutrient_Date");
                input.add(d1);
                adapter.notifyDataSetChanged();
            }
        }else if(requestCode==10){
            if(resultCode==RESULT_OK){
                for(int i=0;i<filteredinput.size();i++){
                    filteredinput = new ArrayList<String>();
                }

                for(int i=0;i<filteredinput.size();i++){
                    Log.d("filteredinput", filteredinput.get(i));
                }
                for(int i=0;i<input.size();i++){
                    input.get(i).setIsCheck(0);
                }
            }

        }
    }
    public void record(){
        for(int i=0;i<input.size();i++){
            if(input.get(i).getIsCheck() == 1){
                // 체크가 되어있으면
                filteredinput.add(input.get(i).getNu1());
                filteredinput.add(input.get(i).getNu2());

            }else{
                //체크가 안되어있다면
                filteredinput.remove(input.get(i).getNu1());
                filteredinput.remove(input.get(i).getNu2());
            }
        }
    }


}
