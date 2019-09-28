package com.bdp.base.project.test.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.test.entity.dto.TestQueryByIdDTO;
import com.bdp.base.project.test.entity.dto.TestSaveDTO;
import com.bdp.jdbc.base.entity.dto.PaginationDTO;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ITestController {

    ResponseEntity<ResponseBean<TestQueryByIdDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<String>> save(HttpServletRequest request, TestSaveDTO saveDTO);

    ResponseEntity<ResponseBean<Integer>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<Integer>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<Map<String,Object>>> pagination(HttpServletRequest request, PaginationDTO paginationDTO);
}
