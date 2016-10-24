package com.mtc;

import com.google.gson.Gson;
import com.mtc.dto.UserDefineField;
import com.mtc.dto.UserValidValue;
import com.mtc.postman.dto.Item;
import com.mtc.postman.dto.ItemLine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by majun on 10/21/16.
 */
public class Test {

    public static void main(String[] args) {

        UserDefineField field = new UserDefineField();
        UserValidValue value = new UserValidValue();

        List<UserDefineField> fields = field.getUserDefineFields();
        Map<String,List<UserValidValue>> validValueMap = value.getValidValuesMap();

        List<ItemLine> itemLines = fields.stream().map(userDefineField->{
            String key = userDefineField.getTableId()+"-"+userDefineField.getFieldId();
            ItemLine itemLine = new ItemLine(userDefineField,validValueMap.get(key));
            return itemLine;
        }).collect(Collectors.toList());

        Item item = new Item();
        item.setItem(itemLines);

        Gson gson = new Gson();
        String str = gson.toJson(item);
        System.out.println(str);
    }

}
