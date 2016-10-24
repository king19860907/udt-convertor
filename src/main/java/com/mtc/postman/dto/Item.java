package com.mtc.postman.dto;

import com.mtc.dto.UserDefineField;
import com.mtc.dto.UserValidValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by majun on 10/24/16.
 */
public class Item {

    private String name="udf_init";

    private String description="";

    private List<ItemLine> item=new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemLine> getItem() {
        return item;
    }

    public void setItem(List<ItemLine> item) {
        this.item = item;
    }
}
