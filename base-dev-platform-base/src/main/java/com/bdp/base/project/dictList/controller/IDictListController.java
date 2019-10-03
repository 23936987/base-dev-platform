/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-02 19:39:29</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.dictList.entity.dto.*;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IDictListController {

    ResponseEntity<ResponseBean<DictListQueryByIdResponseDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<DictListListResponseDTO>> list(HttpServletRequest request, Pager paginationDTO);

    ResponseEntity<ResponseBean<DictListSaveResponseDTO>> save(HttpServletRequest request, DictListSaveDTO saveDTO);

    ResponseEntity<ResponseBean<DictListUpdateResponseDTO>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<DictListDeleteResponseDTO>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<DictListPaginationResponseDTO>> pagination(HttpServletRequest request, Pager paginationDTO);
}
