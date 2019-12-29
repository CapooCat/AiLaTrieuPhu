package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ThongTinCaNhan extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        sharedPreferences = getSharedPreferences("com.example.ailatrieuphu", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        if (token == "") {
            Intent intent = new Intent(this, TrangChu.class);
            startActivity(intent);
        }
    }


    public void Sua(View view) {
        EditText TaiKhoan = findViewById(R.id.txtTenTaiKhoan);
        TaiKhoan.setEnabled(true);
        EditText Email = findViewById(R.id.txtSuaEmail);
        Email.setEnabled(true);
        EditText MK = findViewById(R.id.txtSuaMK);
        MK.setEnabled(true);
    }
}
