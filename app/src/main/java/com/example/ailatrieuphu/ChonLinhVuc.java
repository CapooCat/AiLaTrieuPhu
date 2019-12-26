package com.example.ailatrieuphu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
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
                    i++;
                    this.btn2.setText(itemsArray.getJSONObject(i).getString("ten_linh_vuc"));
                    i++;
                    this.btn3.setText(itemsArray.getJSONObject(i).getString("ten_linh_vuc"));
                    i++;
                this.btn4.setText(itemsArray.getJSONObject(i).getString("ten_linh_vuc"));
                //this.wTacGiaTextView.get(i).get().setText(itemsArray.getJSONObject(i).getString("tac_gia"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    public void LaunchCauHoi(View view) {

        Intent intent = new Intent(this, CauHoi.class);
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
