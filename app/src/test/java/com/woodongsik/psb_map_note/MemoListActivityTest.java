package com.woodongsik.psb_map_note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MemoListActivityTest extends TestCase {

    @Mock
    MemoListActivity memoListActivity;

    @Mock
    Bundle mockBundle;

    int mockRequestCod, mockResultCode;
    Intent mockIntent;


    @Test
    public void mockTest(){
        // the code belows checks if memoListActivity is not null
        MockitoAnnotations.initMocks(this);
    };

    @Test
    public void testOnCreate(){
        // onCreate() has no return value
        memoListActivity.onCreate(mockBundle);
    }

    @Test
    public void testOnActivityResult() {
        // onActivityResult() has no return value
        memoListActivity.onActivityResult(mockRequestCod, mockResultCode, mockIntent);
    }
}