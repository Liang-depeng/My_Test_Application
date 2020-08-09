package com.bh.ldp.lib_base;

/**
 * @author mini
 * @date 2019/10/17
 */
public class Contants {

    public final static String MSG = "msg";
    public final static String NODATA = "没有查询到相关数据";

    public static enum HttpError {

        // 系统错误码 test
        NULLAPPKEY(201, "APPKEY为空或不存在");

        private int errorId;

        private String errorMsg;

        private HttpError(int errorId, String errorMsg) {
            this.errorId = errorId;
            this.errorMsg = errorMsg;
        }

        public int getErrorId() {
            return errorId;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }
}


//API错误码：
//        代号	说明
//        201 	关键词为空
//        202 	分类ID为空
//        203 	详情ID为空
//        205 	没有信息
//        系统错误码：
//        代号	说明
//        101 	APPKEY为空或不存在
//        102 	APPKEY已过期
//        103 	APPKEY无请求此数据权限
//        104 	请求超过次数限制
//        105 	IP被禁止
//        106 	IP请求超过限制
//        107 	接口维护中
//        108 	接口已停用
