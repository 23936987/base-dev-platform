package com.bdp.base.client;

import com.bdp.exception.BizErrorEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResponseBean<T extends Object>  {
    public static String BHW_RESPONSE_SUCCESS="T";
    public static String BHW_RESPONSE_FAILURE="F";

    @ApiModelProperty(value = "请求是否成功;T 代表成功，F 代表失败")
    private String success=BHW_RESPONSE_SUCCESS;
    @ApiModelProperty(value = "具体的返回内容")
    private T data;
    @ApiModelProperty(value = "错误编码")
    private String errorCode="";
    @ApiModelProperty(value = "错误信息描述")
    private String errorMsg="";


    public ResponseBean() {
        this.errorCode = BizErrorEnum.OK.getErrorCode();
        this.errorMsg = BizErrorEnum.OK.getErrorMsg();
    }

    public ResponseBean(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ResponseBean(String errorCode, String errorMsg, T data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }
}
