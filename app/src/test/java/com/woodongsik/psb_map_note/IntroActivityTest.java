package com.woodongsik.psb_map_note;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


// This class is a Mockito test for IntroActivity class.
@RunWith(MockitoJUnitRunner.class)
public class IntroActivityTest{

    @Mock
    IntroActivity introActivity = new IntroActivity();

    @Mock
    Bundle mockBundle;

    @Mock
    View mockView;

    @Test
    public void mockTest(){
        // the code belows checks if introActivity is not null
        MockitoAnnotations.initMocks(this);
    };

    @Test
    public void onCreateTest(){
        // onCreate has no return value
        introActivity.onCreate(mockBundle);
    }

    @Test
    public void sendMessageListTest(){
        // sendMessageListTest() has no return value
        introActivity.sendMessageList(mockView);
    }
}