package com.mtc;

import com.mtc.dao.BaseDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

        ImpartAndExprotDataTest test = new ImpartAndExprotDataTest();
        /*List<String> tableNames = test.getAllTalbeNames();
        List<String> subTableNames = tableNames.subList(tableNames.indexOf("U_SURP2")+1,tableNames.size());
        for(String talbeName : subTableNames){
        	System.out.println("index:"+tableNames.indexOf(talbeName));
            System.out.println("tableName:"+talbeName);
            try{
                test.copyDate(talbeName,suffix);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
*/
        test.copyDate("OUSR",suffix);
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
        baseDaoByHana.deleteResult(suffix, hanaTableName2);
        System.out.println(list.size());
        list.stream().map(column->{
            try{
                baseDaoByHana.insertResult(suffix, hanaTableName2, column);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        System.out.println("success");
    }

}
