package com.woodongsik.psb_map_note;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

// the class below tests MemoPopupWindow class
@RunWith(MockitoJUnitRunner.class)
public class MemoPopupWindowTest extends TestCase {

    int mockNum;
    String mockTitle, mockMemo;

    @Mock
    Bundle mockBundle;

    @Mock
    Context mockContext;

    @Mock
    View mockView;

    @Mock
    MemoPopupWindow memoPopupWindow = new MemoPopupWindow(mockContext,mockTitle, mockMemo, mockNum);


    @Test
    public void mockTest(){
        // the code belows checks if memoPopupWindow is not null
        MockitoAnnotations.initMocks(this);
    };

    @Test
    public void testOnCreate(){
        // onCreate() has no return value
        memoPopupWindow.onCreate(mockBundle);
    }

    public void testOnClick() {
        // onClick() has no return value
        memoPopupWindow.onClick(mockView);
    }
}