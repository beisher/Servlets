package com.tms;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class CarServletListener implements HttpSessionListener , ServletRequestListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session created: ID = " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session destroyed: ID = " + se.getSession().getId());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request destroyed : " + sre.getServletRequest().getLocalName());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("req initialized : " + sre.getServletRequest().getLocalName());
    }
}