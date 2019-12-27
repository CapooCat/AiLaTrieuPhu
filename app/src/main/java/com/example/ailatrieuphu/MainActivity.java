package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final static String FILE_NAME_SHAREREF = "com.example.ailatrieuphu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            System.exit(0);
        }

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN",token);
        if (token != "") { launchActivityMenu(); }


    }

    public void launchActivityMenu() {
        Intent intent = new Intent(this, TrangChu.class);
        startActivity(intent); }

    public void LaunchTrangChu(View view) {
        EditText txtUsername = findViewById(R.id.txtTaiKhoan);
        EditText txtPassword = findViewById(R.id.txtMatKhau);

        String TaiKhoan = txtUsername.getText().toString();
        String MatKhau = txtPassword.getText().toString();

        new DangNhapLoader(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject json = new JSONObject(s);
                    boolean success = json.getBoolean("success");
                    if (success) {
                        String token = "Bearer " + json.getString("token");
                        editor.putString("TOKEN", token);
                        editor.commit();
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Đăng nhập thành công")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismissWithAnimation();
                                        launchActivityMenu();
                                    }
                                })
                                .show();
                    } else {
                        String msg = json.getString("msg");
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Đăng nhập thất bại")
                        .setContentText("Sai tài khoản hoặc mật khẩu, vui lòng nhập lại")
                        .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(TaiKhoan, MatKhau);
    }


    public void LaunchForget(View view) {
        Intent intent = new Intent(this, Forget.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }

    public void LaunchRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
    }
}
