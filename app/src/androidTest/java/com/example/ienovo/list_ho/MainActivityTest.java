package com.example.ienovo.list_ho;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ienovo on 30/12/19.
 */
public class MainActivityTest {

  MainActivity inti;


@Rule

public ActivityTestRule<MainActivity> test =new ActivityTestRule<MainActivity>(MainActivity.class);

@Before
public  void inti(){

inti=new MainActivity();

}

    @Test
    public void loadProducts() throws Exception {

       String test_v= inti.v_unit_test;

assertEquals("loaded",test_v);
    }

}