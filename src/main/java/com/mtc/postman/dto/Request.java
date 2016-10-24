package com.mtc.postman.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by majun on 10/24/16.
 */
public class Request {

    private String url;

    private String method;

    private List<Object> header = new ArrayList<>();

    private Body body;

    private String description="";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Object> getHeader() {
        return header;
    }

    public void setHeader(List<Object> header) {
        this.header = header;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
