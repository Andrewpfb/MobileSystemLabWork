package com.mustofin.loginmvvm;

import android.content.Context;
import android.view.View;

import com.mustofin.loginmvvm.model.ModelUser;
import com.mustofin.loginmvvm.viewmodel.LoginViewModel;
import com.mustofin.loginmvvm.viewmodel.MainViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@Suite.SuiteClasses(value = {
        GetModelUserMVMUnitTest.class,
        SetModelUserMVMUnitTest.class,
        MathUnitTest.class
})
@RunWith(value = Suite.class)
public class ExampleUnitTest {
}