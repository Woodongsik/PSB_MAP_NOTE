package com.woodongsik.psb_map_note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

// This class is called when user click on a new location to create a new memo on the Google Map

public class MemoInputActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_input);
        Intent intent = getIntent();
        double longitude = intent.getExtras().getDouble("longitude"); // get Longitude
        double latitude = intent.getExtras().getDouble("latitude");   // get Latitude

        String currentLocation = "1) Longitude: " + longitude + "\n2) Latitude    : " + latitude;

        // the code below is to set current location to show on the screen
        TextView textGPSView = (TextView)findViewById(R.id.MemoTextViewGPS);
        textGPSView.setText(currentLocation);


        // the code below is to call Date class to get current date and time
        Date date;
        long now;
        now = System.currentTimeMillis();
        date = new Date(now);
        SimpleDateFormat sdfnow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strNow = sdfnow.format(date);

        TextView textDateView = (TextView)findViewById(R.id.MemoTextViewDate);
        textDateView.setText(strNow);

    }

    // method below process when user click a new location to save a memo.
    public void onSaveButtonClicked(View view) {
        EditText editInputTitle = findViewById(R.id.MemoTitleEdit);
        EditText editInputMemo = findViewById(R.id.MemoContentEdit);
        TextView textViewDate = findViewById(R.id.MemoTextViewDate);

        String inputedTitle = editInputTitle.getText().toString(); // save memo title
        String inputedMemo = editInputMemo.getText().toString();   // save memo contents
        String inputedDate = textViewDate.getText().toString();    // save the current date and time

        Intent intent = new Intent();
        intent.putExtra("title" , inputedTitle);
        intent.putExtra("memo" , inputedMemo);
        intent.putExtra("date" , inputedDate);

        setResult(RESULT_OK,intent);
        
        Toast.makeText(
                this, "saved",
                Toast.LENGTH_SHORT)
                .show();
        finish();
    }

    public void onCancelButtonClicked(View view) {
        Toast.makeText(
                this,
                "canceled",
                Toast.LENGTH_SHORT)
                .show();
        finish();
    }
}