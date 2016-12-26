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
public class ImpartAndExprotDataTest {
	

    private BaseDao baseDaoBySqlServer;

    private BaseDao baseDaoByHana;

    public ImpartAndExprotDataTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        baseDaoBySqlServer = (BaseDao)context.getBean("baseDaoBySqlServer");
        baseDaoByHana = (BaseDao)context.getBean("baseDaoByHana");
    }


    public static void main(String[] args) {
        //String tableName = "OCNT";
        String suffix = "SBODEMOUS";
        List<String> excludeTables = Arrays.asList("ONNM","NNM1","NNM2","NNM3","NNM4","NNM5","NNM6");
        
        ImpartAndExprotDataTest test = new ImpartAndExprotDataTest();
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

        test.copyDate("@U_ITRN1",suffix);
    }

    public List<String> getAllTalbeNames(){
       return baseDaoBySqlServer.findAllTables();
    }

    public void copyDate(String tableName,String suffix){
        String sqlTableName = "";
        if(tableName.contains("@U_")){
            sqlTableName = "["+tableName+"]";
        }else{
            sqlTableName = tableName;
        }
        List<Map<String,Object>> list=baseDaoBySqlServer.findResult(sqlTableName);

        String hanaTableName = "";
        if(tableName.contains("@U_")){
            hanaTableName = tableName.replaceAll("@U_", "@MTC_EF_");
        }else{
            hanaTableName = tableName;
        }
        final String hanaTableName2 = hanaTableName;
        //baseDaoByHana.deleteResult(suffix, hanaTableName2);
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
