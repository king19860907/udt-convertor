package com.mtc;

import com.mtc.util.DateUtil;

import java.io.*;
import java.util.Date;

/**
 * Created by majun on 11/4/16.
 */
public class ReadTableSql {

    public static void main(String[] args) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        String str1 = "";
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            String str = "";

            fis = new FileInputStream("/Users/majun/work/idea-java-workspace/udt-convertor/src/main/resources/before/U_PYOWOR.txt");// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            while ((str = br.readLine()) != null) {
                str1 += str+";" + "\r\n";
            }
            // 当读取的一行不为空时,把读到的str的值赋给str1
            System.out.println(str1);// 打印出str1
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

        try {
            writeTxtFile(str1,new File("/Users/majun/work/idea-java-workspace/udt-convertor/src/main/resources/after/U_PYOWOR-after.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean writeTxtFile(String content,File  fileName)throws Exception{
        RandomAccessFile mm=null;
        boolean flag=false;
        FileOutputStream o=null;
        try {
            o = new FileOutputStream(fileName);
            o.write(content.getBytes("UTF-8"));
            o.close();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(mm!=null){
                mm.close();
            }
        }
        return flag;
    }

   /* public static void main(String[] args) {
        System.out.println(Integer.parseInt("0000A1B100000000",16));
        Date date = new Date(Integer.parseInt("0000A1B100000000",16));
        System.out.println(date);
        System.out.println(DateUtil.get4yMdHms(date));
        //System.out.println(decode("0000A1B100000000"));
    }*/



    private static String hexString = "0123456789ABCDEF";

    /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(String str) {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    /*
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     */
    public static String decode(String bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
                    .indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }

}
