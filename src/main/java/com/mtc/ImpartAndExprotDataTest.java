package com.mtc;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mtc.dao.BaseDao;

/**
 * Created by majun on 11/10/16.
 */
public class ImpartAndExprotDataTest {
	
	public final static String tableName = "OCNT";
	
	public final static String suffix = "SBODEMOUS";

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        BaseDao baseDaoBySqlServer = (BaseDao)context.getBean("baseDaoBySqlServer");
        BaseDao baseDaoByHana = (BaseDao)context.getBean("baseDaoByHana");
        
        String sqlTableName = "";
        if(tableName.contains("@U_")){
        	sqlTableName = "["+tableName+"]";
        }else{
        	sqlTableName = tableName;
        }
        List<Map<String,Object>> list=baseDaoBySqlServer.findResult(sqlTableName);
        System.out.println(list.size());
        list.stream().map(column->{
        	try{
        		String hanaTableName = "";
        		if(tableName.contains("@U_")){
        			hanaTableName = tableName.replaceAll("@U_", "@MTC_EF_");
        		}else{
        			hanaTableName = tableName;
        		}
                baseDaoByHana.insertResult(suffix, hanaTableName, column);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	return null;
        }).collect(Collectors.toList());
     
        System.out.print("success");
    }

}
