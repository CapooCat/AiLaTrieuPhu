package com.example.ailatrieuphu;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.renderscript.Sampler;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CauHoi extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS= 30000;

    private TextView mTextViewCountDown;
    private ProgressBar mProgressBar;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);


        Button mShowDialog = findViewById(R.id.btnTroGiup);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChart();
            }
        });

        Button mShowDialogCall = findViewById(R.id.btnCall);
        mShowDialogCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCall();
            }
        });

        mTextViewCountDown = findViewById(R.id.txtCountDown);
        mProgressBar = findViewById(R.id.ThanhThoiGian);
        mProgressBar.setMax((int) START_TIME_IN_MILLIS / 1000);
        mProgressBar.setProgress((int) START_TIME_IN_MILLIS / 1000);
        if(!mTimerRunning)
        startTimer();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                mProgressBar.setProgress((int) (millisUntilFinished / 1000));
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                new SweetAlertDialog(CauHoi.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Đã hết giờ")
                        .setCancelButton("tiếp tục", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                Intent intent = new Intent (CauHoi.this, ChonLinhVuc.class);
                                startActivity(intent);
                            }
                        })
                        .setConfirmText("thoát")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                Intent intent = new Intent (CauHoi.this, TrangChu.class);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        }.start();

        mTimerRunning = true;
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000 ) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;


        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    public void Reset(View view) {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        Intent intent = new Intent (this,ChonLinhVuc.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new SweetAlertDialog(CauHoi.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Thông báo")
                    .setContentText("Bạn có chắc là muốn thoát ?? điểm của bạn sẽ mất")
                    .setCancelButton("không", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    })
                    .setConfirmText("có")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mCountDownTimer.cancel();
                            mTimerRunning = false;
                            mTimeLeftInMillis = START_TIME_IN_MILLIS;
                            Intent intent = new Intent (CauHoi.this,TrangChu.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void DialogChart(){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_chart);
        dialog.show();

        dialog.setCanceledOnTouchOutside(false);

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        Button mThank = dialog.findViewById(R.id.thank);
        mThank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        BarChart barChart;
        barChart = dialog.findViewById(R.id.chart1);

        BarDataSet barDataSet1 = new BarDataSet(dataValues1(), "");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(this,android.R.color.holo_orange_light));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_blue_light));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_green_light));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_red_light));
        barDataSet1.setColors(colors);

        barDataSet1.setStackLabels(new String[]{"A","B","C","D"});

        barChart.getDescription().setEnabled(false);
        barDataSet1.setValueTextColor(Color.WHITE);
        barDataSet1.setValueTextSize(13f);
        BarData barData = new BarData();
        barData.addDataSet(barDataSet1);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextColor(Color.WHITE);
        YAxis Left = barChart.getAxisLeft();
        Left.setTextColor(Color.WHITE);
        YAxis Right = barChart.getAxisRight();
        Right.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        Left.setDrawGridLines(false);
        Right.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        Left.setDrawAxisLine(false);
        Right.setDrawAxisLine(false);
        xAxis.setDrawLabels(false);
        Left.setDrawLabels(false);
        Right.setDrawLabels(false);

        barChart.setData(barData);
        barChart.invalidate();


    }

    Random rand1 = new Random();
    int a = rand1.nextInt(20);
    Random rand2 = new Random();
    int b = rand2.nextInt(20);
    Random rand3 = new Random();
    int c = rand3.nextInt(20);
    Random rand4 = new Random();
    int d = rand4.nextInt(20);

    String name[] = {"A","B","C","D"};
    Random random = new Random();
    int index = random.nextInt(name.length - 0) + 0;

    private void DialogCall() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_call);
        dialog.show();

        dialog.setCanceledOnTouchOutside(false);

        TextView mCauTraLoi = dialog.findViewById(R.id.CauTraLoi);
        Button mCamOn = dialog.findViewById(R.id.btn_Thank);

        mCamOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        mCauTraLoi.setText(name[index]);



        if( name[index] == "A")
        {
            mCauTraLoi.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
        }
        if(name[index] == "B")
        {
            mCauTraLoi.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
        }
        if(name[index] == "C")
        {
            mCauTraLoi.setTextColor(getResources().getColor(android.R.color.holo_green_light));
        }
        if(name[index] == "D")
        {
            mCauTraLoi.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }

    }

    private ArrayList<BarEntry> dataValues1(){
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        dataVals.add(new BarEntry(0,a));
        dataVals.add(new BarEntry(1,b));
        dataVals.add(new BarEntry(2,c));
        dataVals.add(new BarEntry(3,d));
        return dataVals;
    }

    public void Correct(View view) {
        view.setBackgroundResource(R.drawable.buttonstyle4);
    }

}
