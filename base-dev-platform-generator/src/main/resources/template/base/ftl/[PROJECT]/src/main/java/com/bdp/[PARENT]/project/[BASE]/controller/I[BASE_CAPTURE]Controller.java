<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.controller;

import com.bdp.${parent}.client.ResponseBean;
import com.bdp.${parent}.project.${base}.entity.dto.*;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface I${baseCapture}Controller {

    ResponseEntity<ResponseBean<${baseCapture}QueryByIdResponseDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<${baseCapture}ListResponseDTO>> list(HttpServletRequest request, Pager paginationDTO);

    ResponseEntity<ResponseBean<${baseCapture}SaveResponseDTO>> save(HttpServletRequest request, ${baseCapture}SaveDTO saveDTO);

    ResponseEntity<ResponseBean<${baseCapture}UpdateResponseDTO>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<${baseCapture}DeleteResponseDTO>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<${baseCapture}PaginationResponseDTO>> pagination(HttpServletRequest request, Pager paginationDTO);
}
