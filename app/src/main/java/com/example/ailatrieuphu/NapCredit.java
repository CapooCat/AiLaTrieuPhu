package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NapCredit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_credit);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }
}
