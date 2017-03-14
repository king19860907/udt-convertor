package com.mtc.postman.dto;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;

/**
 * Created by majun on 10/24/16.
 */
public class Body {

    private String mode;

    private String raw;

    public <T>T getRawObject(Class<T> clazz){
        if(StringUtils.isNotEmpty(this.raw)){
            Gson gson = new Gson();
            T t = gson.fromJson(this.raw,clazz);
            return t;
        }
        return null;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
