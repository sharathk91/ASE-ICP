package com.example.shara.ase_icp_4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by shara on 2/6/2018.
 */

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class Translate extends AppCompatActivity {

    String sourcelanguage;
    String targetlanguage;
    String sourcetxt;
    TextView outputTextView;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        outputTextView = (TextView) findViewById(R.id.targettext);
    }
    public void Logout(View v) {
        Intent redirect = new Intent(Translate.this, Login.class);
        startActivity(redirect);
    }
    public void convertText(View v) {
        EditText sourcelang = (EditText) findViewById(R.id.sourcelang);
        EditText targetlang = (EditText) findViewById(R.id.targetlang);
        EditText sourcetext = (EditText) findViewById(R.id.sourcetext);
        sourcelanguage = sourcelang.getText().toString();
        targetlanguage = targetlang.getText().toString();
        sourcetxt = sourcetext.getText().toString();
        String getURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20180206T231433Z.3ec06fb4d394d903.07e9547f7d03a98174830ddd8024a84584e6b1ad&text=\""+sourcetxt+"\"&lang="+sourcelanguage+"-"+targetlanguage+"&[format=plain]&[options=1]&[callback=set]";//The API service URL


        //final String response1 = "";
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(getURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray convertedTextArray = jsonResult.getJSONArray("text");
                        final String convertedText = convertedTextArray.get(0).toString();
                        Log.d("okHttp", jsonResult.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                outputTextView.setVisibility(View.VISIBLE);
                                outputTextView.setText(convertedText);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception ex) {
            outputTextView.setText(ex.getMessage());

        }

    }
}
