package com.mtc.postman.dto;

import com.google.gson.Gson;
import com.mtc.dto.UserDefineField;
import com.mtc.dto.UserValidValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by majun on 10/24/16.
 */
public class ItemLine {

    private String name;

    private List<Object> response = new ArrayList<>();

    private Request request;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getResponse() {
        return response;
    }

    public void setResponse(List<Object> response) {
        this.response = response;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public ItemLine(UserDefineField field, List<UserValidValue> values){

        this.name = field.getAliasId();
        this.request = new Request();
        this.request.setUrl("http://192.168.0.211:50000/b1s/v1/UserFieldsMD");
        this.request.setMethod("POST");
        Body body = new Body();
        body.setMode("raw");
        Raw raw = new Raw();
        raw.setFieldID(field.getFieldId());
        //TODO
        raw.setDefaultValue(field.getDflt());
        raw.setDescription(field.getDescr());
        raw.setEditSize(field.getEditSize());
        //TODO
        raw.setLinkedSystemObject(null);
        //TODO
        raw.setLinkedTable(field.getrTable().trim());
        if(values != null){
            List<ValidValuesMD> mds = values.stream().map(value->{
                ValidValuesMD md = new ValidValuesMD();
                md.setDescription(value.getDescr());
                md.setValue(value.getFldValue());
                return md;
            }).collect(Collectors.toList());
            raw.setValidValuesMD(mds);
        }
        //TODO
        raw.setLinkedUDO(field.getRelUDO().trim());
        raw.setType(field.getTypeId());
        raw.setTableName(field.getTableId());
        raw.setSubType(field.getEditType().trim());
        raw.setName(field.getAliasId());
        //TODO
        raw.setMandatory(field.getNotNull().trim());
        Gson gson = new Gson();
        body.setRaw(gson.toJson(raw));
        this.request.setBody(body);


    }

}
