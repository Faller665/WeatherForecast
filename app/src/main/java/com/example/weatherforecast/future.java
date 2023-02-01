package com.example.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class future extends AppCompatActivity {
    private ArrayList<data> dataArrayList;
    private RecyclerView recyclerView;
private String weatherJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future);
        dataArrayList=new ArrayList<>();
        Intent intent=getIntent();
        String location=intent.getStringExtra("location");
        initView();
        getAndHandleData(location);
    }
private void initView(){
        recyclerView=findViewById(R.id.racycle);
}
    private void getAndHandleData(String item){
        OkHttpClient client=new OkHttpClient();
        HttpUrl.Builder builder=HttpUrl.parse("https://v2.alapi.cn/api/weather/forecast").newBuilder();
        builder.addQueryParameter("location",item);
        Request request=new Request.Builder()
                .url(builder.build())
                .addHeader("token","RAOQHDgr4TZmvLkl")
                .build();
        Call call= client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(future.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                weatherJson=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(weatherJson);
                    JSONObject data1=jsonObject.getJSONObject("data");
                    JSONArray daily_forecast=data1.getJSONArray("daily_forecast");
                    for (int i = 0; i < daily_forecast.length(); i++) {
                        JSONObject today=daily_forecast.getJSONObject(i);
                       int tep_max=today.getInt("tmp_max");
                       int tep_min=today.getInt("tmp_min");
                      String  weather=today.getString("cond_txt_d");
                      String date=today.getString("date");
                        data futureData=new data();
                        futureData.setDate(date);
                        futureData.setWeather(weather);
                        futureData.setTem_min(tep_min);
                        futureData.setTem_max(tep_max);
                        dataArrayList.add(futureData);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           rvAdapter myadaoter=new rvAdapter(dataArrayList);
                           recyclerView.setAdapter(myadaoter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(future.this));
                            DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(future.this,DividerItemDecoration.VERTICAL);
                            recyclerView.addItemDecoration(dividerItemDecoration);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}