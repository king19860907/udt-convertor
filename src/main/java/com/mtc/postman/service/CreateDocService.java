package com.mtc.postman.service;

import com.mtc.postman.dto.Item;
import com.mtc.postman.dto.ItemLine;
import com.mtc.postman.dto.Raw;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by majun on 3/14/17.
 */
public class CreateDocService {

    private ReadJsonService readJsonService;

    private Configuration configuration;

    private String srcPath;

    private String destPath;

    private String fileName;

    public CreateDocService(){
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public void createDoc(){
        Map<String,Object> data = new HashMap<String,Object>();
        Item item = readJsonService.toObject(Item.class);

        List<Raw> rows = item.getItem().stream().map(itemLine -> {
            return itemLine.getRequest().getBody().getRawObject(Raw.class);
        }).collect(Collectors.toList());
        /*for(ItemLine itemLine:item.getItem()){
            Raw raw = itemLine.getRequest().getBody().getRawObject(Raw.class);
            data.put("tableName",raw.getTableName());
            data.put("tableDescription",raw.getTableDescription());
            data.put("tableType",raw.getTableType());
            break;
        }*/
        data.put("items",rows);

        configuration.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(),srcPath);

        Template t = null;
        try {
            // test.ftl为要装载的模板
            t = configuration.getTemplate(fileName);

            t.setEncoding("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 输出文档路径及名称
        File outFile = new File(destPath);
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            t.process(data, out);
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        CreateDocService service = new CreateDocService();
        service.setReadJsonService(new ReadJsonService("/postman-json/udf.json"));
        service.setSrcPath("/doc-template/");
        service.setDestPath("udf.doc");
        service.setFileName("udf.ftl");

        service.createDoc();
    }

    public void setReadJsonService(ReadJsonService readJsonService) {
        this.readJsonService = readJsonService;
    }

    public void setDestPath(String destPath) {
        this.destPath = ClassLoader.getSystemResource("").getPath()+destPath;
    }

    public void setSrcPath(String srcPath) {
        //this.srcPath = ClassLoader.getSystemResource("").getPath()+srcPath;
        this.srcPath = srcPath;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
