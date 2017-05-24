package com.example.leechaelin.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class adding extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    RadioButton r1,r2,r3,r4;
    Nutrient_Data d1;
    String cate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        init();
    }

    void init(){
        e1 = (EditText)findViewById(R.id.etname);
        e2 = (EditText)findViewById(R.id.et1);
        e3 = (EditText)findViewById(R.id.et2);
        e4 = (EditText)findViewById(R.id.et3);
        r1 = (RadioButton)findViewById(R.id.radio1);
        r2 = (RadioButton)findViewById(R.id.radio1);
        r3 = (RadioButton)findViewById(R.id.radio1);
        r4 = (RadioButton)findViewById(R.id.radio1);
    }

    public void onClick(View v){
        if(v.getId()==R.id.btnAdd){
            String name = e1.getText().toString();
            String nu1 = e2.getText().toString();
            String nu2 = e3.getText().toString();
            String nu3 = e4.getText().toString();

            if(r1.isChecked())
                cate ="1";
            else if(r2.isChecked())
                cate = "2";
            else if(r3.isChecked())
                cate = "4";

            d1 = new Nutrient_Data(name,nu1,nu2,nu3);

            Intent intent = new Intent();
            intent.putExtra("Nutrient_Date",d1);
            setResult(RESULT_OK,intent);

            Toast.makeText(getApplicationContext(),"등록이 되었습니다.",Toast.LENGTH_SHORT);
        }
    }
}
