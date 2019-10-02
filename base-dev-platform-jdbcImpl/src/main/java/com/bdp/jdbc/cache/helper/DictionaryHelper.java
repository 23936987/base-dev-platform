package com.bdp.jdbc.cache.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bdp.common.helper.SpringContextHelper;
import com.bdp.jdbc.cache.domain.DictListType2Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DictionaryHelper {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private static String _DICT_CACHE_KEY_ = "_dict_cache_key_";

	/**
	 * 根据条目编码获取所有
	 * 
	 * @param dictCode
	 *            条目名称
	 * @return
	 */
	public List<Map<String, Object>> getItemsByCodeAndDictCode(String dictCode) {
		 List<Map<String, Object>> list = new ArrayList<>();
		 String str = (String) redisTemplate.opsForHash().get(_DICT_CACHE_KEY_,dictCode);
		 JSONArray jsonArray = JSON.parseArray(str);
		 if(jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jso = jsonArray.getJSONObject(i);
				Map<String,Object> map = jso.getInnerMap();
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 获取缓存中文名字
	 * 
	 * @param dictCode
	 *            条目编码
	 * @param code
	 *            字典编码
	 * @return
	 */
	public String getDictNameByCodeAndDictCode(String dictCode, String code) {
		List<Map<String, Object>> list = getItemsByCodeAndDictCode(dictCode);

		String name = "";
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> item = list.get(i);
				String itemKey = String.valueOf(item.get("code"));
				String itemName = String.valueOf(item.get("name"));
				if(itemKey.equalsIgnoreCase(code)){
					return itemName;
				}
			}
		}
		return name;
	}
	public String getDictNameByCodeAndDictCodeType2(String dictCode, String code) {
		DictListType2Domain domain = SpringContextHelper.getBean(DictListType2Domain.class);
		try {
			return domain.queryName(dictCode,code);
		} catch (Exception e) {
			return "";
		}
	}
}
