package com.bdp.base.client;

import com.alibaba.fastjson.JSON;
import com.bdp.exception.BizErrorEnum;
import com.bdp.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBeanUtils {

    private static Logger logger = LoggerFactory.getLogger(ResponseBeanUtils.class);

    public static <T> ResponseEntity<ResponseBean<T>> response_success(T result){
        logger.info("返回参数:["+ JSON.toJSONString(result) +"]");
        ResponseBean<T> responseBean = new ResponseBean();
        responseBean.setData(result);
        ResponseEntity<ResponseBean<T>> responseEntity = new ResponseEntity<ResponseBean<T>>(responseBean, HttpStatus.OK);
        return responseEntity;
    }
    public static <T> ResponseEntity<ResponseBean<T>> response_fail(Exception e){
        ResponseBean<T> responseBean = new ResponseBean();
        responseBean.setSuccess(ResponseBean.BHW_RESPONSE_FAILURE);
        logger.error(e.getMessage(), e);
        if(e instanceof BizException) {
            BizException ex = (BizException) e;
            logger.error(ex.getMessage(),e);
            responseBean.setErrorCode(ex.getErrorCode());
            responseBean.setErrorMsg(ex.getMessage());
        }else{
            logger.error(e.getMessage(),e);
            responseBean.setErrorCode(BizErrorEnum.FAILURE.getErrorCode());
            responseBean.setErrorMsg(e.getMessage());
        }

        ResponseEntity<ResponseBean<T>> responseEntity = new ResponseEntity<ResponseBean<T>>(responseBean, HttpStatus.OK);
        return responseEntity;
    }
}
