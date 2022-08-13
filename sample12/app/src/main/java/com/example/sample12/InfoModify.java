package com.example.sample12;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoModify extends AppCompatActivity {

    String result="";
    CheckBox disease1,disease2,disease3,disease4;
    EditText et_pass, et_pass2;
    Button btn_save;
    String ID="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_modify);


        et_pass = findViewById(R.id.et_pass);
        et_pass2 = findViewById(R.id.et_pass2);


        Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");
        ID = userID;



        findViewById(R.id.disease1).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Checked1(v);
            }
        });

        findViewById(R.id.disease2).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Checked2(v);
            }
        });

        findViewById(R.id.disease3).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Checked3(v);
            }
        });

        findViewById(R.id.disease4).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Checked4(v);
            }
        });

        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //EditText에 현재 입력되있는 값을 가져온다.
                String userID = ID;
                String userPass=et_pass2.getText().toString();
                String userDisease = result;

                Response.Listener<String>responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){//정보변경이 성공한 경우
                                Toast.makeText(getApplicationContext(),"정보 변경에 성공하였습니다." , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(InfoModify.this, MainActivity.class);
                                //startActivity(intent);
                                finish();
                            }else {//정보 변경 실패
                                Toast.makeText(getApplicationContext(),"정보 변경에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return ;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //서버로 volley를 이용해서 요청
                ModifyRequest modifyRequest = new ModifyRequest(userID, userPass,userDisease,responseListener);
                RequestQueue queue = Volley.newRequestQueue(InfoModify.this);
                queue.add(modifyRequest);
            }
        });



    }


    public String Checked1(View v){
        disease1 = findViewById(R.id.disease1);

        //String result = "";
        if(disease1.isChecked()){
            result = result + "고혈압 ";
        }else{
            result = result.replaceAll("고혈압 ","");
        }

        return result;
    }

    public String Checked2(View v){

        disease2 = findViewById(R.id.disease2);

        //String result = "";


        if(disease2.isChecked()){
            result = result + "당뇨 ";
        }else{
            result = result.replaceAll("당뇨 ","");
        }

        return result;
    }

    public String Checked3(View v){

        disease3 = findViewById(R.id.disease3);

        //String result = "";


        if(disease3.isChecked()){
            result = result + "심부전증 ";
        }else{
            result = result.replaceAll("심부전증 ","");
        }

        return result;
    }

    public String Checked4(View v){

        disease4 = findViewById(R.id.disease4);

        //String result = "";


        if(disease4.isChecked()){
            result = result + "부정맥 ";
        }else{
            result = result.replaceAll("부정맥 ","");
        }

        return result;
    }
}