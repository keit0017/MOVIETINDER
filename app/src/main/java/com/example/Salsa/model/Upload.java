package com.example.Salsa.model;

import android.media.Image;
import android.widget.ImageView;

import com.google.firebase.database.Exclude;

public class Upload {

    private String mTitle,mDescription,ImageURi1,mKey;

    public Upload(){

    }
    public Upload(String name, String description1, String inputIamgeURI){

        if(name.trim().equals("")){
            name="NO TITLE";
        }
        if(description1.trim().equals("")){
            name="NO Description";
        }

        mTitle =name;
        mDescription= description1;
        ImageURi1= inputIamgeURI;

    }

    public String getImageURi() {
        return ImageURi1;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmTitle() {
        return mTitle;
    }

    @Exclude
    public String getmKey() {
        return mKey;
    }

    public String getUrl(){ return getImageURi().toString();}

    public void setImageURi(String imageURi) {
        ImageURi1 = imageURi;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Exclude
    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
