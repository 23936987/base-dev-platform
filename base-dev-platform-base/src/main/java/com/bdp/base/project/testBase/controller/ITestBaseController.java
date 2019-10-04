/**
* 基础场景-测试基础场景表-这是一个测试基础场景的示例
* <p>完成日期：2019-10-04 13:15:19</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testBase.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.testBase.entity.dto.*;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface ITestBaseController {

    ResponseEntity<ResponseBean<TestBaseQueryByIdResponseDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<TestBaseListResponseDTO>> list(HttpServletRequest request, Pager paginationDTO);

    ResponseEntity<ResponseBean<TestBaseSaveResponseDTO>> save(HttpServletRequest request, TestBaseSaveDTO saveDTO);

    ResponseEntity<ResponseBean<TestBaseUpdateResponseDTO>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<TestBaseDeleteResponseDTO>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<TestBasePaginationResponseDTO>> pagination(HttpServletRequest request, Pager paginationDTO);
}
