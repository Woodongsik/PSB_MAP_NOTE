package com.woodongsik.psb_map_note;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.woodongsik.psb_map_note.MapsActivity.REQUEST_CODE_MENU;
import static com.woodongsik.psb_map_note.MemoListActivity.mapMemoList;

// This class show PSB Map Memo edit/delete table
public class MemoListItemCheckActivity extends AppCompatActivity {
    private int id;
    private int returnResult = 7;
    EditText editInputTitle;
    EditText editInputMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list_item_check);

        editInputTitle = findViewById(R.id.MemoTitleEdit);
        editInputMemo = findViewById(R.id.MemoContentEdit);
        TextView textViewDate = (TextView) findViewById(R.id.MemoTextViewDate);
        TextView textViewGPS = findViewById(R.id.MemoTextViewGPS);

        Intent intent = getIntent();
        id = intent.getExtras().getInt("id"); // 롱지튜드를 가져옴

        MapMemo tmpMemo = (MapMemo) mapMemoList.get(id);

        editInputTitle.setText(tmpMemo.getTitle());
        editInputMemo.setText(tmpMemo.getMemo());
        String position = "Longitude: " + tmpMemo.getLongitude() + "\nLatitude   : " + tmpMemo.getLatitude();
        textViewDate.setText(tmpMemo.getDate());
        textViewGPS.setText(position);
    }


    public void onMemoCheckDeleteButtonClicked(View v){
        // when user click delete memo button
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("※ Warning: Delete this Memo");
        builder.setMessage("Do you want to delete this memo?");
        builder.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mapMemoList.remove(id);
                        Intent intent = new Intent(getApplicationContext(), com.woodongsik.psb_map_note.MemoListActivity.class);
                        intent.putExtra("deleteClicked", true);
                        setResult(returnResult, intent);
                        Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
        builder.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }


    public void onMemoCheckEditButtonClicked(View v){
        // when user click edit(modify) memo button
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("※ Warning: Edit this Memo");
        builder.setMessage("Do you want to edit this memo?");
        builder.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MapMemo tmpMemo = (MapMemo) mapMemoList.get(id);
                        tmpMemo.setTitle(editInputTitle.getText().toString());
                        tmpMemo.setMemo(editInputMemo.getText().toString());
                        mapMemoList.set(id, tmpMemo);

                        Intent intent = new Intent(getApplicationContext(), com.woodongsik.psb_map_note.MemoListActivity.class);
                        intent.putExtra("deleteClicked", true);
                        setResult(returnResult, intent);
                        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
        builder.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

    public void onMemoCheckCancelButtonClicked(View v){
        finish();
    }
}