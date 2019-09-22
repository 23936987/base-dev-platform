/***
 *
 * @ClassName: ResponseDTO
 * @Description: 调用App出参
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.common.dto;

import com.bdp.map.QuickValueMap;
import lombok.Data;

@Data
public class ResponseDTO {
    private String errorCode;
    private String errorMsg;
    private QuickValueMap body;
}



