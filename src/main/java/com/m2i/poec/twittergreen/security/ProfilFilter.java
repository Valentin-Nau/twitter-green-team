package com.m2i.poec.twittergreen.security;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.m2i.poec.twittergreen.entity.User;

@WebFilter("/Profil.xhtml")
public class ProfilFilter implements Filter {

	private FilterConfig fc;

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		User user = (User) request.getSession().getAttribute("user");

		Enumeration<String> parameterNames = req.getParameterNames();
		boolean parameter = true;

		try {
			parameterNames.nextElement();
		} catch (Exception e) {
			parameter = false;
		}

		if (user != null && parameter == false) {

			String loginURL = request.getContextPath() + "/Profil.xhtml?username=" + user.getUsername();
			response.sendRedirect(loginURL);
		} else {

			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fc = fConfig;
	}
}
