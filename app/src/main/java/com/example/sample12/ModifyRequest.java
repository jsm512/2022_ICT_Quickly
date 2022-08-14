package com.example.sample12;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class ModifyRequest extends StringRequest {

    // 서버 URL 설정 (PHP 레지스터 파일 연동)
    final static private String URL = "http://r2445.dothome.co.kr/Modify.php";
    private Map<String, String> map;

    public ModifyRequest(String userID, String userPassword, String userDisease, Response.Listener<String> Listener) {
        super(Method.POST,URL,Listener,null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPassword);
        map.put("userDisease", userDisease);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}