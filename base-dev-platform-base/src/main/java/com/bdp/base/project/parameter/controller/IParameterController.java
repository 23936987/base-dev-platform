/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.parameter.entity.dto.*;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IParameterController {

    ResponseEntity<ResponseBean<ParameterQueryByIdResponseDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<ParameterListResponseDTO>> list(HttpServletRequest request, Pager paginationDTO);

    ResponseEntity<ResponseBean<ParameterSaveResponseDTO>> save(HttpServletRequest request, ParameterSaveDTO saveDTO);

    ResponseEntity<ResponseBean<ParameterUpdateResponseDTO>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<ParameterDeleteResponseDTO>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<ParameterPaginationResponseDTO>> pagination(HttpServletRequest request, Pager paginationDTO);
}
