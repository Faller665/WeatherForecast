package com.example.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class main extends AppCompatActivity {

    private String weatherJson;
    private String weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void getData(){
        OkHttpClient client=new OkHttpClient();
        HttpUrl.Builder builder=HttpUrl.parse("https://v2.alapi.cn/api/weather/forecast").newBuilder();
        builder.addQueryParameter("location","重庆");
        Request request=new Request.Builder()
                .url(builder.build())
                .addHeader("token","RAOQHDgr4TZmvLkl")
                .build();
        Call call= client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(main.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                weatherJson=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(weatherJson);
                    JSONObject data=jsonObject.getJSONObject("data");
                    JSONArray daily_forecast=data.getJSONArray("daily_forecast");
                    for (int i = 0; i < 1; i++) {
                        JSONObject today=daily_forecast.getJSONObject(i);
                        weather=today.getString("cond_txt_n");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}