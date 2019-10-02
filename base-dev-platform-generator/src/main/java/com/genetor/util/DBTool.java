package com.genetor.util;


import com.bdp.exception.Assert;
import com.bdp.helper.BaseHelper;
import com.bdp.helper.StringHelper;
import com.genetor.common.DBFactory;
import com.genetor.common.DBModel;
import com.genetor.model.Field;
import com.genetor.model.Table;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作工具类
 *
 * @author <a href= "mailto:chengzhiping@sinosoft.com.cn"> chengzhiping@sinosoft.com.cn</a>
 *         3:07:34 PM May 11, 2012
 */
public class DBTool {
    private static final Log log = LogFactory.getLog(DBTool.class);

    public static Connection getDBCon(DBModel model) throws SQLException, ClassNotFoundException {
        DBFactory db = new DBFactory();
        Connection conn = db.getDBConnectionInstance(model).getConnection();
        return conn;
    }

    public static List<Map<String,Object>> tables(Connection conn,String schema,String tableName) {
        List<Object> params = new ArrayList<>();
         params.add(schema);
        String sql = "select  cast(@rowno:=@rowno+1 AS unsigned) rowno,t.table_schema table_schema ,t.table_name table_name,t.table_comment table_comment from information_schema.tables t,(select @rowno:=0) r where table_schema=?";

        if(BaseHelper.isNotEmpty(tableName)) {
            sql += " and table_name like concat('%',?,'%')";
            params.add(tableName);
        }
          sql +=  " limit 0,10";
        List<Map<String,Object>> list = DBService.queryForList(conn,sql, params.toArray());
        return list;
    }

    public static Table table(Connection conn,String schema, String tableName) {
        String sql = "select table_schema ,table_name ,table_comment   from information_schema.tables t where table_schema=? and table_name=?";
        Object[] params = new Object[]{schema, tableName};
        List<Map<String, Object>> list = DBService.queryForList(conn, sql, params);
        Assert.isTrue(BaseHelper.isNotEmpty(list) && list.size() > 0, "目标表不存在");
        if (list != null && list.size() > 0) {
            Map<String, Object> res = list.get(0);
            Table table = new Table();
            String name = (String) res.get("TABLE_NAME");
            String comment = (String) res.get("TABLE_COMMENT");

            table.setTable(name);

            String className = StringHelper.parseTuo(name);
            table.setName(className);

            //解析表备注
            comment = BaseHelper.isEmpty(comment) ? "" : comment;
            String[] arr = comment.split("#");
            int count = StringHelper.stringNumbers(comment, "#");
            String nameCn = null;
            String label = null;
            String remark = null;
            if (count == 0) {
                nameCn = comment;
                label = comment;
                remark = comment;
            } else if (count == 1) {
                nameCn = arr[0];
                label = arr[1];
                remark = nameCn;

            } else if (count == 2) {
                nameCn = arr[0];
                label = arr[1];
                remark = arr[2];
            }


            table.setNameCn(nameCn);
            table.setLabel(label);
            table.setComment(remark);
            table.setSchema(schema);
            field(conn,table,schema,tableName);
            return table;
        } else {
            return null;
        }
    }

