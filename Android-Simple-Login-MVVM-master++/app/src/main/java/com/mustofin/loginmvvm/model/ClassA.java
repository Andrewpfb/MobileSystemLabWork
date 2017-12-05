package com.mustofin.loginmvvm.model;

/**
 * Created by frost on 05.12.2017.
 */

public class ClassA {
    String key;
    public ClassA(){
        key="ClassA";
    }
    public void SetKeyClassB(ClassB classB){
        classB.SetKeyBFromA(classB,"SETKEYFROMA");
    }
    public String GetKey(){
        return key;
    }
}
