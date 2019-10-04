/**
* CRUD场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:37:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testCrud.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.testCrud.entity.dto.*;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface ITestCrudController {

    ResponseEntity<ResponseBean<TestCrudQueryByIdResponseDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<TestCrudListResponseDTO>> list(HttpServletRequest request, Pager paginationDTO);

    ResponseEntity<ResponseBean<TestCrudSaveResponseDTO>> save(HttpServletRequest request, TestCrudSaveDTO saveDTO);

    ResponseEntity<ResponseBean<TestCrudUpdateResponseDTO>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<TestCrudDeleteResponseDTO>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<TestCrudPaginationResponseDTO>> pagination(HttpServletRequest request, Pager paginationDTO);
}
