package com.mtc.dto;

/**
 * Created by majun on 10/21/16.
 */
public class UserDefineField {

    private String tableId;

    private Integer fieldId;

    private String aliasId;

    private String descr;

    private String typeId;

    private String editType;

    private Integer sizeId;

    private Integer editSize;

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

    @Override
    public String toString() {
        return "UserDefineField{" +
                "aliasId='" + aliasId + '\'' +
                ", tableId='" + tableId + '\'' +
                ", fieldId=" + fieldId +
                ", descr='" + descr + '\'' +
                ", typeId='" + typeId + '\'' +
                ", editType='" + editType + '\'' +
                ", sizeId=" + sizeId +
                ", editSize=" + editSize +
                '}';
    }
}
