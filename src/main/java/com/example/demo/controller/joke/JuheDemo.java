package com.example.demo.controller.joke;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 笑话大全调用示例代码 － 聚合数据
 * 在线接口文档：http://www.juhe.cn/docs/95
 **/

public class JuheDemo {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY = "d0c8c5450ab8804ba18d450ab2a13585";

    //1.按更新时间查询笑话
    public static void getRequest1() {
        String result = null;
        String url = "http://v.juhe.cn/joke/content/list.php";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("sort", "asc");//类型，desc:指定时间之前发布的，asc:指定时间之后发布的
        params.put("page", "1");//当前页数,默认1
        params.put("pagesize", "10");//每次返回条数,默认1,最大20
        params.put("time", "1418816972");//时间戳（10位），如：1418816972
        params.put("key", APPKEY);//您申请的key

        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.最新笑话
    public static void getRequest2() {
        String result = null;
        String url = "http://v.juhe.cn/joke/content/text.php";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("page", "");//当前页数,默认1
        params.put("pagesize", "20");//每次返回条数,默认1,最大20
        params.put("key", APPKEY);//您申请的key

        try {
            result = net(url, params, "GET");
            System.err.println(result);
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                //Object resultList = object.get("result");

                System.out.println(JSONObject.fromObject(object.get("result")).getJSONArray("data"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.按更新时间查询趣图
    /*public static void getRequest3() {
        String result = null;
        String url = "http://japi.juhe.cn/joke/img/list.from";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("sort", "");//类型，desc:指定时间之前发布的，asc:指定时间之后发布的
        params.put("page", "");//当前页数,默认1
        params.put("pagesize", "");//每次返回条数,默认1,最大20
        params.put("time", "1418816972000");//时间戳（10位），如：1418816972
        params.put("key", APPKEY);//您申请的key

        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //4.最新趣图
    /*public static void getRequest4() {
        String result = null;
        String url = "http://japi.juhe.cn/joke/img/text.from";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("page", "");//当前页数,默认1
        params.put("pagesize", "");//每次返回条数,默认1,最大20
        params.put("key", APPKEY);//您申请的key

        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //2.随机笑话
    public static void getRequest5() {
        String result = null;
        String url = "http://v.juhe.cn/joke/randJoke.php";//请求接口地址
        Map params = new HashMap();//请求参数
        /*params.put("page", "");//当前页数,默认1
        params.put("pagesize", "");//每次返回条数,默认1,最大20*/
        params.put("key", APPKEY);//您申请的key

        try {
            result = net(url, params, "GET");
            System.err.println(result);
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getRequest5();
    }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}