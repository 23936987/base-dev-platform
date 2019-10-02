package com.bdp.jdbc.cache.app;

import com.alibaba.fastjson.JSON;
import com.bdp.jdbc.cache.domain.DictListsDomain;
import com.bdp.jdbc.cache.domain.DictsDomain;
import com.bdp.jdbc.db.App;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/***
 *
 * @ClassName: CacheApp
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/10/2 12:01
 * @version : 1.0
 */
@Component("cache.dictCache")
public class CacheApp implements App {
    private static Logger logger = LoggerFactory.getLogger(CacheApp.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static String _DICT_CACHE_KEY_ = "_dict_cache_key_";
    @Autowired
    private DictsDomain dictsDomain;
    @Autowired
    private DictListsDomain dictListsDomain;
    @Override
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        List<Map<String,Object>> dicts = dictsDomain.queryForList();
        if(dicts != null && dicts.size()>0){
            for (int i = 0; i < dicts.size(); i++) {
                Map<String,Object> item = dicts.get(i);
                String dictCode = String.valueOf(item.get("code"));
                String dictName = String.valueOf(item.get("name"));

                List<Map<String,Object>> dataList = dictListsDomain.queryForList(dictCode);
                logger.info("加载数据字典缓存["+dictCode+","+dictName+"]");
                redisTemplate.opsForHash().put(_DICT_CACHE_KEY_,dictCode, JSON.toJSONString(dataList));
            }
        }
        return null;
    }
}