    public static  void field(Connection conn,Table table,String schema, String tableName) {
        String sql = "select COLUMN_NAME,COLUMN_TYPE,COLUMN_COMMENT,DATA_TYPE,substring_index(substring_index(COLUMN_TYPE,'(',-1),')',1) DATA_LENGTH from information_schema.COLUMNS where table_schema=? and table_name=? ";
        Object[] params = new Object[]{schema,tableName};
        List<Map<String,Object>>  list = DBService.queryForList(conn,sql,params);


        List<Field> fields = new ArrayList<Field>();
        if(list != null && list.size()>0){

             MySqlConvert  convert = new MySqlConvert();


            for (int i = 0; i < list.size(); i++) {
                Map<String,Object> res = list.get(i);
                Field field = new Field();
                String name = (String) res.get("COLUMN_NAME");
                String type = (String)res.get("DATA_TYPE");
                String comment = (String)res.get("COLUMN_COMMENT");
                String length_str = (String) res.get("DATA_LENGTH");
                Integer length = null;
                if(BaseHelper.isNotEmpty(length_str)){
                    try {
                        length=Integer.valueOf(length_str);
                    } catch (NumberFormatException e) {
                    }
                }

                field.setColumn(name);

                //解析表字段备注
                comment = BaseHelper.isEmpty(comment)?"":comment;
                int count = StringHelper.stringNumbers(comment,"#");
                String[] arr = comment.split("#");
                String nameCn = null;
                String label = null;
                String remark = null;
                String dictCode=null;
                String relationId=null;
                String relationTable=null;
                String relationShow=null;

                if(count==0){
                    // 名称
                    nameCn = comment;
                    label = comment;
                    remark = comment;

                }else if(count==1){
                    //名称#显示名称
                    nameCn = arr[0];
                    label = arr[1];
                    remark = nameCn;
                }else if(count==2){
                    try{
                        Integer commentType =Integer.valueOf(arr[0]);
                        if(commentType == 1){
                            //1#名称#说明
                            nameCn = arr[1];
                            label =nameCn;
                            remark = arr[2];
                        }else if(commentType==2){
                            //2#名称#字典代码
                            nameCn = arr[1];
                            label = nameCn;
                            remark = nameCn;
                            dictCode = arr[2];
                        }
                    }catch (Exception e){
                        //名称#显示名称#说明
                        nameCn = arr[0];
                        label = arr[1];
                        remark =  arr[2];
                    }
                }else if(count==3){
                    try{
                        Integer commentType =Integer.valueOf(arr[0]);
                        if(commentType == 1){
                            //1#名称#显示名称#字典代码
                            nameCn = arr[1];
                            label =arr[2];
                            remark = nameCn;
                            dictCode = arr[3];
                        }else if(commentType == 2){
                            //2#名称#说明#字典代码
                            nameCn = arr[1];
                            label = nameCn;
                            remark = arr[2];
                            dictCode = arr[3];
                        }
                    }catch (Exception e){
                        // 名称#显示名称#说明#字典代码
                        nameCn = arr[0];
                        label = arr[1];
                        remark =  arr[2];
                        dictCode =  arr[3];
                    }
                }else if(count==4){
                    try{
                        Integer commentType =Integer.valueOf(arr[0]);
                        if(commentType == 1){
                            //1#名称#外键#关联表#关联显示
                            nameCn = arr[1];
                            label = nameCn;
                            remark = nameCn;
                            relationId=arr[2];
                            relationTable=arr[3];
                            relationShow=arr[4];
                        }
                    }catch (Exception e){
                        //名称#显示名称#外键#关联表#关联显示
                        nameCn = arr[0];
                        label = arr[1];
                        remark =  nameCn;
                        relationId=arr[2];
                        relationTable=arr[3];
                        relationShow=arr[4];
                    }
                }else if(count==5){
                    try{
                        Integer commentType =Integer.valueOf(arr[0]);
                        if(commentType == 1){
                            //1#名称#显示名称#外键#关联表#关联显示
                            nameCn = arr[1];
                            label = arr[2];
                            remark = nameCn;
                            relationId=arr[3];
                            relationTable=arr[4];
                            relationShow=arr[5];
                        }else if(commentType == 2){
                            //2#名称#说明#外键#关联表#关联显示
                            nameCn = arr[1];
                            label = nameCn;
                            remark = arr[2];
                            relationId=arr[3];
                            relationTable=arr[4];
                            relationShow=arr[5];
                        }
                    }catch (Exception e){
                        //名称#显示名称#说明#外键#关联表#关联显示
                        nameCn = arr[0];
                        label = arr[1];
                        remark =  arr[2];
                        relationId=arr[3];
                        relationTable=arr[4];
                        relationShow=arr[5];
                    }
                }
                field.setNameCn(nameCn);
                field.setLabel(label);
                field.setComment(remark);
                if(dictCode != null){
                    field.setDictCode(dictCode);
                }
                if(relationId != null){
                    field.setRelationId(relationId);
                    field.setRelationTable(relationTable);
                    field.setRelationShow(relationShow);
                }

                field.setDbtype(type);
                field.setLength(length);

                field.setName(StringHelper.parseTuo(name));
                try {
                    field.setJavaType(convert.parseType(type));
                } catch (Exception e) {
                }

                fields.add(field);
            }
        }

        table.setFields(fields);
    }
}
