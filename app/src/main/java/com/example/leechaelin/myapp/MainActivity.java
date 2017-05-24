package com.example.leechaelin.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview1,listview2;
    Button b1,b2,b3;
    ArrayList<Nutrient_Data> input = new ArrayList<Nutrient_Data>();
    Nutriadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview1 = (ListView)findViewById(R.id.listview);
        b1 = (Button)findViewById(R.id.add);
        b2 = (Button)findViewById(R.id.select);
        b3 = (Button)findViewById(R.id.check);
        adapter = new Nutriadapter(this,input,false);

    }

    public void btn_funtion(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,adding.class);
                startActivityForResult(intent,5);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==5){
            if (resultCode == RESULT_OK) {
                Nutrient_Data d1 = data.getParcelableExtra("Nutrient_Date");
                adapter.notifyDataSetChanged();
            }
        }
    }
}
