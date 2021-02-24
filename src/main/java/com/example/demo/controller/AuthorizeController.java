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
        AcessTokenDTO acessTokenDTO = new AcessTokenDTO();
        githubProvider.getAcessToken(acessTokenDTO);
        return "index";
    }
}

