package com.mtc.translate;

import com.mtc.dao.BaseDao;
import com.mtc.util.PingyinUtil;
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
public class TranslateOCRD {

    private BaseDao baseDaoByHana;

    private Map<String,String> translateMap = new HashMap<>();

    private Map<String,String> replaceMap = new HashedMap();

    private String table_name = "OCRD";

    public TranslateOCRD(){
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

        //translateMap.put("鸡苗-细菌蛋","big mai");

    }

    public void translate(){
        List<Map<String,Object>> list = baseDaoByHana.findResultHana(table_name,"SBO_AG_FARM");
        //for(String key : translateMap.keySet()){
            //String express = translateMap.get(key);
            //translate(list,key,express);
            translate(list,null,null);

        //}
    }

    private void translate(List<Map<String,Object>> list,String key,String express){
        list.stream().forEach(map->{
            String beforeFarmName = map.get("CardName") == null ? "":map.get("CardName").toString();
            //if(contains(beforeFarmName,key)){
                //String afterFarmName = replace(beforeFarmName,express);
            if(beforeFarmName.equals("深圳市华测检测技术股份有限公司上海分公司深圳市华测检测技术股份有限公司上海分公司")){
                beforeFarmName = "深圳市华测检测技术股份有限公司上海分公司";
            }
            if(beforeFarmName.equals("安徽省地质实验研究所(国土资源部合肥矿产资源监督检测中心）")){
                beforeFarmName = "安徽省地质实验研究所";
            }
                String afterFarmName = PingyinUtil.getPingYin(beforeFarmName);
                System.out.println("before:"+beforeFarmName + "  after:"+afterFarmName);
                Map<String,Object> setMap = new HashMap<>();
                setMap.put("CardName",afterFarmName);

                Map<String,Object> whereMap = new HashMap<>();
                whereMap.put("CardCode",map.get("CardCode"));
                //System.out.println(setMap);
                //System.out.println(whereMap);
                baseDaoByHana.update("SBO_AG_FARM",table_name,setMap,whereMap);
            //}
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
        TranslateOCRD oprc = new TranslateOCRD();
        oprc.translate();
        //Pullet青年鸡场1号第1栋
        //Pullet 3# House 1#
    }

}
