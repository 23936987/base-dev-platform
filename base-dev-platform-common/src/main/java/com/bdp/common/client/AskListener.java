/***
 *
 * @ClassName: AskListener
 * @Description: 接口Controller回调业务类
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.common.client;

import com.bdp.common.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;


interface AskListener<I extends ResponseDTO,T> {
    public <T> ResponseEntity<T> ask(I responseDTO);
}
