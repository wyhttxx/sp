package com.example.demo.provider;
import com.alibaba.fastjson.JSON;
import com.example.demo.dto.AcessTokenDTO;
import com.example.demo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
//alt+enter引入jar包
@Component
public class GithubProvider {
    public String getAcessToken(AcessTokenDTO acessTokenDTO) {
        MediaType mediaType
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(acessTokenDTO));
        Request request = new Request.Builder()
//                url哪里打错里，会提示出现cookie问题
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            String token=string.split("&")[0].split("=")[1];
            return token;
//                alt+enter处理异常
        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }
        public GithubUser getUser (String accessToken){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user")
                    .header("Authorization", "token "+accessToken)
                    .build();

            try  {
                Response response = client.newCall(request).execute();
//                okhttp3.internal.http.RealResponseBody要用string()
                String string=response.body().string();
                System.out.println(string);
//                command alt v
                GithubUser githubUser = JSON.parseObject(string, GithubUser.class);

                return githubUser;
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

}
