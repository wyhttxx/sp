package com.example.demo.controller;

import com.example.demo.dto.AcessTokenDTO;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String Callback(@RequestParam(name = "code") String code,
                           @RequestParam(name="state") String state
    ){
//        commond alt v
//        shift enter
        AcessTokenDTO acessTokenDTO = new AcessTokenDTO();
        acessTokenDTO.setClient_id("5162d050da0daafd0f0a");
        acessTokenDTO.setClient_secret("9619af3b58aa97ac1c1bd87a1f2f73851cc96208");
        acessTokenDTO.setCode(code);
        acessTokenDTO.setRedirect_uri("http:127.0.0.1:8887/callback");
        acessTokenDTO.setState(state);


        githubProvider.getAcessToken(acessTokenDTO);
        return "index";
    }
}

