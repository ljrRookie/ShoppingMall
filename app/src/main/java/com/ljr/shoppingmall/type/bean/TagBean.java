package com.ljr.shoppingmall.type.bean;

import java.util.List;

/**
 * Created by LinJiaRong on 2017/6/5.
 * TODO：
 */

public class TagBean {

    /**
     * code : 200
     * msg : 璇锋眰鎴愬姛
     * result : [{"name":"灏氱璋�","tag_id":"1"},{"name":"JAVA","tag_id":"2"},{"name":"Android","tag_id":"3"},{"name":"HTML5","tag_id":"4"},{"name":"PHP","tag_id":"5"},{"name":"UI","tag_id":"6"},{"name":"Activity","tag_id":"7"},{"name":"Fragment","tag_id":"8"},{"name":"Button","tag_id":"9"},{"name":"TextView","tag_id":"10"},{"name":"JNI","tag_id":"11"},{"name":"NDK","tag_id":"12"},{"name":"鎵嬫満褰遍煶","tag_id":"13"},{"name":"纭呰胺绀句氦","tag_id":"14"},{"name":"纭呰胺鍟嗗煄","tag_id":"15"},{"name":"纭呰胺閲戣瀺","tag_id":"16"},{"name":"鑷畾涔夋帶浠�","tag_id":"17"},{"name":"纭呰胺","tag_id":"18"},{"name":"OKHttp","tag_id":"19"},{"name":"Volley","tag_id":"20"},{"name":"xUtils","tag_id":"21"},{"name":"Imageloader","tag_id":"22"},{"name":"Glide","tag_id":"23"},{"name":"灏氱璋�","tag_id":"24"},{"name":"WEB鍩虹","tag_id":"25"},{"name":"娣峰悎寮\u20ac鍙�","tag_id":"26"},{"name":"灏氱璋�","tag_id":"27"},{"name":"妫掓杈�","tag_id":"31"},{"name":"涔堜箞鍝�","tag_id":"32"},{"name":"鍛靛懙鍝�","tag_id":"33"}]
     */

    public int code;
    public String msg;
    public List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * name : 灏氱璋�
         * tag_id : 1
         */

        public String name;
        public String tag_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag_id() {
            return tag_id;
        }

        public void setTag_id(String tag_id) {
            this.tag_id = tag_id;
        }
    }
}
