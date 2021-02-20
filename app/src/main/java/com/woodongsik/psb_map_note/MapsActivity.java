package com.woodongsik.psb_map_note;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/*
This class is called when User choose first menu on the main page which is "to go to Google Map API"
This MapsActivity class control Google Map API and also shows the current memo on the map
with diverse images and photos, showing different memo concepts.
*/

public class MapsActivity extends FragmentActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback {

    private final String PSB_PARTNER = "a partner of PSB Map Note";
    private final String PSB_TITLE = "PSB Academy in a partnership with Coventry University in UK";
    private final String LFNK_TITLE =  "LFNK Restaurant (★★★★☆)\nin a partnership with PSB Map Note";
    private final String HOLLIN_TITLE = "Hollin Cafe (★★★☆)\nin a partnership with PSB Map Note";
    private final String PRINT_THAT_NOW_TITLE = "Print That Now (★★★★★)\nin a partnership with PSB Map Note";
    private final String MY_GYM = "MY GYM Fitness Center (★★☆)\nin a partnership with PSB Map Note";
    private final String ASIA_CARZ = "ASIA CARZ car rent (★★★★☆)\nin a partnership with PSB Map Note";
    private final String DAWSON_BOWL = "DAWSON BOWL bowling center (★★☆)\nin a partnership with PSB Map Note";
    private final String BIKE_WORLD = "BIKE WORLD bike shop (★★★★★)\nin a partnership with PSB Map Note";
    private final String FACE_SHOP = "The Face Shop (★★★★☆)\nin a partnership with PSB Map Note";

    final String PSB_MEMO = "PSB Academy Stem Campus";

    private DatabaseReference mDatabase;                    // This is to use Firebase database
    private GoogleMap mMap;                                 // This is to use Google Map API
    private int MY_LOCATION_REQUEST_CODE = 1;               // This is to check if the user click the "Current Location" icon on the Google Map
    public static final int REQUEST_CODE_MENU = 101;
    public static int database_count = 0;
    final MarkerOptions markerOptions = new MarkerOptions();

    Double latitude = 0.0;
    Double longitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // mDatabase is to connect to the Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onResume() {

