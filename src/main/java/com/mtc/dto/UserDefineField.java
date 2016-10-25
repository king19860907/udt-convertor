package com.mtc.dto;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by majun on 10/21/16.
 */
public class UserDefineField {

    private String fileLocation = "UserDefineField.xml";

    private String tableId;

    private Integer fieldId;

    private String aliasId;

    private String descr;

    private String typeId;

    private String editType;

    private Integer sizeId;

    private Integer editSize;

    private String dflt;

    private String notNull;

    private String rTable;

    private String rField;

    private String relUDO;

    public List<UserDefineField> getUserDefineFields(){

        InputStream in  = null;
        List<UserDefineField> fields = new ArrayList<>();
        try{
            SAXReader reader = new SAXReader();
            in = this.getClass().getClassLoader().getResourceAsStream(fileLocation);
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> childNodes = root.elements();
            for(Element ele : childNodes){
                UserDefineField field = new UserDefineField();
                field.setAliasId(ele.attribute("AliasID").getData().toString());
                field.setDescr(ele.attribute("Descr").getData().toString());
                field.setEditSize(Integer.parseInt(ele.attribute("EditSize").getData().toString()));
                field.setEditType(ele.attribute("EditType").getData().toString());
                field.setFieldId(Integer.parseInt(ele.attribute("FieldID").getData().toString()));
                field.setSizeId(Integer.parseInt(ele.attribute("SizeID").getData().toString()));
                field.setTableId(ele.attribute("TableID").getData().toString());
                field.setTypeId(ele.attribute("TypeID").getData().toString());
                field.setDflt(ele.attribute("Dflt").getData().toString());
                field.setNotNull(ele.attribute("NotNull").getData().toString());
                field.setrTable(ele.attribute("RTable").getData().toString());
                field.setrField(ele.attribute("RField").getData().toString());
                field.setRelUDO(ele.attribute("RelUDO").getData().toString());
                fields.add(field);
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
        return fields;
    }

    public static void main(String[] args) {
        UserDefineField r = new UserDefineField();
        List<UserDefineField> list = r.getUserDefineFields();
        System.out.println(list);
        System.out.println(list.size());
    }

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getEditSize() {
        return editSize;
    }

    public void setEditSize(Integer editSize) {
        this.editSize = editSize;
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDflt() {
        return dflt;
    }

    public void setDflt(String dflt) {
        this.dflt = dflt;
    }

    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    public String getRelUDO() {
        return relUDO;
    }

    public void setRelUDO(String relUDO) {
        this.relUDO = relUDO;
    }

    public String getrField() {
        return rField;
    }

    public void setrField(String rField) {
        this.rField = rField;
    }

    public String getrTable() {
        return rTable;
    }

    public void setrTable(String rTable) {
        this.rTable = rTable;
    }

    @Override
    public String toString() {
        return "UserDefineField{" +
                "rField='" + rField + '\'' +
                ", aliasId='" + aliasId + '\'' +
                ", descr='" + descr + '\'' +
                ", dflt='" + dflt + '\'' +
                ", editSize=" + editSize +
                ", editType='" + editType + '\'' +
                ", fieldId=" + fieldId +
                ", fileLocation='" + fileLocation + '\'' +
                ", notNull='" + notNull + '\'' +
                ", relUDO='" + relUDO + '\'' +
                ", rTable='" + rTable + '\'' +
                ", sizeId=" + sizeId +
                ", tableId='" + tableId + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
