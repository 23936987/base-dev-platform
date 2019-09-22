package com.bdp.base.project.test.controller;

import com.bdp.base.annotation.TestSwagger;
import com.bdp.base.client.ResponseBean;
import com.bdp.base.client.ResultHandler;
import com.bdp.base.project.test.dto.TestQueryDTO;
import com.bdp.jdbc.dto.RequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/***
 *
 * @ClassName: TestController
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/22 21:41
 * @version : 1.0
 */
@RestController
@Api("测试")
@TestSwagger
@RequestMapping("/test")
public class TestController implements ITestController {


    /**
     * 按主键查询
     * @param id
     * @return
     */
    @ApiOperation(value = "按主键查询",notes = "测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "string", paramType = "query", value = "主键"),
    })
    @GetMapping("/queryForId/{id}")
    @Override
    public ResponseEntity<ResponseBean<TestQueryDTO>>  queryForId(HttpServletRequest request, @PathVariable("id") String id) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setChannel("test.queryForId");
        requestDTO.setBody("id",id);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseDTO -> {
            TestQueryDTO testQueryResponseDTO = responseDTO.getBody().getObjectByKey("dto",TestQueryDTO.class);
            return testQueryResponseDTO;
        });
    }
}



