package com.bh.ldp.lib_base.http;

/**
 * @author mini
 * @date 2019/10/15
 */
public abstract class BaseRequest {

    private String netUrl;
    private Class parseClass = null;
    private Object tag;

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    public Class getParseClass() {
        return parseClass;
    }

    public void setParseClass(Class parseClass) {
        this.parseClass = parseClass;
    }
}
