package com.example.ailatrieuphu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }
}
