package com.example.demo.utils;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.HashMap;
import java.util.Map;

public class JsonResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private static final String SUCCEED = "succeed";
    private static final String STATUS = "status";
    private static final String MESSAGE = "msg";
    public static final boolean OPERATE_SUCCEED = true;
    public static final boolean OPERATE_FAILURE = false;
    public static final String MSG_AUTH_SUCCEED = "auth:succeed";
    public static final String MSG_AUTH_LOGIN_ERROR = "auth:failed";
    public static final String MSG_AUTH_UNAUTHORIZED = "auth:unauthorized";
    public static final String MSG_AUTH_FORBIDDEN = "auth:forbidden";
    public static final String MSG_INTERNAL_UNKNOWN_ERROR = "internal:unknown_error";
    public static final int SC_OK = 200;
    public static final int SC_BAD_REQUEST = 400;
    public static final int SC_UNAUTHORIZED = 401;
    public static final int SC_FORBIDDEN = 403;
    public static final int SC_NOT_FOUND = 404;
    public static final int SC_INTERNAL_SERVER_ERROR = 500;

    public JsonResult() {
    }

    public static JsonResult build() {
        return new JsonResult();
    }

    public static JsonResult ok() {
        JsonResult baseReturn = new JsonResult();
        baseReturn.put("succeed", true);
        baseReturn.put("status", 200);
        return baseReturn;
    }

    public static JsonResult ok(String message) {
        JsonResult baseReturn = new JsonResult();
        baseReturn.put("succeed", true);
        baseReturn.put("status", 200);
        baseReturn.put("msg", message);
        return baseReturn;
    }

    public static JsonResult ok(int status, String message) {
        JsonResult baseReturn = new JsonResult();
        baseReturn.put("succeed", true);
        baseReturn.put("status", status);
        baseReturn.put("msg", message);
        return baseReturn;
    }

    public static JsonResult fail() {
        JsonResult baseReturn = new JsonResult();
        baseReturn.put("succeed", false);
        baseReturn.put("status", 500);
        return baseReturn;
    }

    public static JsonResult fail(String message) {
        JsonResult baseReturn = new JsonResult();
        baseReturn.put("succeed", false);
        baseReturn.put("status", 500);
        baseReturn.put("msg", message);
        return baseReturn;
    }

    public static JsonResult fail(int status, String message) {
        JsonResult baseReturn = new JsonResult();
        baseReturn.put("succeed", false);
        baseReturn.put("status", status);
        baseReturn.put("msg", message);
        return baseReturn;
    }

    public JsonResult respond(boolean respond) {
        this.put("succeed", respond);
        return this;
    }

    public JsonResult message(String message) {
        this.put("msg", message);
        return this;
    }

    public JsonResult add(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public JsonResult addAll(Map<String, ?> map) {
        this.putAll(map);
        return this;
    }
}
