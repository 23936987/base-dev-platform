package com.bdp.base.project.test.controller;

/***
 *
 * @ClassName: controller
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/28 8:08
 * @version : 1.0
 */

import com.bdp.base.annotation.TestSwagger;
import com.bdp.base.client.ResponseBean;
import com.bdp.base.client.ResultHandler;
import com.bdp.base.project.test.entity.dto.TestQueryByIdDTO;
import com.bdp.base.project.test.entity.dto.TestSaveDTO;
import com.bdp.helper.Constant;
import com.bdp.jdbc.base.controller.BaseController;
import com.bdp.jdbc.base.entity.dto.PaginationDTO;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import com.bdp.jdbc.dto.RequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    @GetMapping("/queryById/{id}")
    @Override
    public ResponseEntity<ResponseBean<TestQueryByIdDTO>> queryById(HttpServletRequest request, @PathVariable("id") String id) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setChannel(_BASE_ + ".queryById");
        requestDTO.setBody(Constant.ID,id);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseDTO -> {
            TestQueryByIdDTO testQueryResponseDTO = responseDTO.getBody().getObjectByKey(Constant.DTO, TestQueryByIdDTO.class);
            return testQueryResponseDTO;
        });
    }

    @ApiOperation(value = "保存",notes = "测试")
    @PostMapping("/save")
    @Override
    public ResponseEntity<ResponseBean<String>> save(HttpServletRequest request,  @RequestBody TestSaveDTO saveDTO) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setChannel(_BASE_ + ".save");
        requestDTO.setBody(Constant.DTO,saveDTO);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseDTO -> {
            String id = responseDTO.getBody().getStringByKey("id");
            return id;
        });
    }

    @ApiOperation(value = "更新",notes = "测试")
    @PostMapping("/update")
    @Override
    public ResponseEntity<ResponseBean<Integer>> update(HttpServletRequest request,@RequestBody UpdateDTO updateDTO) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setChannel(_BASE_ + ".update");
        requestDTO.setBody(Constant.DTO,updateDTO);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseDTO -> {
            Integer result = responseDTO.getIntByKey(Constant.RESULT);
            return result;
        });
    }

    @ApiOperation(value = "删除",notes = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "string", paramType = "query", value = "主键"),
    })
    @PostMapping("/delete")
    @Override
    public ResponseEntity<ResponseBean<Integer>> delete(HttpServletRequest request, String id) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setChannel(_BASE_ + ".delete");
        requestDTO.setBody(Constant.ID,id);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseDTO -> {
            Integer result = responseDTO.getIntByKey(Constant.RESULT);
            return result;
        });
    }

    @ApiOperation(value = "分页查询",notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "string", paramType = "query", value = "主键"),
    })
    @PostMapping("/pagination")
    @Override
    public ResponseEntity<ResponseBean<Map<String,Object>>> pagination(HttpServletRequest request, @RequestBody PaginationDTO paginationDTO) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setChannel(_BASE_ + ".pagination");
        requestDTO.setBody(Constant.DTO,paginationDTO);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseDTO -> {
            Map<String,Object> res = responseDTO.getMapByKey(Constant.RESULT);
            return res;
        });
    }

}

