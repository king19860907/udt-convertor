package com.mtc.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by majun on 11/10/16.
 */
public class BaseDao extends SqlSessionDaoSupport {

    public List<Map<String,Object>> findResult(String tableName){
        Map<String,Object> params = new HashMap<>();
        params.put("tableName",tableName);
        return getSqlSession().selectList("findResult",params);
    }
    
    public void insertResult(String suffix,String tableName,Map<String,Object> values){
    	Map<String,Object> params = new HashMap<>();
    	params.put("tableName", tableName);
    	params.put("suffix", suffix);
    	params.put("map", values);
    	getSqlSession().insert("insertResult", params);
    }

    public List<String> findAllTables(){
        return getSqlSession().selectList("findAllTables",null);
    }

    public void deleteResult(String suffix,String tableName){
        Map<String,Object> params = new HashMap<>();
        params.put("tableName", tableName);
        params.put("suffix", suffix);
        getSqlSession().delete("deleteResult",params);
    }

}
