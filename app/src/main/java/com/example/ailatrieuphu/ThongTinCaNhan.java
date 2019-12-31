package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ThongTinCaNhan extends AppCompatActivity {
    EditText SuaTaiKhoan,SuaEmail,SuaMatKhau,NhapLaiMatKhau;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        SuaTaiKhoan = findViewById(R.id.txtSuaTaiKhoan);
        SuaEmail = findViewById(R.id.txtSuaEmail);
        SuaMatKhau = findViewById(R.id.txtSuaMK);
        NhapLaiMatKhau = findViewById(R.id.txtSuaMK2);
        sharedPreferences = getSharedPreferences("com.example.ailatrieuphu", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        if (token == "") {
            Intent intent = new Intent(this, TrangChu.class);
            startActivity(intent);
        }
        SuaTaiKhoan.setText(sharedPreferences.getString("ten_dang_nhap", ""));
        SuaEmail.setText(sharedPreferences.getString("email", ""));
    }


    public void Sua(View view) {
        SuaTaiKhoan = findViewById(R.id.txtSuaTaiKhoan);
        SuaEmail = findViewById(R.id.txtSuaEmail);
        SuaMatKhau = findViewById(R.id.txtSuaMK);
        NhapLaiMatKhau = findViewById(R.id.txtSuaMK2);
        String TaiKhoan = SuaTaiKhoan.getText().toString();
        String Email = SuaEmail.getText().toString();
        String MatKhau = SuaMatKhau.getText().toString();
        String NhapLaiMK = NhapLaiMatKhau.getText().toString();
        String id = sharedPreferences.getString("id", "");

        if(MatKhau != "" && MatKhau != NhapLaiMK) {
            new SuaThongTinLoader().execute(TaiKhoan, Email, MatKhau, id);
            new SweetAlertDialog(ThongTinCaNhan.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Sửa thành công")
                    .show();
        }
        else {
            new SweetAlertDialog(ThongTinCaNhan.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Sửa thất bại")
                    .show();
        }


    }
}
