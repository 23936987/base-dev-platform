/**
* 基础场景-区域表-区域表
* <p>完成日期：2019-10-04 13:14:59</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.area.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.area.entity.dto.*;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IAreaController {

    ResponseEntity<ResponseBean<AreaQueryByIdResponseDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<AreaListResponseDTO>> list(HttpServletRequest request, Pager paginationDTO);

    ResponseEntity<ResponseBean<AreaSaveResponseDTO>> save(HttpServletRequest request, AreaSaveDTO saveDTO);

    ResponseEntity<ResponseBean<AreaUpdateResponseDTO>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<AreaDeleteResponseDTO>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<AreaPaginationResponseDTO>> pagination(HttpServletRequest request, Pager paginationDTO);
}
