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
        String oldTableName = field.getTableId();
        String newTableName = null;
        if(oldTableName.contains("@AU_")){
            newTableName = "@AMTC_EF_"+oldTableName.replaceAll("@AU_","");
        }else if(oldTableName.contains("@U_")){
            newTableName = "@MTC_EF_"+oldTableName.replaceAll("@U_","");
        }else{
            newTableName = oldTableName;
        }
        this.name = newTableName+"-"+field.getAliasId();
        this.request = new Request();
        this.request.setUrl("http://192.168.0.211:50000/b1s/v1/UserFieldsMD");
        this.request.setMethod("POST");
        Body body = new Body();
        body.setMode("raw");
        Raw raw = new Raw();
        raw.setFieldID(field.getFieldId());
        raw.setDefaultValue(field.getDflt());
        raw.setDescription(field.getDescr());
        raw.setEditSize(field.getEditSize());
        raw.setLinkedSystemObject(null);
        String oldRelateTable = field.getrTable().trim();
        String newRelateTable = "";
        if(oldRelateTable.contains("AU_")){
            newRelateTable = "AMTC_EF_"+oldRelateTable.replaceAll("AU_","");
        }else if(oldRelateTable.contains("U_")){
            newRelateTable = "MTC_EF_"+oldRelateTable.replaceAll("U_","");
        }else{
            newRelateTable = oldRelateTable;
        }
        raw.setLinkedTable(newRelateTable);
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
        raw.setTableName(newTableName);
        raw.setSubType(field.getEditType().trim());
        raw.setName(field.getAliasId());
        //TODO
        raw.setMandatory(field.getNotNull().trim());
        Gson gson = new Gson();
        body.setRaw(gson.toJson(raw));
        this.request.setBody(body);


    }

}
