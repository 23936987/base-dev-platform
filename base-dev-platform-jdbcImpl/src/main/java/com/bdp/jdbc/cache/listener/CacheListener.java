package com.bdp.jdbc.cache.listener;

import com.bdp.common.helper.SpringContextHelper;
import com.bdp.jdbc.base.app.App;
import com.bdp.jdbc.dto.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/***
 *
 * @ClassName: CacheListener
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/10/2 9:19
 * @version : 1.0
 */
@Component
public class CacheListener implements ApplicationListener {
    private static Logger logger = LoggerFactory.getLogger(CacheListener.class);
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent){
            App app = SpringContextHelper.getBeanById("cache.dictCache",App.class);
            try {
                app.execute(new RequestContext());
                logger.info("====================加载数据字典缓存==================");
            } catch (Exception e) {
                logger.info("====================加载数据字典缓存失败==================");
            }
        }
    }
}



