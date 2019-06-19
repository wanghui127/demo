package com.example.demo.utils;

import com.alibaba.fastjson.JSON;

public abstract class JsonResultUtil {

    protected JsonResult successRender() {
        return JsonResult.ok().add("code", 0);
    }

    protected JsonResult successRender(String msg) {
        return JsonResult.ok().add("code", 0).message(msg);
    }

    protected JsonResult failRender() {
        return JsonResult.fail().add("code", 1);
    }

    protected JsonResult failRender(String msg) {
        return JsonResult.fail().add("code", 1).message(msg);
    }

    protected JsonResult checkSuccessRender() {
        return JsonResult.ok().add("result", Boolean.TRUE);
    }

    protected JsonResult checkFailRender() {
        return JsonResult.ok().add("result", Boolean.FALSE);
    }

    protected JsonResult checkRender(boolean result) {
        return result ? this.checkSuccessRender() : this.checkFailRender();
    }

    protected String toJson(Object object) {
        return JSON.toJSONString(object);
    }
}
