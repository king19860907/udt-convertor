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
        return getSqlSession().selectList("findResult",params);
    }

}
