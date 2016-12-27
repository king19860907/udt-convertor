package com.mtc;

import com.mtc.dao.BaseDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by majun on 11/10/16.
 */
public class ImpartAndExprotDataTest2 {


    //private BaseDao baseDaoBySqlServer;

    private BaseDao baseDaoByHana;

    public ImpartAndExprotDataTest2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        //baseDaoBySqlServer = (BaseDao)context.getBean("baseDaoBySqlServer");
        baseDaoByHana = (BaseDao)context.getBean("baseDaoByHana");
    }


    public static void main(String[] args) {
        //String tableName = "OCNT";
        String suffix = "SBO_AG_FARM";
        List<String> excludeTables = Arrays.asList("ONNM","NNM1","NNM2","NNM3","NNM4","NNM5","NNM6");
        
        ImpartAndExprotDataTest2 test = new ImpartAndExprotDataTest2();
       // List<String> tableNames = test.getAllTalbeNames();
       // List<String> subTableNames = tableNames.subList(tableNames.indexOf("U_SURP2")+1,tableNames.size());
       // for(String talbeName : subTableNames){
       // 	if(!excludeTables.contains(talbeName)){
       // 		System.out.println("index:"+tableNames.indexOf(talbeName));
        //        System.out.println("tableName:"+talbeName);
         //       try{
          //          test.copyDate(talbeName,suffix);
           //     }catch (Exception e){
            //        System.out.println(e.getMessage());
             //   }
        //	}else{
        //		System.out.println("排除的表："+talbeName);
        //	}
        //}

        String [] tables = new String[]{
                "@U_AOCND","@U_AOIDB","@U_AOPRM","@U_AOUSP","@U_CCIT1","@U_CITM1","@U_COCIT","@U_COITM","@U_FCTR1","@U_FJTY1",
                "@U_FOAJT","@U_FOCTR","@U_FOJTY","@U_FOTLR","@U_IICL10","@U_IICL2","@U_IICL3","@U_IICL4","@U_IICL5","@U_IICL6",
                "@U_IICL7","@U_IICL8","@U_IICL9","@U_IOTRN","@U_ITRN1","@U_PKTY1","@U_PKTY2","@U_POBRL","@U_POBST","@U_POFIN",
                "@U_POFOT","@U_POKTY","@U_POMED","@U_POMLV","@U_POPLT","@U_POPRO","@U_PPLT1","@U_SBC21","@U_SBCL2","@U_SBCL3",
                "@U_SBCL4","@U_SBCL5","@U_SORIN","@U_SORPL","@U_SOTCT","@U_EECCTR","@U_EEEMP","@U_EEGLACCT","@U_FFAD1","@U_FFAT1",
                "@U_FOFAC","@U_FOFAD","@U_FOFAT","@U_PCST1","@U_POCSI","@U_POCST","@U_POPAN","@U_POPQA","@U_POPRM","@U_POTBP",
                "@U_PPAN1","@U_PPAN2","@U_PPQA1","@U_PPQA1"
        };

        for(String tableName : tables){
            test.copyDate(tableName,suffix);;
        }

        //test.copyDate("@U_ITRN1",suffix);
    }

    public List<String> getAllTalbeNames(){
       return baseDaoByHana.findAllTables();
    }

    public void copyDate(String tableName,String suffix){
        String sqlTableName = "";
        if(tableName.contains("@U_")){
            sqlTableName = ""+tableName+"";
        }else{
            sqlTableName = tableName;
        }
        List<Map<String,Object>> list=baseDaoByHana.findResultHana(sqlTableName,suffix);

        String hanaTableName = "";
        if(tableName.contains("@U_")){
            hanaTableName = tableName.replaceAll("@U_", "@MTC_EF_");
        }else{
            hanaTableName = tableName;
        }
        final String hanaTableName2 = hanaTableName;
        baseDaoByHana.deleteResult(suffix, hanaTableName2);
        System.out.println(list.size());
        list.stream().map(column->{
            try{
                baseDaoByHana.insertResult(suffix, hanaTableName2, column);
            }catch(Exception e){
                System.out.println("error:"+e.getMessage());
            }
            return null;
        }).collect(Collectors.toList());

        System.out.println("success");
    }

}
