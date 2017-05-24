package com.example.leechaelin.myapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leechaelin on 2017. 5. 24..
 */

public class Nutrient_Data implements Parcelable{
    private String name="",nu1="",nu2="",nu3="";

    public Nutrient_Data(String name,String nu1,String n2,String nu3){
        this.name = name;
        this.nu1 = nu1;
        this.nu2 = nu2;
        this.nu3 = nu3;

    }

    protected Nutrient_Data(Parcel in) {
        name = in.readString();
        nu1 = in.readString();
        nu2 = in.readString();
        nu3 = in.readString();
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
        dest.writeString(nu3);
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

    public String getNu3() {
        return nu3;
    }

    public void setNu3(String nu3) {
        this.nu3 = nu3;
    }

}
