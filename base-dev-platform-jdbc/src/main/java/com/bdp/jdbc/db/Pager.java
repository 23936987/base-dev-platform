package com.bdp.jdbc.db;


import com.bdp.helper.Constant;
import com.bdp.map.QuickValueMap;
import lombok.Data;

import javax.management.Query;
import java.util.*;

@Data
public class Pager<V>{


	private Integer pageSize=10;
	private Integer page=1;
	private String orderBy;

    private List<QueryItem> params = new ArrayList<>();

    private Long total=0l;
    private List<V> list=new ArrayList<>();



    public Map<String, QueryItem> getWheres() {
        Map<String, QueryItem> wheres = new HashMap<>();

        for (Iterator<QueryItem> it = params.iterator();it.hasNext();){
            QueryItem query = it.next();
            String key = query.getKey();
            wheres.put(key,query);
        }
        return wheres;
    }

	public QueryItem getQueryItem(String key){
        Map<String, QueryItem> wheres = this.getWheres();
        return wheres.get(key);
	}

    public void put(String key,Object value){
        this.params.add(new QueryItem(key,value));
    }
    public void put(String key,QueryItem query){
        this.params.add(query);
    }
    public void put(String key,int type,Object value){
        this.params.add(new QueryItem(key,type,value));
    }

    public void remove(String key) {
        for (int i = 0; i < params.size(); i++) {
            QueryItem queryItem = params.get(i);
            if(key.equals(queryItem.getKey())){
                params.remove(i);
                break;
            }
        }
    }
}
