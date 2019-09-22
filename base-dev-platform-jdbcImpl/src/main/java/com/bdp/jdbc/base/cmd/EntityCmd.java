package com.bdp.jdbc.base.cmd;

import com.bdp.helper.BaseHelper;
import com.bdp.helper.ReflectionHelper;
import com.bdp.helper.StringHelper;
import com.bdp.jdbc.annotation.DateColumn;
import com.bdp.jdbc.annotation.RelationColumn;
import com.bdp.jdbc.annotation.Table;
import com.bdp.jdbc.db.QType;
import com.bdp.jdbc.db.Query;
import com.bdp.jdbc.db.WhereResult;
import com.bdp.jdbc.base.entity.Entity;
import com.bdp.jdbc.db.cmd.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.util.*;

public abstract class EntityCmd<E extends Entity,R> implements Command<R> {
    private static Logger logger = LoggerFactory.getLogger(EntityCmd.class);
    protected Class<E> entityClass;

	public void setClazz(Class<E> entityClass){
        this.entityClass = entityClass;
	}


    protected String getTableName() {
        String name = entityClass.getName();
        String tableName = null;
        Table table = entityClass.getAnnotation(Table.class);
        if(table != null && BaseHelper.isNotEmpty(table.name())){
            tableName = table.name();
        }else{
            tableName = StringHelper.parseCol(name);
        }
        return tableName;
    }
    protected String getBase() {
        String base = null;
        Table table = entityClass.getAnnotation(Table.class);
        if(table != null && BaseHelper.isNotEmpty(table.base())){
            base = table.base();
        }else{
            base = entityClass.getSimpleName();
        }
        return base;
    }

    protected  String getColumnName(Field f) {
        String fieldName = f.getName();
        String columnName = StringHelper.parseCol(fieldName);
        return columnName;
    }

    protected WhereResult getWhere(Map<String, Object> params) {
        List<String> notWhere = new ArrayList<>();
        return  getWhere(params,notWhere);
    }
    protected WhereResult getWhere(Map<String, Object> params, List<String> notWhere) {
        Map<String,Object> wheres = new HashMap<>();

        String sql="";
        if(params != null && params.size() >0){
            Set<Map.Entry<String,Object>> whereSet = params.entrySet();
            for (Iterator<Map.Entry<String,Object>> it=whereSet.iterator();it.hasNext();){
                Map.Entry<String,Object> item = it.next();
                String key = item.getKey();
                Object where = item.getValue();

                if(BaseHelper.isNotEmpty(key)){
                    // key值不等于空
                    if("customSql".equalsIgnoreCase(key)){
                        //key值默认为customSql,传入自定义sql
                        String customSql = String.valueOf(where);
                        sql += customSql;
                    }else{
                        //其它情况均为按model字段作查询条件
                        Field f = ReflectionHelper.getDeclaredField(entityClass, key);

                        if(f != null){
                            if(notWhere.contains(key)){
                                continue;
                            }
                            //字段存在才解析
                            String fieldName = key;
                            String columnName = getColumnName(f);
                            if(where == null){
                                //where为null
                                sql = getNullSql( sql, columnName);
                            }else if(where instanceof Query){
                            	Query query = (Query) where;
                            	if(query.getValue() == null) {
                                    sql = getNullSql( sql, columnName);
                                }else if(!"".equals(query.getValue().toString().trim())){
                                    sql = getEmptySql( sql, columnName);
                                }else{
                                    sql = getQuerySql(wheres, sql, query, fieldName, columnName);
                                }
                            }else if(where instanceof List){
	                            List<Query> list = (List<Query>)where;
	                            for (int i = 0; i < list.size(); i++) {
		                            Query query = list.get(i);
		                            String name = fieldName +"_"+ i;
		                            sql = getQuerySql(wheres, sql, query, name, columnName);
	                            }
                            } else if("".equals(where.toString().trim())){
                                //where为 ''
                                sql = getEmptySql( sql, columnName);
                            }else{
                                sql = getEqualSql( wheres, sql, fieldName, columnName, where);
                            }
                        }
                    }
                }
            }
        }
        WhereResult result = new WhereResult();
        result.setSql(sql);
        result.setWheres(wheres);
        return result;
    }


