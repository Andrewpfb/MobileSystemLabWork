package com.mustofin.loginmvvm.model;

/**
 * Created by frost on 05.12.2017.
 */

public class ClassB {
    String key;
    public ClassB(){
        key = "ClassB";
    }
    public void SetKeyBFromA(ClassB classB,String key){
       classB.key = key;
    }
    public String GetKey(){
        return key;
    }
}
