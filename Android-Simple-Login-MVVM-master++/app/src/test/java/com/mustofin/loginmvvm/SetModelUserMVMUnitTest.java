package com.mustofin.loginmvvm;

import com.mustofin.loginmvvm.viewmodel.MainViewModel;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by frost on 05.12.2017.
 */

public class SetModelUserMVMUnitTest {
    @Test(timeout = 10)
    public void test_SetUsernameMainVM(){
        MainViewModel mainViewModel = Mockito.mock(MainViewModel.class);
        mainViewModel.setUser("user","password");
        Mockito.verify(mainViewModel).setUser(anyString(),anyString());
    }
}
