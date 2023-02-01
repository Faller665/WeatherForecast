package com.example.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * @Author      : 苟云东
 * @Email       : 2191288460@qq.com
 * @Date        : on 2022-1-21
 * @Description :简单实现了对天气的呈现
 */
public class main extends AppCompatActivity {

    private TextView tem;
    private String weatherJson;
    private int  tep_max;
    private int tep_min;
    private String weather;
    private TextView Weather;

    private ImageView city;
    private String data;
    private ImageView background;
    private ImageView imageView_wind;
    private ImageView imageView_air;
    private TextView Air;
    private TextView Wind;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private Button future11;
private TextView tv_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Intent intent=getIntent();
        String location=intent.getStringExtra("location");
        Glide.with(main.this).load(R.drawable.login).into(background);
        tv_city.setText(location);
        front();
        getAndHandleData(location);
        future11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(main.this,future.class);
                intent1.putExtra("location",location);
                startActivity(intent1);
                overridePendingTransition(R.anim.inter2,R.anim.out2);
            }
        });
    }
private void initView(){
        tem=findViewById(R.id.tv_tem);
        Weather=findViewById(R.id.iv_weather);
         textView1=findViewById(R.id.tv_1);
         textView2=findViewById(R.id.tv_2);
         textView3=findViewById(R.id.tv_3);
         textView4=findViewById(R.id.tv_4);
         textView5=findViewById(R.id.tv_5);
         Air=findViewById(R.id.tv_air);
         Wind=findViewById(R.id.tv_wind);
         background=findViewById(R.id.main_img);
         city=findViewById(R.id.imageView5);
         imageView_wind=findViewById(R.id.imageView6);
         imageView_air=findViewById(R.id.imageView4);
    future11=findViewById(R.id.bt_future);
    tv_city=findViewById(R.id.tv_city);
}
private void front(){
        tem.bringToFront();
        Weather.bringToFront();
        Air.bringToFront();
        Wind.bringToFront();
        city.bringToFront();
        imageView_air.bringToFront();
        imageView_wind.bringToFront();
        tv_city.bringToFront();
}

    private void getAndHandleData(String item){
        OkHttpClient client=new OkHttpClient();
        HttpUrl.Builder builder=HttpUrl.parse("https://v2.alapi.cn/api/tianqi").newBuilder();
        builder.addQueryParameter("city",item);
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
                 data=response.body().string();
                Log.d("TAG", "onResponse: "+data);
                    if(data!=null){
                        try {
                        JSONObject jsonObject=new JSONObject(data);
                        JSONObject a=jsonObject.getJSONObject("data");
                        weather=a.getString("weather");
                        tep_min=a.getInt("min_temp");
                        tep_max=a.getInt("max_temp");
                        String wind=a.getString("wind");
                        JSONObject aqi=a.getJSONObject("aqi");
                        int air=aqi.getInt("air");
                        String air_level=aqi.getString("air_level");
                        JSONObject index=a.getJSONObject("index");
                        JSONObject xiche=index.getJSONObject("xiche");
                        JSONObject ganmao=index.getJSONObject("ganmao");
                        JSONObject yundong=index.getJSONObject("yundong");
                        JSONObject chuangyi=index.getJSONObject("chuangyi");
                        JSONObject ziwaixian=index.getJSONObject("ziwaixian");
                        String xiche_content=xiche.getString("content");
                        String ganmao_content=ganmao.getString("content");
                        String yudon_content=yundong.getString("content");
                        String chuanyi_content=chuangyi.getString("content");
                        String ziwaixian_content=ziwaixian.getString("content");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView1.setText(xiche_content);
                                textView2.setText(ganmao_content);
                                textView3.setText(yudon_content);
                                textView4.setText(chuanyi_content);
                                textView5.setText(ziwaixian_content);
                                Weather.setText(weather);
                                tem.setText(tep_min+"℃/"+tep_max+"℃");
                                Wind.setText(wind);
                                Air.setText(air_level+air);
                            }
                        });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                       }


            }
        });
    }

}