package com.bdp.base.project.test.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.test.entity.dto.*;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface ITestController {

    ResponseEntity<ResponseBean<TestQueryByIdResponseDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<TestListResponseDTO>> list(HttpServletRequest request, Pager paginationDTO);

    ResponseEntity<ResponseBean<TestSaveResponseDTO>> save(HttpServletRequest request, TestSaveDTO saveDTO);

    ResponseEntity<ResponseBean<TestUpdateResponseDTO>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<TestDeleteResponseDTO>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<TestPaginationResponseDTO>> pagination(HttpServletRequest request, Pager paginationDTO);
}
