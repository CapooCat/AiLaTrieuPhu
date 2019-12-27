package com.example.ailatrieuphu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NapCredit extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private Button btn100C;
    private Button btn500C;
    private Button btn1000C;
    private Button btn2500C;
    private Button btn5000C;
    private Button btn10000C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_credit);
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportLoaderManager().restartLoader(0, null, this);
        this.btn100C = findViewById(R.id.btn100);
        this.btn500C = findViewById(R.id.btn500);
        this.btn1000C = findViewById(R.id.btn1000);
        this.btn2500C = findViewById(R.id.btn2500);
        this.btn5000C = findViewById(R.id.btn5000);
        this.btn10000C = findViewById(R.id.btn10000);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        return new CreditLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("lstDanhSach");
            for (int i = 0; i < itemsArray.length(); i++) {

                this.btn100C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                i++;
                this.btn500C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                i++;
                this.btn1000C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                i++;
                this.btn2500C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                i++;
                this.btn5000C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                i++;
                this.btn10000C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
