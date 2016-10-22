package com.mtc.dto;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
            for(Element ele : childNodes){
                UserValidValue value = new UserValidValue();
                value.setFieldID(Integer.parseInt(ele.attribute("FieldID").getData().toString()));
                value.setIndexID(Integer.parseInt(ele.attribute("IndexID").getData().toString()));
                value.setDescr(ele.attribute("Descr").getData().toString());
                value.setTableId(ele.attribute("TableID").getData().toString());
                value.setFldValue(ele.attributeValue("FldValue"));
                values.add(value);
            }

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

    public static void main(String[] args) {
        UserValidValue value = new UserValidValue();
        List<UserValidValue> values = value.getValidValues();
        System.out.println(values);
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
