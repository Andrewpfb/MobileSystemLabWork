package com.mustofin.loginmvvm.viewmodel;

import android.content.Context;

import com.mustofin.loginmvvm.model.ModelUser;


public class MainViewModel {
    private Context context;
    private ModelUser user;

    public MainViewModel(Context context, ModelUser user) {
        this.context = context;
        this.user = user;
    }

    public String getUsername(){
        return user.username;
    }
    public String getPassword(){
        return user.password;
    }
    public void setUser(String username, String password){
        this.user.username = username;
        this.user.password = password;
        ModelUser modelUser = new ModelUser();
        modelUser.NewUser(username,password);
    }
}
