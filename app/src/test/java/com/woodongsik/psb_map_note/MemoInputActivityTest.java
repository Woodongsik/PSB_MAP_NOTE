package com.woodongsik.psb_map_note;

import android.os.Bundle;
import android.view.View;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

// This class is to test units of MemoInputActivity class
@RunWith(MockitoJUnitRunner.class)
public class MemoInputActivityTest extends TestCase {


    @Mock
    MemoInputActivity memoInputActivity = new MemoInputActivity();

    @Test
    public void mockTest() {
        // the code belows checks if MemoInputActivity class is not null
        MockitoAnnotations.initMocks(this);
    }
}