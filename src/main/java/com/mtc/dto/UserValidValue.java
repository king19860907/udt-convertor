package com.mtc.dto;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by majun on 10/22/16.
 */
@SuppressWarnings("Duplicates")
public class UserValidValue {

    private String fileLocation = "UDFValidValues.xml";

    private String tableId;

    private Integer fieldID;

    private Integer indexID;

    private String fldValue;

    private String descr;

    public List<UserValidValue> getValidValues(){

        InputStream in  = null;
        List<UserValidValue> values = new ArrayList<UserValidValue>();
        try{
            SAXReader reader = new SAXReader();
            in = this.getClass().getClassLoader().getResourceAsStream(fileLocation);
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> childNodes = root.elements();

            values = childNodes.stream().map(element -> {
                UserValidValue value = new UserValidValue();
                value.setFieldID(Integer.parseInt(element.attribute("FieldID").getData().toString()));
                value.setIndexID(Integer.parseInt(element.attribute("IndexID").getData().toString()));
                value.setDescr(element.attribute("Descr").getData().toString());
                value.setTableId(element.attribute("TableID").getData().toString());
                value.setFldValue(element.attributeValue("FldValue"));
                return value;
            }).collect(Collectors.toList());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return values;
    }

    public Map<String,List<UserValidValue>> getValidValuesMap(){
        Map<String,List<UserValidValue>> map = new LinkedHashMap<>();
        List<UserValidValue> values = this.getValidValues();

        for(UserValidValue validValue : values){
            String key = validValue.getTableId()+"-"+validValue.getFieldID();
            List<UserValidValue> list = map.get(key);
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(validValue);
            map.put(key,list);
        }

        return map;
    }

    public static void main(String[] args) {
        UserValidValue value = new UserValidValue();
        List<UserValidValue> values = value.getValidValues();
        System.out.println(values);
        System.out.println(value.getValidValuesMap());
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getFieldID() {
        return fieldID;
    }

    public void setFieldID(Integer fieldID) {
        this.fieldID = fieldID;
    }

    public String getFldValue() {
        return fldValue;
    }

    public void setFldValue(String fldValue) {
        this.fldValue = fldValue;
    }

    public Integer getIndexID() {
        return indexID;
    }

    public void setIndexID(Integer indexID) {
        this.indexID = indexID;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "UserValidValue{" +
                "descr='" + descr + '\'' +
                ", tableId='" + tableId + '\'' +
                ", fieldID=" + fieldID +
                ", indexID=" + indexID +
                ", fldValue='" + fldValue + '\'' +
                '}';
    }
}
