package com.bdp.common.client;

import com.bdp.common.app.App;
import com.bdp.common.dto.RequestDTO;
import com.bdp.common.dto.ResponseDTO;
import com.bdp.common.helper.SpringContextHelper;
import com.bdp.exception.Assert;
import com.bdp.exception.BizErrorEnum;
import com.bdp.exception.BizException;
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
    public <T> ResponseEntity<T> callApi(HttpServletRequest request, RequestDTO requestDTO, AskListener listener){
        logger.info("输入参数:" + JsonHelper.toJSonString(requestDTO));
        long start = System.currentTimeMillis();

        ResponseDTO responseDTO  = null;
        try {
            responseDTO = callService(requestDTO);
           return listener.ask(responseDTO);
        } catch (Exception e) {
            responseDTO = new ResponseDTO();
            logger.error(e.getMessage(), e);
            if(e instanceof BizException) {
                BizException ex = (BizException) e;
                responseDTO.setErrorCode(ex.getErrorCode());
                responseDTO.setErrorMsg(ex.getErrorCode());
            }else{
                responseDTO.setErrorCode(BizErrorEnum.UNKONW.getErrorCode());
                responseDTO.setErrorMsg(e.getMessage());
            }
        }
        long end = System.currentTimeMillis();
        logger.info("输出参数:" +JsonHelper.toJSonString(responseDTO));
        logger.info("耗时:" + DateHelper.formatTime(end - start));
        return null;
    }

    private ResponseDTO callService(RequestDTO requestDTO) throws Exception {
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
        ResponseDTO responseDTO = app.execute(requestDTO);
        return responseDTO;
    }


    private static ResultHandler instance = new ResultHandler();

    public static ResultHandler getInstance() {
        return instance;
    }
}



