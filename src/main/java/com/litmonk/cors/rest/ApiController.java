package com.litmonk.cors.rest;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by lu on 2017/3/29.
 */
@RestController
public class ApiController {
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public void login(HttpServletRequest req, HttpServletResponse resp){
        Cookie cookie = new Cookie("Auth", "test");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
    }
}
