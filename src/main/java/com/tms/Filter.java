package com.tms;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = "/car")
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String method = req.getMethod();

        if (!method.equalsIgnoreCase("get")) {
            res.sendError(403, "ERROR");
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}