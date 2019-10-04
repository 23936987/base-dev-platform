/***
 *
 * @ClassName: App
 * @Description: App接口
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.jdbc.base.app;

import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;

@SuppressWarnings("unchecked")
public interface App {
     ResponseContext execute(RequestContext requestDTO) throws Exception;
}
