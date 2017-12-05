package com.mustofin.loginmvvm.model;

import android.os.Parcel;
import android.os.Parcelable;


public class ModelUser implements Parcelable{
    public String username;
    public String password;

    public ModelUser() {
    }

    public ModelUser(String username, String password) {
        this.username = username;
        this.password = password;
        NewUser(username,password);
    }

    public ModelUser(Parcel in) {
        username = in.readString();
        password = in.readString();
    }
    public void NewUser(String username,String password){
        this.username = username;
        this.password = password;
    }

    public static final Creator<ModelUser> CREATOR = new Creator<ModelUser>() {
        @Override
        public ModelUser createFromParcel(Parcel in) {
            return new ModelUser(in);
        }

        @Override
        public ModelUser[] newArray(int size) {
            return new ModelUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
    }
}
