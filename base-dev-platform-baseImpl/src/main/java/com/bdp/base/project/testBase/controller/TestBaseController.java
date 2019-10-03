/**
* 基础场景-测试基础场景表-这是一个测试基础场景的示例
* <p>完成日期：2019-10-02 19:31:08</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testBase.controller;

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
import com.bdp.base.project.testBase.entity.dto.*;
import com.bdp.helper.Constant;
import com.bdp.jdbc.base.controller.BaseController;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.map.QuickValueMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 *
 * @ClassName: TestBaseController
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/22 21:41
 * @version : 1.0
 */
@RestController
@Api("测试")
@TestSwagger
@RequestMapping("/testBase")
public class TestBaseController extends BaseController implements ITestBaseController {

    public TestBaseController(){
        this._BASE_ = "testBase";
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
    public ResponseEntity<ResponseBean<TestBaseQueryByIdResponseDTO>> queryById(HttpServletRequest request, @PathVariable("id") String id) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".queryById");
        requestDTO.setBody(Constant.ID,id);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            TestBaseQueryByIdResponseDTO testBaseQueryResponseDTO = responseContext.getObjectByKey(Constant.RESULT, TestBaseQueryByIdResponseDTO.class);
            return testBaseQueryResponseDTO;
        });
    }


    @ApiOperation(value = "保存",notes = "测试")
    @PostMapping("/save")
    @Override
    public ResponseEntity<ResponseBean<TestBaseSaveResponseDTO>> save(HttpServletRequest request, @RequestBody TestBaseSaveDTO saveDTO) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".save");
        requestDTO.setBody(Constant.DTO,saveDTO);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            String id = responseContext.getStringByKey(Constant.ID);
            TestBaseSaveResponseDTO responseDTO  = new TestBaseSaveResponseDTO();
            responseDTO.setId(id);
            return responseDTO;
        });
    }

    @ApiOperation(value = "更新",notes = "测试")
    @PostMapping("/update")
    @Override
    public ResponseEntity<ResponseBean<TestBaseUpdateResponseDTO>> update(HttpServletRequest request, @RequestBody UpdateDTO updateDTO) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".update");
        requestDTO.setBody(Constant.DTO,updateDTO);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            Integer result = responseContext.getIntByKey(Constant.RESULT);
            TestBaseUpdateResponseDTO responseDTO = new TestBaseUpdateResponseDTO();
            responseDTO.setResult(result);
            return responseDTO;
        });
    }

    @ApiOperation(value = "删除",notes = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "string", paramType = "query", value = "主键"),
    })
    @PostMapping("/delete")
    @Override
    public ResponseEntity<ResponseBean<TestBaseDeleteResponseDTO>> delete(HttpServletRequest request, String id) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".delete");
        requestDTO.setBody(Constant.ID,id);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            Integer result = responseContext.getIntByKey(Constant.RESULT);
            TestBaseDeleteResponseDTO responseDTO = new TestBaseDeleteResponseDTO();
            responseDTO.setResult(result);
            return responseDTO;
        });
    }

    @ApiOperation(value = "分页查询",notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "string", paramType = "query", value = "主键"),
    })
    @PostMapping("/pagination")
    @Override
    public ResponseEntity<ResponseBean<TestBasePaginationResponseDTO>> pagination(HttpServletRequest request, @RequestBody Pager pager) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".pagination");
        requestDTO.setBody(Constant.PAGER,pager);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            QuickValueMap qm = responseContext.getQuickValueMap(Constant.RESULT);
            Long total = qm.getLongByKey(Constant.TOTAL);
            List<TestBasePaginationDTO> rows = qm.getListByKey(Constant.ROWS,TestBasePaginationDTO.class);

            TestBasePaginationResponseDTO responseDTO=new TestBasePaginationResponseDTO();
            responseDTO.setTotal(total);
            responseDTO.setRows(rows);
            return responseDTO;
        });
    }

    @ApiOperation(value = "查询列表",notes = "查询列表")
    @PostMapping("/list")
    @Override
    public ResponseEntity<ResponseBean<TestBaseListResponseDTO>> list(HttpServletRequest request, @RequestBody Pager pager) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".list");
        requestDTO.setBody(Constant.PAGER,pager);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            List<TestBaseListDTO> rows = responseContext.getListByKey(Constant.ROWS,TestBaseListDTO.class);

            TestBaseListResponseDTO responseDTO=new TestBaseListResponseDTO();
            responseDTO.setRows(rows);
            return responseDTO;
        });
    }
}

