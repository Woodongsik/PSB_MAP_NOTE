package com.woodongsik.psb_map_note;

import android.os.Bundle;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

// This class is to test units of MapsActivity class
@RunWith(MockitoJUnitRunner.class)
public class MapsActivityTest extends TestCase {

    private final Double TEST_Longitude = 1.0392;
    private final Double TEST_Latitude = 104.29029;

    int mockRequestCode;
    int [] mockGrantResults;
    String [] mockPermission;

    MapsActivity mapsActivity = new MapsActivity();


    @Test
    public void mockTest(){
        // the code belows checks if MapsActivity class is not null
        MockitoAnnotations.initMocks(this);
    };

    @Test
    public void testOnRequestPermissionsResult() {
        // testOnRequestPermissionsResult() has no return value
        mapsActivity.onRequestPermissionsResult(mockRequestCode, mockPermission, mockGrantResults);
    }

    @Test
    public void testSetLatitude() {
        mapsActivity.setLatitude(TEST_Latitude);
        double testLatitudeResult = mapsActivity.getLatitude();
        assertEquals(TEST_Latitude, testLatitudeResult);
    }

    @Test
    public void testGetLatitude() {
        double testLatitudeResult = mapsActivity.getLatitude();
        assertEquals(0.0, testLatitudeResult);
    }

    @Test
    public void testSetLongitude() {
        mapsActivity.setLongitude(TEST_Longitude);
        double testLongitudeResult = mapsActivity.getLongitude();
        assertEquals(TEST_Longitude, testLongitudeResult);
    }

    @Test
    public void testGetLongitude() {
        double testLongitudeResult = mapsActivity.getLongitude();
        assertEquals(0.0, testLongitudeResult);
    }
}