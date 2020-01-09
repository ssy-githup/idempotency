package ai.ssy.controller;

import ai.ssy.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gettoken")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public String token() {
        return tokenService.createToken();
    }

}
