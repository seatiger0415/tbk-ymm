package com.tbk.ymm.web.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
    private static CookieManager instance;

    public static CookieManager getInstance() {
        if (instance == null)
            synchronized (CookieManager.class) {
                if (instance == null)
                    instance = new CookieManager();
            }

        return instance;
    }

    public String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0)
            return null;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(key))
                return cookies[i].getValue();

        }
        return null;
    }

    public void saveCookie(HttpServletResponse response, String key, String value) {
        this.saveCookie(response, key, value, -1, "/");
    }

    public void saveCookie(HttpServletResponse response, String key, String value, String path) {
        this.saveCookie(response, key, value, -1, path);
    }

    public void saveCookie(HttpServletResponse response, String key, String value, int second, String path) {
        saveCookie(response, key, value, second, path, ".yunmama123.com");
    }
    
    public void saveCookie(HttpServletResponse response, String key, String value, int second, String path, String domain) {
        response.addCookie(createCookie(key, value, second, path, domain, false));
    }

    public void saveCookie(HttpServletResponse response, String key, String value, int second, String path, String domain, boolean secure) {
        response.addCookie(createCookie(key, value, second, path, domain, secure));
    }

    public void clearCookie(HttpServletResponse response, String key, int second, String path) {
        clearCookie(response, key, second, path, ".yunmama123.com");
    }

    public void clearCookie(HttpServletResponse response, String key, int second, String path, String domain) {
        if (key.equals("xng")) {
            new Throwable().printStackTrace(System.out);
        }
        response.addCookie(createCookie(key, null, second, path, domain, false));
    }

    public void expireCookie(HttpServletResponse response, String key, String path, String domain) {
        response.addCookie(createCookie(key, "EXPIRED", 0, path, domain, false));
    }

    private Cookie createCookie(String key, String value, int maxAge, String path, String domain, boolean secure) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        // according to rfc2109, this attribute is optional
        if (secure) {
            cookie.setSecure(true);
        }
        return cookie;
    }
}
