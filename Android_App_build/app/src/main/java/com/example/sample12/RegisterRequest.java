package com.example.sample12;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class RegisterRequest extends StringRequest {

    // 서버 URL 설정 (PHP 레지스터 파일 연동)
    final static private String URL = "http://ye20327.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPassword, String userName,
                           int userAge, int user2num, Response.Listener<String> Listener) {
        super(Method.POST,URL,Listener,null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPassword);
        map.put("userName",userName);
        map.put("userAge",userAge + "");
        map.put("user2num", user2num + "");

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}