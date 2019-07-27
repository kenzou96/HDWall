package com.vn.edu.poly.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    private ImageView imgA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);



        initView();

        Intent intent = getIntent();
        String source = intent.getStringExtra("Anh");
        Log.d("iiiiii", source);
//        startActivity(intent);

        Picasso.with(this).load(source).into(imgA);
    }

    private void initView() {
        imgA = (ImageView) findViewById(R.id.imgA);
    }
}
