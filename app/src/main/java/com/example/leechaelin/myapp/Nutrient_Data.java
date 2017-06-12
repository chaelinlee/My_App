package com.example.leechaelin.myapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leechaelin on 2017. 5. 24..
 */

public class Nutrient_Data implements Parcelable{
    private String name="",nu1="",nu2="",cate;
    int isCheck = 0;

    public Nutrient_Data(String name,String nu1,String nu2,String cate){
        this.name = name;
        this.nu1 = nu1;
        this.nu2 = nu2;

        this.cate = cate;

    }

    protected Nutrient_Data(Parcel in) {
        this.name = in.readString();
        this.nu1 = in.readString();
        this.nu2 = in.readString();
        this.cate = in.readString();
    }

    public static final Creator<Nutrient_Data> CREATOR = new Creator<Nutrient_Data>() {
        @Override
        public Nutrient_Data createFromParcel(Parcel in) {
            return new Nutrient_Data(in);
        }

        @Override
        public Nutrient_Data[] newArray(int size) {
            return new Nutrient_Data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(nu1);
        dest.writeString(nu2);
        dest.writeString(cate);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNu1() {
        return nu1;
    }

    public void setNu1(String nu1) {
        this.nu1 = nu1;
    }

    public String getNu2() {
        return nu2;
    }

    public void setNu2(String nu2) {
        this.nu2 = nu2;
    }

    public String getCate(){
        return cate;
    }

    public void setCate(String cate){
        this.cate= cate;
    }

    public int getIsCheck(){
        return isCheck;
    }

    public void setIsCheck(int isCheck){
        this.isCheck=isCheck;
    }
}
