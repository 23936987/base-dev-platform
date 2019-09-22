/***
 *
 * @ClassName: App
 * @Description: App接口
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.common.app;

import com.bdp.common.dto.RequestDTO;
import com.bdp.common.dto.ResponseDTO;

@SuppressWarnings("unchecked")
public interface App {
     ResponseDTO  execute(RequestDTO requestDto) throws Exception;
}
