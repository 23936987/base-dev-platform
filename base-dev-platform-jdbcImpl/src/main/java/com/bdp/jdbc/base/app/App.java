/***
 *
 * @ClassName: App
 * @Description: App接口
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.jdbc.base.app;

import com.bdp.jdbc.dto.RequestDTO;
import com.bdp.jdbc.dto.ResponseDTO;

@SuppressWarnings("unchecked")
public interface App {
     ResponseDTO execute(RequestDTO requestDto) throws Exception;
}
