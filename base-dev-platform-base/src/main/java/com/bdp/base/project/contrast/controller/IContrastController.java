/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-01 08:14:13</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.contrast.entity.dto.*;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IContrastController {

    ResponseEntity<ResponseBean<ContrastQueryByIdResponseDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<ContrastListResponseDTO>> list(HttpServletRequest request, Pager paginationDTO);

    ResponseEntity<ResponseBean<ContrastSaveResponseDTO>> save(HttpServletRequest request, ContrastSaveDTO saveDTO);

    ResponseEntity<ResponseBean<ContrastUpdateResponseDTO>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<ContrastDeleteResponseDTO>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<ContrastPaginationResponseDTO>> pagination(HttpServletRequest request, Pager paginationDTO);
}
