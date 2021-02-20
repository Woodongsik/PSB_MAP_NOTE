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

// the class below does test of MemoListItemCheckActivity class and its methods
@RunWith(MockitoJUnitRunner.class)
public class MemoListItemCheckActivityTest extends TestCase {

    @Mock
    MemoListItemCheckActivity memoListItemCheckActivity = new MemoListItemCheckActivity();

    @Mock
    Bundle mockBundle;

    @Mock
    View mockView;


    @Test
    public void mockTest(){
        // the code belows checks if memoListItemCheckActivity is not null
        MockitoAnnotations.initMocks(this);
    };

    @Test
    public void testOnCreate(){
        // onCreate() has no return value
        memoListItemCheckActivity.onCreate(mockBundle);
    }

    @Test
    public void testOnMemoCheckDeleteButtonClicked() {
        // onMemoCheckDeleteButtonClicked() has no return value
        memoListItemCheckActivity.onMemoCheckDeleteButtonClicked(mockView);
    }

    @Test
    public void testOnMemoCheckEditButtonClicked() {
        // onMemoCheckEditButtonClicked() has no return value
        memoListItemCheckActivity.onMemoCheckEditButtonClicked(mockView);
    }

    @Test
    public void testOnMemoCheckCancelButtonClicked() {
        // onMemoCheckCancelButtonClicked() has no return value
        memoListItemCheckActivity.onMemoCheckCancelButtonClicked(mockView);
    }
}