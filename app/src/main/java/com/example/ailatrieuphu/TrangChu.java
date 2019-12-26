package com.example.ailatrieuphu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;

public class TrangChu extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
    }

    public void LaunchNapCredit(View view) {
        Intent intent = new Intent (this, NapCredit.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
    }


    public void LaunchChonLinhVuc(View view) {
        Intent intent = new Intent (this, ChonLinhVuc.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn 1 lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void LaunchLogin(View view) {
        new SweetAlertDialog(TrangChu.this, SweetAlertDialog.WARNING_TYPE)

                .setTitleText("Bạn có chắc chắn muốn đăng xuất ?")
                .setCancelButton("không", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .setConfirmText("Có")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        Intent intent = new Intent (TrangChu.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }

    public void LaunchThongTinXepHang(View view) {
        Intent intent = new Intent (this, ThongTinXepHangMain.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
    }

    public void LaunchThongTinCaNhan(View view) {
        Intent intent = new Intent (this,ThongTinCaNhan.class);
        startActivity(intent);
    }
}
