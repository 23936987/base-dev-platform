package com.bdp.base.project.test.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.test.entity.dto.TestQueryByIdDTO;
import com.bdp.base.project.test.entity.dto.TestSaveDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface ITestController {

    ResponseEntity<ResponseBean<TestQueryByIdDTO>> queryForId(HttpServletRequest request, @PathVariable("id") String id);


    ResponseEntity<ResponseBean<String>> save(HttpServletRequest request, @RequestBody TestSaveDTO saveDTO);

}
