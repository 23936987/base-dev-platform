/***
 *
 * @ClassName: ResponseDTO
 * @Description: 调用App出参
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.jdbc.dto;

import com.bdp.map.QuickValueMap;
import lombok.Data;

@Data
public class ResponseDTO{
    private QuickValueMap body=new QuickValueMap();
    public void setBody(String key,Object value){
        this.body.put(key,value);
    }
}



