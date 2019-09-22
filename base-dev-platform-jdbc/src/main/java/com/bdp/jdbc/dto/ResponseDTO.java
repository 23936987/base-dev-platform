/***
 *
 * @ClassName: ResponseDTO
 * @Description: 调用App出参
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.jdbc.dto;

import com.bdp.map.IMapGetter;
import com.bdp.map.QuickValueMap;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ResponseDTO implements IMapGetter {
    private QuickValueMap body=new QuickValueMap();
    public void setBody(String key,Object value){
        this.body.put(key,value);
    }


    @Override
    public Object getByKey(String key) {
        return this.body.getByKey(key);
    }

    @Override
    public <T> T getObjectByKey(String key, Class<T> clazz) {
        return  this.body.getObjectByKey(key,clazz);
    }

    @Override
    public <T> List<T> getListByKey(String key, Class<T> clazz) {
        return  this.body.getListByKey(key,clazz);
    }

    @Override
    public List<Map<String, Object>> getListWithMapByKey(String key) {
        return  this.body.getListWithMapByKey(key);
    }

    @Override
    public <T> List<Map<String, T>> getListWithMapByKey(String key, Class<T> clazz) {
        return  this.body.getListWithMapByKey(key,clazz);
    }

    @Override
    public Map<String, Object> getMapByKey(String key) {
        return  this.body.getMapByKey(key);
    }

    @Override
    public <T> Map<String, T> getMapByKey(String key, Class<T> clazz) {
        return  this.body.getMapByKey(key,clazz);
    }

    @Override
    public <T> Map<String, Object> getMapQueryByKey(String key, Class<T> clazz) {
        return  this.body.getMapQueryByKey(key,clazz);
    }

    @Override
    public QuickValueMap getQuickValueMap(String key) {
        return  this.body.getQuickValueMap(key);
    }

    @Override
    public Boolean getBooleanByKey(String key) {
        return  this.body.getBooleanByKey(key);
    }

    @Override
    public Boolean getBooleanByKey(String key, Boolean defaultValue) {
        return  this.body.getBooleanByKey(key,defaultValue);
    }

    @Override
    public String getStringByKey(String key) {
        return  this.body.getStringByKey(key);
    }

    @Override
    public String getStringByKey(String key, String defaultValue) {
        return  this.body.getStringByKey(key,defaultValue);
    }

    @Override
    public Integer getIntByKey(String key) {
        return  this.body.getIntByKey(key);
    }

    @Override
    public Integer getIntByKey(String key, Integer defaultValue) {
        return  this.body.getIntByKey(key,defaultValue);
    }

    @Override
    public Long getLongByKey(String key) {
        return  this.body.getLongByKey(key);
    }

    @Override
    public Long getLongByKey(String key, Long defaultValue) {
        return  this.body.getLongByKey(key,defaultValue);
    }

    @Override
    public Float getFloatByKey(String key) {
        return  this.body.getFloatByKey(key);
    }

    @Override
    public Float getFloatByKey(String key, Float defaultValue) {
        return  this.body.getFloatByKey(key,defaultValue);
    }

    @Override
    public Double getDoubleByKey(String key) {
        return  this.body.getDoubleByKey(key);
    }

    @Override
    public Double getDoubleByKey(String key, Double defaultValue) {
        return  this.body.getDoubleByKey(key,defaultValue);
    }

    @Override
    public BigDecimal getBigDecimalByKey(String key) {
        return  this.body.getBigDecimalByKey(key);
    }

    @Override
    public BigDecimal getBigDecimalByKey(String key, String defaultValue) {
        return  this.body.getBigDecimalByKey(key,defaultValue);
    }

    @Override
    public Date getDateByKey(String key) {
        return  this.body.getDateByKey(key);
    }

    @Override
    public Date getDateByKey(String key, String defaultValue) {
        return  this.body.getDateByKey(key,defaultValue);
    }
}



