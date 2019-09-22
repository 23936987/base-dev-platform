package com.bdp.base.project.test.app;

import com.bdp.base.project.test.domain.TestQueryForIdDomain;
import com.bdp.base.project.test.dto.TestQueryDTO;
import com.bdp.exception.Assert;
import com.bdp.jdbc.db.App;
import com.bdp.jdbc.dto.RequestDTO;
import com.bdp.jdbc.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 *
 * @ClassName: QueryForIdApp
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/22 23:21
 * @version : 1.0
 */
@Component("test.queryForId")
public class TestQueryForIdApp implements App {

    @Autowired
    private TestQueryForIdDomain testQueryForIdDomain;
    @Override
    public ResponseDTO execute(RequestDTO requestDto) throws Exception {
        //参数获取
        String id = requestDto.getBody().getStringByKey("id");
        //参数校验
        Assert.isNotNull(id,"${0}不为空",id);
        //业务逻辑
        TestQueryDTO testQueryDTO =  testQueryForIdDomain.queryForId(id);

        //返回值处理
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setBody("dto",testQueryDTO);
        return responseDTO;
    }
}



