package com.example.ailatrieuphu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Forget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
    }
}
