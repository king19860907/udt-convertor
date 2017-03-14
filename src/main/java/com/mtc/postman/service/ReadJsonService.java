package com.mtc.postman.service;

import com.google.gson.Gson;
import com.mtc.postman.dto.Item;
import com.mtc.postman.dto.ItemLine;
import com.mtc.postman.dto.Raw;

import java.io.*;

/**
 * Created by majun on 3/14/17.
 */
public class ReadJsonService {

    private String path ;

    public ReadJsonService(String path){
        this.path = ClassLoader.getSystemResource("").getPath()+path;
    }

    public String readJson(){
        FileInputStream fis = null;
        InputStreamReader isr = null;
        String str1 = "";
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            String str = "";

            //fis = new FileInputStream("/Users/majun/work/idea-java-workspace/udt-convertor/src/main/resources/before/U_PYOWOR.txt");// FileInputStream
            fis = new FileInputStream(path);
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            while ((str = br.readLine()) != null) {
                str1 += str;
            }
            // 当读取的一行不为空时,把读到的str的值赋给str1
           return str1;
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T>T toObject(Class<T> clazz){
        String str = readJson();
        Gson gson = new Gson();
        T t = gson.fromJson(str,clazz);
        return t;
    }

    public String getPath() {
        return path;
    }

    public static void main(String[] args) {
        //ReadJsonService service = new ReadJsonService("/postman-json/udt.json");
        ReadJsonService service = new ReadJsonService("/postman-json/udf.json");
        //System.out.println(service.readJson());
        Item item = service.toObject(Item.class);
        System.out.println(item);
        for(ItemLine line : item.getItem()){
            System.out.println(line.getRequest().getBody().getRawObject(Raw.class).getTableName());
        }
    }

}
