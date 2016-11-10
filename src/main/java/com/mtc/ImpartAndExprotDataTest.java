package com.mtc;

import com.mtc.dao.BaseDao;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by majun on 11/10/16.
 */
public class ImpartAndExprotDataTest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        BaseDao baseDaoBySqlServer = (BaseDao)context.getBean("baseDaoBySqlServer");
        List<Map<String,Object>> list=baseDaoBySqlServer.findResult("U_PYWOR1");
        System.out.println(list);
    }

}
