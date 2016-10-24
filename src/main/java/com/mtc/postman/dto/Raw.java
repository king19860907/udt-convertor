package com.mtc.postman.dto;

import java.util.List;

/**
 * Created by majun on 10/24/16.
 */
public class Raw {

    private String Name;

    private String Type;

    private Integer Size;

    private String Description;

    private String SubType;

    private String LinkedTable;

    private String DefaultValue;

    private String TableName;

    private Integer FieldID;

    private Integer EditSize;

    private String Mandatory;

    private String LinkedUDO;

    private String LinkedSystemObject;

    private List<ValidValuesMD> ValidValuesMD;

    public String getDefaultValue() {
        return DefaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        DefaultValue = defaultValue;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getEditSize() {
        return EditSize;
    }

    public void setEditSize(Integer editSize) {
        EditSize = editSize;
    }

    public Integer getFieldID() {
        return FieldID;
    }

    public void setFieldID(Integer fieldID) {
        FieldID = fieldID;
    }

    public String getLinkedSystemObject() {
        return LinkedSystemObject;
    }

    public void setLinkedSystemObject(String linkedSystemObject) {
        LinkedSystemObject = linkedSystemObject;
    }

    public String getLinkedTable() {
        return LinkedTable;
    }

    public void setLinkedTable(String linkedTable) {
        LinkedTable = linkedTable;
    }

    public String getLinkedUDO() {
        return LinkedUDO;
    }

    public void setLinkedUDO(String linkedUDO) {
        LinkedUDO = linkedUDO;
    }

    public String getMandatory() {
        return Mandatory;
    }

    public void setMandatory(String mandatory) {
        Mandatory = mandatory;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getSize() {
        return Size;
    }

    public void setSize(Integer size) {
        Size = size;
    }

    public String getSubType() {
        return SubType;
    }

    public void setSubType(String subType) {
        SubType = subType;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public List<com.mtc.postman.dto.ValidValuesMD> getValidValuesMD() {
        return ValidValuesMD;
    }

    public void setValidValuesMD(List<com.mtc.postman.dto.ValidValuesMD> validValuesMD) {
        ValidValuesMD = validValuesMD;
    }
}
