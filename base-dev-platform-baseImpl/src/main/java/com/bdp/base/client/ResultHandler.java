package com.bdp.base.client;

import com.bdp.jdbc.base.app.App;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import com.bdp.common.helper.SpringContextHelper;
import com.bdp.exception.Assert;
import com.bdp.helper.DateHelper;
import com.bdp.helper.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/***
 *
 * @ClassName: ResultHandler
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/21 22:02
 * @version : 1.0
 */

public class ResultHandler {
    private static Logger logger = LoggerFactory.getLogger(ResultHandler.class);
    public <T> ResponseEntity<ResponseBean<T>> callApi(HttpServletRequest request, RequestContext requestDTO,
                                                                   AskListener<T> listener){
        logger.info("in_params:" + JsonHelper.toJSonString(requestDTO));
        long start = System.currentTimeMillis();

        ResponseContext responseContext  = null;
        try {
            responseContext = callService(requestDTO);
            long end = System.currentTimeMillis();
            logger.info("out_params:" +JsonHelper.toJSonString(responseContext));
            logger.info("time_consuming:" + DateHelper.formatTime(end - start));
           T object= listener.ask(responseContext);
          return ResponseBeanUtils.response_success(object);
        } catch (Exception e) {
            long end = System.currentTimeMillis();
            logger.info("out_params:" +JsonHelper.toJSonString(responseContext));
            logger.info("time_consuming:"+(end - start) +",time_consuming_cn"+ DateHelper.formatTime(end - start));
            return ResponseBeanUtils.response_fail(e);
        }
    }
    private ResponseContext callService(RequestContext requestDTO) throws Exception {
        String channel = requestDTO.getChannel();
        Assert.isNotNull(channel,"channel ${0} is null",channel);
        App app = null;
        try {
            app = (App) SpringContextHelper.getBeanById(channel);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.isNotNull(channel,"channel ${0} not exists]",channel);
        }
        Assert.isNotNull(channel,"channel ${0} not exists]",channel);
        ResponseContext responseContext = app.execute(requestDTO);
        return responseContext;
    }


    private static ResultHandler instance = new ResultHandler();

    public static ResultHandler getInstance() {
        return instance;
    }
}



