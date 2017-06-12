package com.example.leechaelin.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Recording extends AppCompatActivity {
    LinearLayout l1,l2;
    TextView t;
    RadioButton radio1,radio2,radio3;
    Button btnsave,btncancel;
    ListView listview;
    EditText e;
    DatePicker dp;
    Intent intent = new Intent();
    int count=0;
    String path = getExternalPath();
    String filename = path+"diary/";
    int index=0;
    ArrayList<String> filteredinput = new ArrayList<String>();
    ArrayAdapter<titlename> adapter;
    ArrayList<titlename> name = new ArrayList<titlename>();
    String input= "";
    boolean modify=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        filteredinput= getIntent().getStringArrayListExtra("filteredinput");
        Checkpermission();
        e=(EditText)findViewById(R.id.edittext);
        radio1 =(RadioButton)findViewById(R.id.r1);
        radio2 = (RadioButton)findViewById(R.id.r2);
        radio3 = (RadioButton)findViewById(R.id.r3);
        t=(TextView)findViewById(R.id.tvCount);
        l1=(LinearLayout)findViewById(R.id.linear1);
        l2=(LinearLayout)findViewById(R.id.linear2);
        btnsave=(Button)findViewById(R.id.btnsave);
        btncancel=(Button)findViewById(R.id.btncancel);
        dp = (DatePicker)findViewById(R.id.DatePicker);
        listview =(ListView)findViewById(R.id.listview);
        adapter=  new ArrayAdapter<titlename>(this,android.R.layout.simple_list_item_1,name);
        listview.setAdapter(adapter);
        for(int i=0;i<filteredinput.size();i++){
            Toast.makeText(getApplicationContext(),filteredinput.get(i),Toast.LENGTH_SHORT).show();
        }


        File file = new File(path+"diary/");
        //file.delete();

        if(file.isDirectory()==false)
            file.mkdir();

        File[] files = new File(path + "diary/").listFiles();
        for(File f:files)
            //f.delete();
            name.add(new titlename(f.getName()));

        adapter.notifyDataSetChanged();
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dlg= new AlertDialog.Builder(Recording.this);
                dlg.setMessage("정말로 삭제하시겠습니까 ")
                        .setNegativeButton("취소",null)
                        .setPositiveButton("확인 ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                File files = new File(path + "diary/"+name.get(position).getTitlename());
                                Log.d("file",name.get(position).getTitlename());
                                if (!files.exists()) {
                                    Log.d("DEBUG", "파일 없음)");
                                }
                                boolean remove = files.delete();
                                if(remove){
                                    Toast.makeText(getApplicationContext(),"삭제성공",Toast.LENGTH_LONG).show();

                                }else{
                                    Toast.makeText(getApplicationContext(),"삭제실패 ",Toast.LENGTH_SHORT).show();
                                }
                                File[] file = new File(path+"diary/").listFiles();
                                String str = "";
                                for(File f:file)
                                    str += f.getName() + "\n" ;
                                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
                                name.remove(position);
                                adapter.notifyDataSetChanged();
                                t.setText("등록된 메모의 갯수"+name.size());
                                Toast.makeText(getApplicationContext(),"삭제하였습니다. ",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                return true;
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    l2.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.INVISIBLE);
                    btnsave.setText("수정");

                    //기존의 것들을 보여준다
                    BufferedReader br = new BufferedReader(new FileReader(path+"diary/"+name.get(position).getTitlename()));
                    String readStr="";
                    String str = null;

                    while((str=br.readLine())!= null)
                        readStr += str + "\n";
                    e.setText(readStr.substring(0,readStr.length()-1));

                    String year = name.get(position).toString().substring(0,4);
                    String month = name.get(position).toString().substring(5,7);
                    String date = name.get(position).toString().substring(8,10);
                    dp.updateDate(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(date));
                    modify = true;

                    br.close();
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }

            }

        });

    }


    public void onClick(View v){
        if(v.getId()==R.id.btn1){
            l1.setVisibility(View.INVISIBLE);
            l2.setVisibility(View.VISIBLE);
            listview.setVisibility(View.INVISIBLE);
            for(int i=0;i<filteredinput.size();i++){
                input += filteredinput.get(i)+",";
            }
            e.setText(input);
            input ="";
            btnsave.setText("완료");

        }else if(v.getId()==R.id.btnsave){
            String year = String.valueOf(dp.getYear());
            String month = String.valueOf(dp.getMonth()+1);
            String date = String.valueOf(dp.getDayOfMonth());
            if(month.length()==1){
                month = "0"+(dp.getMonth()+1);
            }
            if(date.length()==1){
                date = "0"+dp.getDayOfMonth();
            }
            String oldfilenames = year+"-"+month+"-"+date;

            if(radio1.isChecked()){
                oldfilenames+="아침";
            }else if(radio2.isChecked()){
                oldfilenames+="점심";
            }else if(radio3.isChecked()){
                oldfilenames+="저녁 ";
            }
            if(modify==true){
                btnsave.setText("수정");
                File files = new File(path + "diary/"+name.get(index).getTitlename());
                files.delete();
                name.remove(index);
                adapter.notifyDataSetChanged();
                try{
                    BufferedWriter bw =new BufferedWriter(new FileWriter(filename+oldfilenames,false));
                    bw.write(e.getText().toString());
                    bw.close();
                    Toast.makeText(getApplicationContext(),"파일 저장 ",Toast.LENGTH_SHORT).show();

                }catch(IOException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage()+":"+getFilesDir(),Toast.LENGTH_SHORT).show();
                }
                name.add(new titlename(oldfilenames.toString()));
                adapter.notifyDataSetChanged();
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.INVISIBLE);
                listview.setVisibility(View.VISIBLE);
                t.setText("등록된 메모 개수:"+name.size());

            }

            for(int i=0;i<name.size();i++){
                if(name.get(i).toString().equals(oldfilenames)){
                    //수정모드로 변경
                    modify = true;
                    try{
                        BufferedReader br = new BufferedReader(new FileReader(path+"diary/"+name.get(i).getTitlename()));
                        //Log.d("modify",Boolean.toString(modify));
                        index = i;
                        String readStr = "";
                        String str =null;
                        while((str=br.readLine())!=null)
                            readStr+=str+"\n";
                        e.setText(readStr);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
            if(modify==false){
                try{
                    BufferedWriter bw =new BufferedWriter(new FileWriter(filename+oldfilenames,false));
//                    for(int i=0;i<filteredinput.size();i++){
//                        input +=filteredinput.get(i);
//                    }
//                    e.setText(input);
                    bw.write(e.getText().toString());
                    bw.close();
//                    input = "";
                    Toast.makeText(getApplicationContext(),"파일 저장 ",Toast.LENGTH_SHORT).show();
                    btnsave.setText("저장");

                }catch(IOException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage()+":"+getFilesDir(),Toast.LENGTH_SHORT).show();
                }
                name.add(new titlename(oldfilenames.toString()));
                adapter.notifyDataSetChanged();
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.INVISIBLE);
                listview.setVisibility(View.VISIBLE);
                t.setText("등록된 메모 개수:"+name.size());

            }

        }


        else if(v.getId()==R.id.btncancel){
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.INVISIBLE);
            listview.setVisibility(View.VISIBLE);
        }
        else if(v.getId()==R.id.btn2){
            setResult(RESULT_OK,intent);
            finish();
        }

    }




    public String getExternalPath(){
        String sdPath="";
        String ext = Environment.getExternalStorageState();
        if(ext.equals(Environment.MEDIA_MOUNTED)){
            sdPath=Environment.getExternalStorageDirectory().getAbsolutePath()+ "/";
        }else
            sdPath=getFilesDir()+"";
        return sdPath;
    }


    public void Checkpermission(){
        int permissioninfo = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissioninfo== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(),"SDCard 쓰기 권한 있음 ",Toast.LENGTH_SHORT).show();
        }else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(getApplicationContext(),"권한의 필요성 설명 ",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            }else{
                ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String str = null;
        if(requestCode==100){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                str = "SD Card 쓰기 권한 승인 ";
            else
                str ="SD Card 쓰기 권한 거부";
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
