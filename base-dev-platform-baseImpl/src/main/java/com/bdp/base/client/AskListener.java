/***
 *
 * @ClassName: AskListener
 * @Description: 接口Controller回调业务类
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.base.client;

import com.bdp.jdbc.dto.ResponseDTO;

public  interface AskListener<O> {
     O ask(ResponseDTO requestDTO);
}
