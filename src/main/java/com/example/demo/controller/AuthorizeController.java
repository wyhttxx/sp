package com.example.demo.controller;

import com.example.demo.dto.AcessTokenDTO;
import com.example.demo.dto.GithubUser;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String Callback(@RequestParam(name = "code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request
    ){
//        commond alt v
//        shift enter
        AcessTokenDTO acessTokenDTO = new AcessTokenDTO();
        acessTokenDTO.setClient_id(clientId);
        acessTokenDTO.setClient_secret(clientSecret);
        acessTokenDTO.setCode(code);
        acessTokenDTO.setRedirect_uri(redirectUrl);
        acessTokenDTO.setState(state);


        String accessToken = githubProvider.getAcessToken(acessTokenDTO);
//        option+enter引入局部变量
        GithubUser user = githubProvider.getUser(accessToken);
        if(user !=null){
//            登陆成功
            request.getSession().setAttribute("user",user);
            return "redirect:/";

        }else {
            return "redirect:/";
        }
    }
}

