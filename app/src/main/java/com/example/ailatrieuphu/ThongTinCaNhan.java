package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ThongTinCaNhan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
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
