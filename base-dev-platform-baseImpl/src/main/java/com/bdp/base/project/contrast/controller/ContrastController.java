/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-01 08:14:13</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.controller;

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
import com.bdp.base.project.contrast.entity.dto.*;
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
 * @ClassName: ContrastController
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/22 21:41
 * @version : 1.0
 */
@RestController
@Api("测试")
@TestSwagger
@RequestMapping("/contrast")
public class ContrastController extends BaseController implements IContrastController {

    public ContrastController(){
        this._BASE_ = "contrast";
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
    public ResponseEntity<ResponseBean<ContrastQueryByIdResponseDTO>> queryById(HttpServletRequest request, @PathVariable("id") String id) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".queryById");
        requestDTO.setBody(Constant.ID,id);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            ContrastQueryByIdResponseDTO contrastQueryResponseDTO = responseContext.getObjectByKey(Constant.RESULT, ContrastQueryByIdResponseDTO.class);
            return contrastQueryResponseDTO;
        });
    }


    @ApiOperation(value = "保存",notes = "测试")
    @PostMapping("/save")
    @Override
    public ResponseEntity<ResponseBean<ContrastSaveResponseDTO>> save(HttpServletRequest request, @RequestBody ContrastSaveDTO saveDTO) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".save");
        requestDTO.setBody(Constant.DTO,saveDTO);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            String id = responseContext.getStringByKey(Constant.ID);
            ContrastSaveResponseDTO responseDTO  = new ContrastSaveResponseDTO();
            responseDTO.setId(id);
            return responseDTO;
        });
    }

    @ApiOperation(value = "更新",notes = "测试")
    @PostMapping("/update")
    @Override
    public ResponseEntity<ResponseBean<ContrastUpdateResponseDTO>> update(HttpServletRequest request, @RequestBody UpdateDTO updateDTO) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".update");
        requestDTO.setBody(Constant.DTO,updateDTO);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            Integer result = responseContext.getIntByKey(Constant.RESULT);
            ContrastUpdateResponseDTO responseDTO = new ContrastUpdateResponseDTO();
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
    public ResponseEntity<ResponseBean<ContrastDeleteResponseDTO>> delete(HttpServletRequest request, String id) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".delete");
        requestDTO.setBody(Constant.ID,id);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            Integer result = responseContext.getIntByKey(Constant.RESULT);
            ContrastDeleteResponseDTO responseDTO = new ContrastDeleteResponseDTO();
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
    public ResponseEntity<ResponseBean<ContrastPaginationResponseDTO>> pagination(HttpServletRequest request, @RequestBody Pager pager) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".pagination");
        requestDTO.setBody(Constant.PAGER,pager);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            QuickValueMap qm = responseContext.getQuickValueMap(Constant.RESULT);
            Long total = qm.getLongByKey(Constant.TOTAL);
            List<ContrastPaginationDTO> rows = qm.getListByKey(Constant.ROWS,ContrastPaginationDTO.class);

            ContrastPaginationResponseDTO responseDTO=new ContrastPaginationResponseDTO();
            responseDTO.setTotal(total);
            responseDTO.setRows(rows);
            return responseDTO;
        });
    }

    @ApiOperation(value = "查询列表",notes = "查询列表")
    @PostMapping("/list")
    @Override
    public ResponseEntity<ResponseBean<ContrastListResponseDTO>> list(HttpServletRequest request, @RequestBody Pager pager) {
        RequestContext requestDTO = new RequestContext();
        requestDTO.setChannel(_BASE_ + ".list");
        requestDTO.setBody(Constant.PAGER,pager);

        return  ResultHandler.getInstance().callApi(request, requestDTO,responseContext -> {
            List<ContrastListDTO> rows = responseContext.getListByKey(Constant.ROWS,ContrastListDTO.class);

            ContrastListResponseDTO responseDTO=new ContrastListResponseDTO();
            responseDTO.setRows(rows);
            return responseDTO;
        });
    }
}

