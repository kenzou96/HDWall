package com.vn.edu.poly.myapplication.model;



public class category {

    private String mImageResource;
    private String mText;


    public category(String mImageResource, String mText) {
        this.mImageResource = mImageResource;
        this.mText = mText;
    }

    public String getmImageResource() {
        return mImageResource;
    }

    public String getmText() {
        return mText;
    }

    public void setmImageResource(String mImageResource) {
        this.mImageResource = mImageResource;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }
}