        super.onResume();

    }


    /*
    The function below is to deal with the situation when the user first click "current location" which requires the phone
    to allow the app to use its GPS location
    */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                }
                mMap.setMyLocationEnabled(true);
            }
        }
    }


    // The code below check if PSB Map Note attains the right to use current location with GPS on the phone.
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1);
        }
        
        // This codes belows are to move the phone screen to the current GPS location
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);


        // The code below changes google marker icon to a partner company's logo (Asia Carz)
        // The code below create a marker on the very position like the number belows on the google map (Asia Carz)
        BitmapDrawable bitmapdraw1 = (BitmapDrawable)getResources().getDrawable(R.drawable.asia_carz_map_marker);
        Bitmap b1 = bitmapdraw1.getBitmap();
        Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, 230, 400, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        LatLng tour = new LatLng(1.3367967521771074, 103.84056555205751);
        mMap.addMarker(markerOptions.position(tour)
                .title(ASIA_CARZ)
                .snippet(PSB_PARTNER)
        );


        // The code below changes google marker icon to a partner company's logo (Dawson Bowl )
        // The code below create a marker on the very position like the number belows on the google map (Dawson Bowl)
        bitmapdraw1 = (BitmapDrawable)getResources().getDrawable(R.drawable.dawson_bowl_map_marker);
        b1 = bitmapdraw1.getBitmap();
        smallMarker1 = Bitmap.createScaledBitmap(b1, 230, 400, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        tour = new LatLng(1.3440688376964227, 103.8589919186262);
        mMap.addMarker(markerOptions.position(tour)
                .title(DAWSON_BOWL)
                .snippet(PSB_PARTNER)
        );


        // The code below changes google marker icon to a partner company's logo (Face shop)
        // The code below create a marker on the very position like the number belows on the google map (Face shop)
        bitmapdraw1 = (BitmapDrawable)getResources().getDrawable(R.drawable.face_shop_map_marker);
        b1 = bitmapdraw1.getBitmap();
        smallMarker1 = Bitmap.createScaledBitmap(b1, 230, 400, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        tour = new LatLng(1.3246477257515725, 103.84122162248373);
        mMap.addMarker(markerOptions.position(tour)
                .title(FACE_SHOP)
                .snippet(PSB_PARTNER)
        );


        // The code below changes google marker icon to a partner company's logo (Bike World)
        // The code below create a marker on the very position like the number belows on the google map (Bike World)
        bitmapdraw1 = (BitmapDrawable)getResources().getDrawable(R.drawable.bike_world_map_marker);
        b1 = bitmapdraw1.getBitmap();
        smallMarker1 = Bitmap.createScaledBitmap(b1, 230, 400, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        tour = new LatLng(1.3250335577641645, 103.85222770644694);
        mMap.addMarker(markerOptions.position(tour)
                .title(BIKE_WORLD)
                .snippet(PSB_PARTNER)
        );


        // The code below changes google marker icon to a partner company's logo (LFNK Restaurant)
        // The code below create a marker on the very position like the number belows on the google map (LFNK Restaurant)
        bitmapdraw1 = (BitmapDrawable)getResources().getDrawable(R.drawable.lfnk_map_marker);
        b1 = bitmapdraw1.getBitmap();
        smallMarker1 = Bitmap.createScaledBitmap(b1, 230, 400, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        tour = new LatLng(1.3388418650694656, 103.850140026834);
        mMap.addMarker(markerOptions.position(tour)
                .title(LFNK_TITLE)
                .snippet(PSB_PARTNER)
        );


        // The code below changes google marker icon to a partner company's logo (Hollin Coffee)
        // The code below create a marker on the very position like the number belows on the google map (Hollin Coffee)
        bitmapdraw1 = (BitmapDrawable)getResources().getDrawable(R.drawable.hollin_map_marker);
        b1 = bitmapdraw1.getBitmap();
        smallMarker1 = Bitmap.createScaledBitmap(b1, 230, 400, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        tour = new LatLng(1.3315001467818057, 103.84820130054987);
        mMap.addMarker(markerOptions.position(tour)
                .title(HOLLIN_TITLE)
                .snippet(PSB_PARTNER)
        );


        // The code below changes google marker icon to a partner company's logo (BPrint That Now)
        // The code below create a marker on the very position like the number belows on the google map (Print That Now)
        bitmapdraw1 = (BitmapDrawable)getResources().getDrawable(R.drawable.printthatnow_map_marker);
        b1 = bitmapdraw1.getBitmap();
        smallMarker1 = Bitmap.createScaledBitmap(b1, 230, 400, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        tour = new LatLng(1.3324895687105052, 103.85870496311523);
        mMap.addMarker(markerOptions.position(tour)
                .title(PRINT_THAT_NOW_TITLE)
                .snippet(PSB_PARTNER)
        );


        // The code below changes google marker icon to a partner company's logo (My Gym)
        // The code below create a marker on the very position like the number belows on the google map (My Gym)
        bitmapdraw1 = (BitmapDrawable)getResources().getDrawable(R.drawable.my_gym_map_marker);
        b1 = bitmapdraw1.getBitmap();
        smallMarker1 = Bitmap.createScaledBitmap(b1, 230, 400, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        tour = new LatLng(1.3454108844494455, 103.84378719123112);
        mMap.addMarker(markerOptions.position(tour)
                .title(MY_GYM)
                .snippet(PSB_PARTNER)
        );


        // The code below changes google marker icon to PSB Academy logo (PSB Academy)
        // The code below create a marker on the very position like the number belows on the google map (PSB Academy)
        bitmapdraw1 = (BitmapDrawable)getResources().getDrawable(R.drawable.psb_map_stem_marker);
        b1 = bitmapdraw1.getBitmap();
        smallMarker1 = Bitmap.createScaledBitmap(b1, 230, 400, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        tour = new LatLng(1.3374411739899936, 103.84868887443692);
        mMap.addMarker(markerOptions.position(tour)
                .title(PSB_TITLE)
                .snippet(PSB_MEMO)
        );


        // The code below changes current google marker to its original Google Map marker
        BitmapDrawable bitmapdraw2 = (BitmapDrawable)getResources().getDrawable(R.drawable.psb_map_marker);
        Bitmap b2 = bitmapdraw2.getBitmap();
        Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, 200, 300, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker2));


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("MapMemos");

        loadMapMemo(databaseRef);

        // the code belows indicates the screen to move to the new position(tour) with 16 zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tour,16));


        // The code belows demonstrate what to happen when the user click the map marker on the Google Map
        // the code belows indicates local partner who will pay monthly fee for showing their ads on PSB Map Memo app
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int num = 0;
                String title = marker.getTitle();
                String memo = marker.getSnippet();

                switch(title){
                    case PSB_TITLE:
                        num = 1;
                        break;
                    case LFNK_TITLE:
                        num = 2;
                        break;
                    case HOLLIN_TITLE:
                        num = 3;
                        break;
                    case PRINT_THAT_NOW_TITLE:
                        num = 4;
                        break;
                    case MY_GYM:
                        num = 5;
                        break;
                    case ASIA_CARZ:
                        num = 6;
                        break;
                    case DAWSON_BOWL:
                        num = 7;
                        break;
                    case BIKE_WORLD:
                        num = 8;
                        break;
                    case FACE_SHOP:
                        num = 9;
                        break;
                    default:
                        num = 0;
                        break;
                }

                MemoPopupWindow mPsbMemoPopup = new MemoPopupWindow(MapsActivity.this, title, memo, num);
                mPsbMemoPopup.setCancelable(false);
                mPsbMemoPopup.show();


                // If the user click PSB Academy map marker, web browser will be open accessing "PSB Academy Homepage"
                if (title.equals(PSB_TITLE)) {
                    String url = "https://www.psb-academy.edu.sg/coventry-university";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                return false;
            }
        });


        // The code below demonstrate what to happen when the user click a new location on the Google map to create a new memo
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            // when the user click just one time the map
            @Override
            public void onMapClick(LatLng point) {
                AlertDialog.Builder builder = new AlertDialog.Builder(com.woodongsik.psb_map_note.MapsActivity.this);
                latitude = point.latitude; // get Latitude
                longitude = point.longitude; // get Longitude

                // create a alarming window to create a new memo
                builder.setTitle("※ A New PSB Map Memo");
                builder.setMessage("Do you want to write a new memo here?");
                builder.setNegativeButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(getApplicationContext(), com.woodongsik.psb_map_note.MemoInputActivity.class);
                                intent.putExtra("latitude", latitude);
                                intent.putExtra("longitude", longitude);
                                startActivityForResult(intent, REQUEST_CODE_MENU);
                            }
                        });
                builder.setPositiveButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.show();
            }

        });
    }

    // When the user clicks "the current position check" button
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }


    // When the user clicks "show my current location" button.
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU){
            if(resultCode == RESULT_OK){
                String title = data.getExtras().getString("title");
                String memo = data.getExtras().getString("memo");
                String date = data.getExtras().getString("date");

                createNewMemo(title,memo,date);
            }
        }
    }

    // the function below is to save a new memo in the Firebase database.
    private void createNewMemo(String title, String memo, String date) {
        MapMemo mapMemo = new MapMemo(longitude, latitude, memo, date, title);

        mDatabase.child("MapMemos").child(mapMemo.getId()).setValue(mapMemo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //
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

    // the function below is to get the memo data saved in the Firebase database and to show the memo on specific location on the Google map
    private void loadMapMemo(DatabaseReference databaseRef){
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String dataCount =  Long.toString(dataSnapshot.getChildrenCount());
                database_count = Integer.parseInt(dataCount);

                int count = 1;

                for (DataSnapshot memoMap : dataSnapshot.getChildren()){
                    String date = memoMap.child("date").getValue(String.class);
                    Double latitude = memoMap.child("latitude").getValue(Double.class);
                    Double longitude = memoMap.child("longitude").getValue(Double.class);
                    String title = memoMap.child("title").getValue(String.class);
                    String memo = memoMap.child("memo").getValue(String.class);
                    String contents = date + "\n" + memo;

                    LatLng tour = new LatLng(latitude, longitude);
                    mMap.addMarker(markerOptions.position(tour)
                            .title(title)
                            .snippet(contents)
                    );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}


