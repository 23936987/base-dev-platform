package com.bdp.base.project.test.controller;

import com.bdp.base.annotation.TestSwagger;
import com.bdp.base.client.ResponseBean;
import com.bdp.base.client.ResultHandler;
import com.bdp.base.project.test.entity.dto.TestQueryByIdDTO;
import com.bdp.base.project.test.entity.dto.TestSaveDTO;
import com.bdp.jdbc.base.controller.BaseController;
import com.bdp.jdbc.dto.RequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class TestController extends BaseController implements ITestController {

    public TestController(){
        this._BASE_ = "test";
    }

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
    public ResponseEntity<ResponseBean<TestQueryByIdDTO>> queryForId(HttpServletRequest request, @PathVariable("id") String id) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setChannel(_BASE_ + ".queryForId");
        requestDTO.setBody("id",id);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseDTO -> {
            TestQueryByIdDTO testQueryResponseDTO = responseDTO.getBody().getObjectByKey("dto", TestQueryByIdDTO.class);
            return testQueryResponseDTO;
        });
    }

    @ApiOperation(value = "保存",notes = "测试")
    @PostMapping("/save")
    @Override
    public ResponseEntity<ResponseBean<String>> save(HttpServletRequest request,  @RequestBody TestSaveDTO saveDTO) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setChannel(_BASE_ + ".save");
        requestDTO.setBody("saveDTO",saveDTO);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseDTO -> {
            String id = responseDTO.getBody().getStringByKey("id");
            return id;
        });
    }
}