	private String getQuerySql(Map<String, Object> wheres, String sql, Query query, String fieldName, String columnName) {
		//where 为 Query对象
		Integer type = query.getType();
		Object value = query.getValue();

		if(type == null || QType.EQUALS.getType() == type){
		    sql = getEqualSql( wheres, sql, fieldName, columnName, value);
		}else  if(QType.GREATER.getType() == type){
		    sql = getGreaterSql( wheres, sql, fieldName, columnName, value);
		}else  if(QType.GREATER_EQ.getType() == type){
		    sql = getGreaterEqSql( wheres, sql, fieldName, columnName, value);
		}else  if(QType.LESS.getType() == type){
		    sql = getLessSql( wheres, sql, fieldName, columnName, value);
		}else  if(QType.LESS_EQ.getType() == type){
		    sql = getLessEqSql( wheres, sql, fieldName, columnName, value);
		}else  if(QType.FIND_IN_SET.getType() == type){
		    sql = getFindInSetSql(wheres, sql, fieldName, columnName, value);
		}else if(QType.IN.getType() == type){
		    sql = getInSql( wheres, sql, fieldName, columnName, value);
		}else if(QType.NOT_IN.getType() == type){
		    sql = getNotInSql( wheres, sql, fieldName, columnName, value);
		}else if(QType.LEFT_LIKE.getType() == type){
		    sql = getLeftLikeSql( wheres, sql, fieldName, columnName, value);
		} else if(QType.RIGHT_LIKE.getType() == type){
            sql = getRightLikeSql( wheres, sql, fieldName, columnName, value);
        }else if(QType.FULL_LIKE.getType() == type){
            sql = getFullLikeSql( wheres, sql, fieldName, columnName, value);
        }
		return sql;
	}

	private void setWhere(Map<String, Object> wheres, String key, Object where) {
        if(where instanceof Query){
                //where 为 Query对象
                Query query = (Query) where;
                Object value = query.getValue();
                wheres.put(key,value);
            }else{
                wheres.put(key,where);
        }
    }
    
    private String getFullLikeSql( Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(BaseHelper.isNotEmpty(value)){

	        sql += "  and t." +columnName +  " like concat('%',:"+fieldName+",'%') ";
            wheres.put(fieldName,value);
        }
        return sql;
    }

    private String getRightLikeSql( Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(BaseHelper.isNotEmpty(value)){

	        sql += "  and t." +columnName +  " like concat(:"+fieldName+",'%')  ";
            wheres.put(fieldName,value);
        }
        return sql;
    }

    private String getLeftLikeSql( Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(BaseHelper.isNotEmpty(value)){

	        sql += "  and t." +columnName +  " like concat('%',:"+fieldName+")  ";
            wheres.put(fieldName,value);
        }
        return sql;
    }

    private String getNotInSql( Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(BaseHelper.isNotEmpty(value)){
            String str = String.valueOf(value);
            List<Object> list = BaseHelper.string2list(str);
	        sql += "  and t." +columnName +  " not in (:"+fieldName+")  ";
            wheres.put(fieldName,list);
        }
        return sql;
    }

    private String getInSql( Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(BaseHelper.isNotEmpty(value)){

            String str = String.valueOf(value);
            List<Object> list = BaseHelper.string2list(str);
	        sql += "  and t." +columnName +  " in (:"+fieldName+")  ";
            wheres.put(fieldName,list);
        }
        return sql;
    }

    private String getFindInSetSql(Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(BaseHelper.isNotEmpty(value)){
            sql += "  and find_in_set(t." +columnName +  ",:"+fieldName+") ";
            wheres.put(fieldName,value);
        }
        return sql;
    }

    private String getLessEqSql( Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(BaseHelper.isNotEmpty(value)){

	        sql += " and t." +columnName +  "<= :"+fieldName+"  ";
            wheres.put(fieldName,value);
        }
        return sql;
    }

    private String getLessSql( Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(BaseHelper.isNotEmpty(value)){

	        sql += " and t." +columnName +  "< :"+fieldName+"  ";
            wheres.put(fieldName,value);
        }
        return sql;
    }

