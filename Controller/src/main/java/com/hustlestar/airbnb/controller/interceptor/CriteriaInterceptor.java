package com.hustlestar.airbnb.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yauheni_Malashchytsk on 5/5/2017.
 */

public class CriteriaInterceptor implements HandlerInterceptor {

    public static final String TITLE = "title";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String GUESTS = "guests";
    public static final String APARTMENTS_CRITERIA = "/apartments/criteria/;";
    public static final String EQ = "=";
    public static final String SEMICOLON = ";";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        String title = request.getParameter(TITLE);
        String country = request.getParameter(COUNTRY);
        String city = request.getParameter(CITY);
        String guests = request.getParameter(GUESTS);
        StringBuilder stringBuilder = new StringBuilder(APARTMENTS_CRITERIA);
        if (title != null && !title.isEmpty()) {
            stringBuilder.append(TITLE);
            stringBuilder.append(EQ);
            stringBuilder.append(title);
        }
        stringBuilder.append(SEMICOLON);
        if (country != null && !country.isEmpty()) {
            stringBuilder.append(COUNTRY);
            stringBuilder.append(EQ);
            stringBuilder.append(country);
        }
        stringBuilder.append(SEMICOLON);
        if (city != null && !city.isEmpty()) {
            stringBuilder.append(CITY);
            stringBuilder.append(EQ);
            stringBuilder.append(city);
        }
        stringBuilder.append(SEMICOLON);
        if (guests != null && !guests.isEmpty()) {
            stringBuilder.append(GUESTS);
            stringBuilder.append(EQ);
            stringBuilder.append(guests);
        }
        stringBuilder.append(SEMICOLON);
        System.out.println(stringBuilder);
        response.sendRedirect(stringBuilder.toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

