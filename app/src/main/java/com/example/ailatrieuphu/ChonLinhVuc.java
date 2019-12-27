package com.example.ailatrieuphu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChonLinhVuc extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    TextView txtUsername,txtCredit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    String ID1,ID2,ID3,ID4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_linh_vuc);
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportLoaderManager().restartLoader(0, null, this);
        this.btn1 = findViewById(R.id.btnLinhVuc1);
        this.btn2 = findViewById(R.id.btnLinhVuc2);
        this.btn3 = findViewById(R.id.btnLinhVuc3);
        this.btn4 = findViewById(R.id.btnLinhVuc4);
        txtUsername = findViewById(R.id.NguoiDung);
        txtCredit = findViewById(R.id.TienCredit);
        sharedPreferences = getSharedPreferences("com.example.ailatrieuphu", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String credit = sharedPreferences.getString("credit", "");
        String User = sharedPreferences.getString("ten_dang_nhap", "");
        this.txtUsername.setText(User);
        this.txtCredit.setText(credit);

    }

    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        return new LinhVucLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("lstDanhSach");
                for (int i = 0; i < itemsArray.length(); i++) {

                    this.btn1.setText(itemsArray.getJSONObject(i).getString("ten_linh_vuc"));
                    ID1= itemsArray.getJSONObject(i).getString("linh_vuc_id");
                    i++;
                    this.btn2.setText(itemsArray.getJSONObject(i).getString("ten_linh_vuc"));
                    ID2= itemsArray.getJSONObject(i).getString("linh_vuc_id");
                    i++;
                    this.btn3.setText(itemsArray.getJSONObject(i).getString("ten_linh_vuc"));
                    ID3= itemsArray.getJSONObject(i).getString("linh_vuc_id");
                    i++;
                    this.btn4.setText(itemsArray.getJSONObject(i).getString("ten_linh_vuc"));
                    ID4= itemsArray.getJSONObject(i).getString("linh_vuc_id");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    public void LaunchCauHoi1(View view) {

        Intent intent = new Intent(this, CauHoi.class);
        String layID = ID1;
        intent.putExtra("ID", layID);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }
    public void LaunchCauHoi2(View view) {

        Intent intent = new Intent(this, CauHoi.class);
        String layID = ID2;
        intent.putExtra("ID", layID);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }
    public void LaunchCauHoi3(View view) {

        Intent intent = new Intent(this, CauHoi.class);
        String layID = ID3;
        intent.putExtra("ID", layID);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }
    public void LaunchCauHoi4(View view) {

        Intent intent = new Intent(this, CauHoi.class);
        String layID = ID4;
        intent.putExtra("ID", layID);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, TrangChu.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
