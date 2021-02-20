package com.woodongsik.psb_map_note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.woodongsik.psb_map_note.MapsActivity.REQUEST_CODE_MENU;
import static com.woodongsik.psb_map_note.MapsActivity.database_count;

// This class controls the list of PSB Map memo
public class MemoListActivity extends AppCompatActivity {
    private ListView listView;
    List fileList = new ArrayList<>();
    ArrayAdapter adapter;
    public static List mapMemoList = new ArrayList<MapMemo>();
    private int indexId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        listView= (ListView) findViewById(R.id.lv_fileList);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_memo_item_list, fileList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                indexId = Long.valueOf(id).intValue();
                Intent intent = new Intent(getApplicationContext(),MemoListItemCheckActivity.class);
                intent.putExtra("id", position);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("MapMemos");

        // read memo data from the Firebase database
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fileList.clear();
                mapMemoList.clear();
                MapMemo.totalNumber = 1;
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    String date = fileSnapshot.child("date").getValue(String.class);
                    String title = fileSnapshot.child("title").getValue(String.class);
                    String memo = fileSnapshot.child("memo").getValue(String.class);
                    double longitude = fileSnapshot.child("longitude").getValue(double.class);
                    double latitude = fileSnapshot.child("latitude").getValue(double.class);
                    mapMemoList.add(new MapMemo(longitude, latitude, memo, date, title));
                    String str = "Date: " + date + "\nTitle: " + title;
                    fileList.add(str);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

        int countNum = 1;
        for(int i = 0; i <mapMemoList.size(); i++){
            MapMemo tmp = (MapMemo) mapMemoList.get(i);
            String str = "Date: " + tmp.getDate() + "\nTitle: " + tmp.getTitle();
            // createNewMemo(tmp, countNum);
            // countNum ++;
            //fileList.add(str);
            adapter.notifyDataSetChanged();
        }
        adapter.notifyDataSetChanged();
    }


    // this method get results message from MemoListItemCheckActivity class
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_MENU){
            if(resultCode == 7){
                mDatabase.removeValue();

                int countNum = 1;
                for(int i = 0; i <mapMemoList.size(); i++){
                    MapMemo tmp = (MapMemo) mapMemoList.get(i);
                    String str = "Date: " + tmp.getDate() + "\nTitle: " + tmp.getTitle();
                    createNewMemo(tmp, countNum);
                    countNum ++;
                    //fileList.add(str);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    // this method save a new memo to the Firebase database
    private void createNewMemo(MapMemo mapMemo, int num) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("MapMemos").child(Integer.toString(num)).setValue(mapMemo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}