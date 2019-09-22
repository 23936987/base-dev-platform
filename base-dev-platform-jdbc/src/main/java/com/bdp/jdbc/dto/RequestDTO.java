/***
 *
 * @ClassName: RequestDTO
 * @Description: 调用App入参
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.jdbc.dto;

import com.bdp.map.QuickValueMap;
import lombok.Data;

@Data
public class RequestDTO {
    private String channel;
    private QuickValueMap header = new QuickValueMap();
    private QuickValueMap body  = new QuickValueMap();

    public void setHeader(String key,Object value){
        this.header.put(key,value);
    }
    public void setBody(String key,Object value){
        this.body.put(key,value);
    }
}



