package com.example.sample12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id,et_pass,et_name,et_age;
    private Button btn_register;

    private RadioGroup radioGroup;

    private String result="";
    private CheckBox disease1,disease2,disease3,disease4;

    String sex="여성";
    public RegisterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {//액티비티 시작시 처음 실행된느 생명주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id=findViewById(R.id.et_id);
        et_pass=findViewById(R.id.et_pass);
        et_name=findViewById(R.id.et_name);
        et_name.setPrivateImeOptions("defaultInputmode=korean;");
        et_age=findViewById(R.id.et_age);



        radioGroup = findViewById(R.id.genderGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                Toast.makeText(RegisterActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                if(!sex.equals(radioButton.getText())){
                    sex = sex.replaceAll(sex, (String) radioButton.getText());
                }
            }
        });

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

        //회원가입 클릭시
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 현재 입력되있는 값을 가져온다.
                String userID=et_id.getText().toString();
                String userPass=et_pass.getText().toString();
                String userName=et_name.getText().toString();
                int userAge = Integer.parseInt(et_age.getText().toString());
                String userSex = sex;
                String userDisease = result;

                Response.Listener<String>responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){//회원등록이 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 성공하였습니다." , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, Login.class);
                                startActivity(intent);
                                finish();
                            }else {//회원등록 실패
                                Toast.makeText(getApplicationContext(),"회원 등록에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return ;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //서버로 volley를 이용해서 요청
                RegisterRequest registerRequest = new RegisterRequest(userID,userPass,userName,userAge,userSex,userDisease,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
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