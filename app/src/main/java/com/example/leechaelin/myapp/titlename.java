package com.example.leechaelin.myapp;

/**
 * Created by leechaelin on 2017. 6. 10..
 */

public class titlename {
    String title;


    public titlename(String title){
        this.title=title;


    }
    public String getTitlename(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
