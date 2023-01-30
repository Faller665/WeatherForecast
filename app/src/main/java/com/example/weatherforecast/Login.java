package com.example.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;




public class Login extends AppCompatActivity {
    private ImageView background1;
    private EditText name;
    private EditText password;
    private ImageView name1;
    private ImageView password1;
    private String PhotoJson;
    private String LoginJson;
    private String UserName;
    private String Password;
    private Button login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        Glide.with(Login.this).load(R.drawable.login).into(background1);
        front();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserName=name.getText().toString();
                Password=password.getText().toString();
                login();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }
//    控件的初始化
    private void initView(){
        background1=findViewById(R.id.login_img);
        name=findViewById(R.id.et_name);
        password=findViewById(R.id.et_password);
        name1=findViewById(R.id.imageView);
        password1=findViewById(R.id.imageView2);
        login=findViewById(R.id.Bt_login);
        register=findViewById(R.id.Bt_register);
    }
//    将控件置于最上层
    private void front(){
        name1.bringToFront();
        password1.bringToFront();
        name.bringToFront();
        password.bringToFront();
    }

    private void login(){
        if (!UserName.equals("")&&!Password.equals("")){
            OkHttpClient client=new OkHttpClient();
            FormBody formBody=new FormBody.Builder()
                    .add("username",UserName)
                    .add("password",Password).build();
            Request request=new Request.Builder()
                    .url("https://www.wanandroid.com/user/login")
                    .post(formBody)
                    .build();
            Call call=client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Toast.makeText(Login.this, "没有注册", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    LoginJson=response.body().string();
                    try {
                        JSONObject jsonObject=new JSONObject(LoginJson);

                        int errorCode=jsonObject.getInt("errorCode");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(errorCode==0){
                                    Intent intent=new Intent(Login.this,main.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(Login.this, "账号或者密码不匹配或者该账号未注册", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        else{
            Toast.makeText(Login.this, "登陆失败，账号或者密码为空", Toast.LENGTH_SHORT).show();
        }
    }
}