    private String getGreaterEqSql( Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(BaseHelper.isNotEmpty(value)){

	        sql += " and t." +columnName +  ">= :"+fieldName+"   ";
            wheres.put(fieldName,value);
        }
        return sql;
    }

    private String getGreaterSql( Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(BaseHelper.isNotEmpty(value)){

	        sql += " and t." +columnName +  "> :"+fieldName+"  ";
            wheres.put(fieldName,value);
        }
        return sql;
    }

    private String getEqualSql( Map<String, Object> wheres, String sql, String fieldName, String columnName, Object value) {
        if(value == null){
            sql = getNullSql(sql, columnName);
        }else if("".equalsIgnoreCase(value.toString().trim())){
            sql = getEmptySql(sql, columnName);
        }else{
	        sql += " and t." +columnName +  "=:"+fieldName+"";
            wheres.put(fieldName,value);
        }
        return sql;
    }

    private String getEmptySql( String sql, String columnName) {

	    sql += " and t." +columnName +  " =''";
        return sql;
    }

    private String getNullSql( String sql, String columnName) {

	    sql += " and t." +columnName +  " is null";
        return sql;
    }

    protected WhereResult getWhereConvert(Map<String, Object> params) {
        Map<String,Object> wheres = new HashMap<>();

        if(params != null && params.size() >0){
            Set<Map.Entry<String,Object>> whereSet = params.entrySet();
            for (Iterator<Map.Entry<String,Object>> it=whereSet.iterator();it.hasNext();){
                Map.Entry<String,Object> item = it.next();
                String fieldName = item.getKey();
                Object where = params.get(fieldName);

                setWhere(wheres, fieldName, where);
            }
        }
        WhereResult result = new WhereResult();
        result.setWheres(wheres);
        return result;
    }

    protected String getPageQuery() {
        String query = "";
        List<Field> fields = ReflectionHelper.getDeclaredFields(entityClass);
        for (int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            String fieldName = f.getName();
            String columnName = getColumnName(f);


            Transient tran =f.getAnnotation(Transient.class);
            if(tran != null){
                continue;
            }

            if("java.util.Date".equals(f.getType().getName())){
                query += ",DATE_FORMAT(t."+columnName+",'%Y-%m-%d %T') " + fieldName;
            }else{
                query += ",t." + columnName + " " + fieldName;
            }
        }
        return query;
    }

    protected String getQuery() {
        String query = "";
        List<Field> fields = ReflectionHelper.getDeclaredFields(entityClass);
        for (int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            String fieldName = f.getName();
            String columnName = getColumnName(f);

            if(ReflectionHelper.getDeclaredField(entityClass,fieldName) != null) {
	            query += ",t." + columnName + " " + fieldName;
            }
        }
        return query;
    }
    protected String getMapQuery() {
        String query = "";
        List<Field> fields = ReflectionHelper.getDeclaredFields(entityClass);
        for (int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            String fieldName = f.getName();
            String columnName = getColumnName(f);

            if(ReflectionHelper.getDeclaredField(entityClass,fieldName) == null){
                continue;
            }

            DateColumn dateColumn = f.getAnnotation(DateColumn.class);
            if(dateColumn != null){
                String format = dateColumn.format();
                query += ",date_format(t."+columnName+",'"+format+"')" + fieldName + "Name";
            }
	       /*
	       DictColumn dictColumn = f.getAnnotation(DictColumn.class);
	        if(dictColumn != null){
		        String dictCode = dictColumn.dictCode();
		        query += ",(select l.name from sys_dict d join sys_dict_list l on d.id=l.dict_id where d.code='"+dictCode+"' and l.code=t."+columnName+") " + fieldName + "Name";
	        }*/


	        RelationColumn relationColumn = f.getAnnotation(RelationColumn.class);
	        if(relationColumn != null){
		        String show = relationColumn.show();
		        String relationId = relationColumn.relationId();
		        String relationTable = relationColumn.relationTable();

		        query += ",(select concat(a."+show+",'') from "+relationTable+" a where a."+relationId+"=t."+ columnName +")" + fieldName + "Name";
	        }

            query += ",t." + columnName + " " + fieldName;
        }
        return query;
    }


}
