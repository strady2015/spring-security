package com.strady.springsecurity.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: strady
 * @Date: 2019-08-16
 * @Time: 10:53:42
 * @Description: 请求返回结果操作类
 */
public class WebResult {

    /**
     * 返回的状态码
     */
    private Integer __code;

    /**
     * 返回的状态信息
     */
    private String __msg;

    /**
     * 返回值结果集
     */
    private Map<String, Object> __attrs = new HashMap<String, Object>();

    /**
     * 实例化对象
     *
     * @return
     */
    public static WebResult create() {
        return new WebResult();
    }

    /**
     * 实例化对象，并设为success
     *
     * @return
     */
    public static WebResult SUCCESS() {
        return new WebResult(1);
    }

    /**
     * 接受code，实例化对象
     *
     * @param code
     * @return
     */
    public static WebResult CODE(int code) {
        return new WebResult(code);
    }

    protected WebResult() {
    }

    protected WebResult(Integer __code) {
        this.__code = __code;
    }

    /**
     * 传入状态码
     *
     * @param code
     * @return
     */
    public WebResult code(Integer code) {
        __code = code;
        return this;
    }

    /**
     * 传入错误信息
     *
     * @param msg
     * @return
     */
    public WebResult msg(String msg) {
        __msg = msg;
        return this;
    }

    /**
     * 传入数据（对象）
     *
     * @param data
     * @return
     */
    public WebResult data(Object data) {
        __attrs.put("data", data);
        return this;
    }

    /**
     * 传入数据（键值对）
     *
     * @param attrKey
     * @param attrValue
     * @return
     */
    public WebResult attr(String attrKey, Object attrValue) {
        __attrs.put(attrKey, attrValue);
        return this;
    }

    /**
     * 将对象转换为json
     *
     * @return
     */
    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        if (__code != null) {
            jsonObject.put("ret", __code);
        }
//        if (StringUtils.isNotBlank(__msg)) {
//            jsonObject.put("msg", __msg);
//        }
        if (!StringUtils.isEmpty(__msg)) {
            jsonObject.put("msg", __msg);
        }
        if (__attrs != null) {
            jsonObject.putAll(__attrs);
        }
        return jsonObject.toString();
    }
}
