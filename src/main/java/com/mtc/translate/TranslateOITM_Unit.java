package com.mtc.translate;

import com.mtc.dao.BaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by majun on 5/18/17.
 */
@SuppressWarnings("Duplicates")
public class TranslateOITM_Unit {

    private BaseDao baseDaoByHana;

    private Map<String,String> translateMap = new HashMap<>();

    private Map<String,String> replaceMap = new HashedMap();

    private String table_name = "OITM";

    // "BuyUnitMsr","SalUnitMsr","InvntryUom"
    private String column_name="InvntryUom";

    public TranslateOITM_Unit(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        //baseDaoBySqlServer = (BaseDao)context.getBean("baseDaoBySqlServer");
        baseDaoByHana = (BaseDao)context.getBean("baseDaoByHana");

        replaceMap.put("一","1");
        replaceMap.put("二","2");
        replaceMap.put("三","3");
        replaceMap.put("四","4");
        replaceMap.put("五","5");
        replaceMap.put("六","6");
        replaceMap.put("七","7");
        replaceMap.put("八","8");
        replaceMap.put("九","9");
        replaceMap.put("十","10");


        translateMap.put("瓶","PCS");
        translateMap.put("只","PCS");
        translateMap.put("桶","PCS");
        translateMap.put("片","PCS");
        translateMap.put("个","PCS");
        translateMap.put("袋","PCS");
        translateMap.put("元","PCS");
        translateMap.put("支","PCS");
    }

    public void translate(){
        List<Map<String,Object>> list = baseDaoByHana.findResultHana(table_name,"SBO_AG_FARM");
        for(String key : translateMap.keySet()){
            String express = translateMap.get(key);
            translate(list,key,express);
        }
    }

    private void translate(List<Map<String,Object>> list,String key,String express){
        list.stream().forEach(map->{
            String beforeFarmName = map.get(column_name) == null ? "":map.get(column_name).toString();
            if(contains(beforeFarmName,key)){
                String afterFarmName = replace(beforeFarmName,express);
                System.out.println("before:"+beforeFarmName + "  after:"+afterFarmName);
                Map<String,Object> setMap = new HashMap<>();
                setMap.put(column_name,afterFarmName);

                Map<String,Object> whereMap = new HashMap<>();
                whereMap.put("ItemCode",map.get("ItemCode"));
                //System.out.println(setMap);
                //System.out.println(whereMap);
                baseDaoByHana.update("SBO_AG_FARM",table_name,setMap,whereMap);
            }
        });
    }

    private String replace(String before,String express){
        String regex = "[{0-9}一二三四五六七八九十]+";
        Pattern p = Pattern. compile(regex);
        Matcher matcher = p.matcher(before);
        List<String> numbers = new ArrayList<>();
        while (matcher.find()) {
            if(StringUtils.isNumeric(matcher.group(0))){
                numbers.add(matcher.group(0));
            }else{
                numbers.add(replaceMap.get(matcher.group(0)));
            }
        }
        String afterFarmName = null;
        switch (numbers.size()){
            case 0:
                afterFarmName = express;
                break;
            case 1:
                afterFarmName = MessageFormat.format(express,numbers.get(0));
                break;
            case 2:
                afterFarmName = MessageFormat.format(express,numbers.get(0),numbers.get(1));
                break;
            default:
                afterFarmName = MessageFormat.format(express,numbers.get(0),numbers.get(1));
        }
        return afterFarmName;
    }

    private boolean contains(String value,String express){
        Pattern p = Pattern.compile(express);
        Matcher matcher = p.matcher(value);
        return matcher.find();
    }

    public static void main(String[] args) {
        TranslateOITM_Unit oprc = new TranslateOITM_Unit();
        oprc.translate();
        //Pullet青年鸡场1号第1栋
        //Pullet 3# House 1#
    }

}
