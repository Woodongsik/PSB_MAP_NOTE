package com.woodongsik.psb_map_note;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

// This is unit test for MapMemo Class with all functions inside it.
@RunWith(MockitoJUnitRunner.class)
public class MapMemoTest extends TestCase {
    private final Double TEST_Longitude = 1.0392;
    private final Double TEST_Latitude = 104.29029;
    private final String TEST_MEMO = "Test Memo = Life is Good";
    private final String TEST_DATE = "Test Date = 15/Feb/2021";
    private final String TEST_TITLE = "Test Title = Today's PSB Memo";
    private final String TEST_ID = "Test ID = 0291";

    // MapMemo Class Constructor (double x, double y, String memo, String date, String title)

    MapMemo testMapMemo = new MapMemo(TEST_Longitude, TEST_Latitude, TEST_MEMO, TEST_DATE, TEST_TITLE);

    @Test
    public void mockTest(){
        // the code belows checks if testMapMemo is not null
        MockitoAnnotations.initMocks(this);
    };

    @Test
    public void testSetId() {
        testMapMemo.setId(TEST_ID);
        String testIdResult = testMapMemo.getId();
        assertEquals(TEST_ID, testIdResult);
    }

    @Test
    public void testGetLongitude() {
        double testLongitudeResult = testMapMemo.getLongitude();
        assertEquals(TEST_Longitude, testLongitudeResult);
    }

    @Test
    public void testGetLatitude() {
        double testLatitudeResult = testMapMemo.getLatitude();
        assertEquals(TEST_Latitude, testLatitudeResult);
    }

    @Test
    public void testGetDate() {
        String testDateResult = testMapMemo.getDate();
        assertEquals(TEST_DATE, testDateResult);
    }

    @Test
    public void testGetTitle() {
        String testTitleResult = testMapMemo.getTitle();
        assertEquals(TEST_TITLE, testTitleResult);
    }

    @Test
    public void testGetMemo() {
        String testMemoResult = testMapMemo.getMemo();
        assertEquals(TEST_MEMO, testMemoResult);
    }
}