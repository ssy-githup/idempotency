package ai.ssy.controller;

import ai.ssy.annotation.ApiIdempotent;
import ai.ssy.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
/**
 * @program: idempotency
 * @description: 测试token
 * @author: ssy
 * @create: 2020-01-09 10:05
 **/
@RestController
@RequestMapping("/test")
public class TestTokenController {

    @Autowired
    private TokenService tokenService;

    @ApiIdempotent
    @PostMapping("testIdempotence")
    public String testIdempotence(HttpServletRequest request) {
        return tokenService.checkToken(request);
    }

}