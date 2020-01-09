package ai.ssy.service;


import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    String createToken();

    String checkToken(HttpServletRequest request);

}
