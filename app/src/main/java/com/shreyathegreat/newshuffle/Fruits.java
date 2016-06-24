package com.shreyathegreat.newshuffle;

import android.os.Parcelable;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shreya on 11/2/16.
 */
public class Fruits implements Parcelable {

    public int image;
    public String name;
    public Fruits(String name,int image){

        this.image = image;

        this.name = name;
    }

    public int getimage() {
        return image;
    }


    public String getname() {
        return name;
    }
    public void setimage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int describeContents() {
        return 0;
    }
    private Fruits(Parcel in) {
        // This order must match the order in writeToParcel()
        name = in.readString();
        image= in.readInt();
        // Continue doing this for the rest of your member data
    }

    public void writeToParcel(Parcel out, int flags) {
        // Again this order must match the Question(Parcel) constructor
        out.writeString(name);
        out.writeInt(image);
        // Again continue doing this for the rest of your member data
    }
    public static final Parcelable.Creator<Fruits> CREATOR = new Parcelable.Creator<Fruits>() {
        public Fruits createFromParcel(Parcel in) {
            return new Fruits(in);
        }

        public Fruits[] newArray(int size) {
            return new Fruits[size];
        }
    };
}



