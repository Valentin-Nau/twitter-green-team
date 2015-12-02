package com.m2i.poec.twittergreen.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.m2i.poec.twittergreen.bean.UserCreateBean;
import com.m2i.poec.twittergreen.bean.UserLoginBean;
import com.m2i.poec.twittergreen.entity.Users;

@WebFilter("/Profil.xhtml")
public class LoginFilter implements Filter {
    private FilterConfig fc;
    
    private static final Logger LOGGER = Logger.getLogger(UserCreateBean.class.getName());

    public LoginFilter() {

    }

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String user = (String) request.getSession().getAttribute("user");
        
        String loginURL = request.getContextPath() + "/Login.xhtml";
        if(user != null){
            chain.doFilter(req, res);
        }
        else{
        	response.sendRedirect(loginURL);
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        this.fc = fConfig;
    }
}