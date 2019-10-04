/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-04 13:15:02</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.dict.entity.dto.*;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IDictController {

    ResponseEntity<ResponseBean<DictQueryByIdResponseDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<DictListResponseDTO>> list(HttpServletRequest request, Pager paginationDTO);

    ResponseEntity<ResponseBean<DictSaveResponseDTO>> save(HttpServletRequest request, DictSaveDTO saveDTO);

    ResponseEntity<ResponseBean<DictUpdateResponseDTO>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<DictDeleteResponseDTO>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<DictPaginationResponseDTO>> pagination(HttpServletRequest request, Pager paginationDTO);
}
