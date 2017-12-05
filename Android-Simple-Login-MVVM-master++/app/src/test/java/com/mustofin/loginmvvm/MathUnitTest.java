package com.mustofin.loginmvvm;

import com.mustofin.loginmvvm.model.ClassA;
import com.mustofin.loginmvvm.model.ClassB;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by frost on 05.12.2017.
 */

public class MathUnitTest {
    @Ignore
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_ClassAandB(){
        ClassA classA = new ClassA();
        ClassB classB = new ClassB();
        System.out.println("Before ClassA key: "+classA.GetKey());
        System.out.println("Before ClassB key: "+classB.GetKey());
        classA.SetKeyClassB(classB);
        assertEquals("SETKEYFROMA",classB.GetKey());
        System.out.println("After ClassA key: "+classA.GetKey());
        System.out.println("After ClassB key: "+classB.GetKey());
    }
}
