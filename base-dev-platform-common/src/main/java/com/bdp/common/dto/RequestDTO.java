/***
 *
 * @ClassName: RequestDTO
 * @Description: 调用App入参
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.common.dto;

import com.bdp.map.QuickValueMap;
import lombok.Data;

import javax.security.auth.login.LoginContext;

@Data
public class RequestDTO<T> {
    private String channel;
    private QuickValueMap header = new QuickValueMap();
    private QuickValueMap body;
}



