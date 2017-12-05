package com.mustofin.loginmvvm;

import android.content.Context;

import com.mustofin.loginmvvm.model.ModelUser;
import com.mustofin.loginmvvm.viewmodel.MainViewModel;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

/**
 * Created by frost on 05.12.2017.
 */

public class GetModelUserMVMUnitTest {
    @Mock
    ModelUser mockModelUser;
    @Mock
    Context mockContext;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_GetUsernameMainVM(){
        MainViewModel mainViewModel = new MainViewModel(mockContext,mockModelUser);
        mainViewModel.setUser("user","password");
        assertEquals("user",mainViewModel.getUsername());
    }
    @Test
    public void test_GetPasswordMainVM(){
        MainViewModel mainViewModel = new MainViewModel(mockContext,mockModelUser);
        mainViewModel.setUser("user","password");
        assertEquals("password",mainViewModel.getPassword());
    }
}
