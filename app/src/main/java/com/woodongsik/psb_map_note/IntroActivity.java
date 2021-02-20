package com.woodongsik.psb_map_note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/*
This is the first class of the PSB Map Note
This class shows the main page of the App, in other words, it is the launcher class.
Through this class, the user can choose two different menu (1. To Google Map/2. See the current memo list)
*/

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    // Through the function below, user can access to the Google map where a new memo can be made and be shown
    public void sendMessageMap(View view) {
        Intent intent = new Intent(getApplicationContext(),
                MapsActivity.class);
        startActivity(intent);
    }

    // Through the function below, user can see the currently saved memo list and edit or delete them.
    public void sendMessageList(View view) {
        Intent intent = new Intent(getApplicationContext(),
                com.woodongsik.psb_map_note.MemoListActivity.class);
        startActivity(intent);
    }
}
