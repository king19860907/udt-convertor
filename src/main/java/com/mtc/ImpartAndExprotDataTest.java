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
	
	public final static String tableName = "U_PYWOR1";
	
	public final static String suffix = "SBODEMOUS_TABLE";

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        BaseDao baseDaoBySqlServer = (BaseDao)context.getBean("baseDaoBySqlServer");
        BaseDao baseDaoByHana = (BaseDao)context.getBean("baseDaoByHana");
        
        List<Map<String,Object>> list=baseDaoBySqlServer.findResult(tableName);
        System.out.println(list.size());
        list.stream().map(column->{
        	try{
                baseDaoByHana.insertResult(suffix, tableName, column);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	return null;
        }).collect(Collectors.toList());
     
        System.out.print("success");
    }

}
