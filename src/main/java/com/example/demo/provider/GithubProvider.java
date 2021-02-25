package com.example.demo.provider;
import com.alibaba.fastjson.JSON
import com.example.demo.dto.AcessTokenDTO;
import com.example.demo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
//alt+enter引入jar包
@Component
public class GithubProvider {
    public String getAcessToken(AcessTokenDTO acessTokenDTO){
        MediaType mediaType
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(acessTokenDTO));
        Request request = new Request.Builder()
                .url("https:github.com/login/oauth/acess_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string= null;
            try {
                string = response.body().string();
                System.out.println(string);
                return string;
//                alt+enter处理异常
            } catch (IOException e) {
            }
            return null

        }

    }
    public GithubUser getUser(String acessToken){

    }

}
