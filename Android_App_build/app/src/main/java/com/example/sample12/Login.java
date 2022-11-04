package com.example.sample12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class Login extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login;
    private TextView btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);


//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Login.this,RegisterActivity.class);
//                startActivity(intent);
//            }
//        });


        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // EditText에 현재 입력되어있는 값을 get(가져옴)
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            // 로그인 요청 후 json으로 값 받음. 성공여부확인
                            boolean success = jsonObject.getBoolean("success");
                            // 로그인 성공여부 받음

                            if (success) { // 로그인 성공했을 경우
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");
                                String userName = jsonObject.getString("userName");
                                String Phone_number = jsonObject.getString("Phone_number");

                                Toast.makeText(getApplicationContext(),"로그인 성공", Toast.LENGTH_SHORT).show();


                                // 로그인 성공 후 main액티비티로 전환
                                Intent intent = new Intent(Login.this,MainActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("userPass",userPass);
                                intent.putExtra("userName", userName);
                                intent.putExtra("Phone_number",Phone_number);
                                // 성공했으니 액티비티 실행
                                startActivity(intent);
                                finish();
                            }
                            else { // 로그인 실패했을 경우
                                Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);
            }
        });



        TextView registerButton = (TextView) findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this,RegisterActivity.class);
                Login.this.startActivity(registerIntent);
            }
        });


    }
}