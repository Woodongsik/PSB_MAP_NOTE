package com.woodongsik.psb_map_note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

// This class shows cutsom-pop up memu to get user's input when user click a map marker on the Google Map API

public class MemoPopupWindow extends Dialog implements View.OnClickListener{
    private Context mContext;
    private TextView viewTitle;
    private TextView viewMemo;
    private TextView button_ok;
    private String title;
    private String memo;
    private LinearLayout linearBackground;
    private int num;

    public MemoPopupWindow(@NonNull Context context, String title, String memo, int num) {
        super(context);
        mContext = context;
        this.title = title;
        this.memo = memo;
        this.num = num;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_popup_window);
        button_ok = findViewById(R.id.button_memo_popup_ok);
        linearBackground = findViewById(R.id.linearLayout_Background);
        button_ok.setOnClickListener(this);
        viewTitle = findViewById(R.id.textTitle);
        viewTitle.setText(title);
        viewMemo = findViewById(R.id.textMemo);

        BitmapDrawable bitmapdraw;

        // the code below changes background images of the current location
        // the 8 images are local partner's shop photos (this function is to make profits as a business model)
        switch(num){
            case 1:
                bitmapdraw = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.psb_map_background);
                setBackgroundImage(bitmapdraw);
                break;
            case 2:
                bitmapdraw = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.lfnk_map_background);
                setBackgroundImage(bitmapdraw);
                break;
            case 3:
                bitmapdraw = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.holling_map_background);
                setBackgroundImage(bitmapdraw);
                break;
            case 4:
                bitmapdraw = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.print_that_now_map_background);
                setBackgroundImage(bitmapdraw);
                break;
            case 5:
                bitmapdraw = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.mygym_map_background);
                setBackgroundImage(bitmapdraw);
                break;
            case 6:
                bitmapdraw = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.asis_carz_map_background);
                setBackgroundImage(bitmapdraw);
                break;
            case 7:
                bitmapdraw = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.dawson_bowl_map_background);
                setBackgroundImage(bitmapdraw);
                break;
            case 8:
                bitmapdraw = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.bike_world_map_background);
                setBackgroundImage(bitmapdraw);
                break;
            case 9:
                bitmapdraw = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.face_shop_map_background);
                setBackgroundImage(bitmapdraw);
                break;
            default:
                bitmapdraw = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.memo_default_background);
                setBackgroundImage(bitmapdraw);
                viewMemo.setText(this.memo);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_memo_popup_ok:
                dismiss();
                break;
        }
    }

    private void setBackgroundImage(BitmapDrawable b1){
        linearBackground.setBackground(b1);
        viewMemo.setText("");
    }
}