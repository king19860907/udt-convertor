package com.mtc.reader;

import com.mtc.dto.UserDefineField;
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
public class UserDefineFieldReador {

    private String fileLocation = "UserDefineField.xml";

    public List<UserDefineField> getUserDefineFields(){

        InputStream in  = null;
        List<UserDefineField> fields = new ArrayList<UserDefineField>();
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
        UserDefineFieldReador r = new UserDefineFieldReador();
        List<UserDefineField> list = r.getUserDefineFields();
        System.out.println(list);
        System.out.println(list.size());
    }

}
