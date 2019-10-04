/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 13:15:04</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.user.entity.dto.*;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IUserController {

    ResponseEntity<ResponseBean<UserQueryByIdResponseDTO>> queryById(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<UserListResponseDTO>> list(HttpServletRequest request, Pager paginationDTO);

    ResponseEntity<ResponseBean<UserSaveResponseDTO>> save(HttpServletRequest request, UserSaveDTO saveDTO);

    ResponseEntity<ResponseBean<UserUpdateResponseDTO>> update(HttpServletRequest request, UpdateDTO updateDT);

    ResponseEntity<ResponseBean<UserDeleteResponseDTO>> delete(HttpServletRequest request, String id);

    ResponseEntity<ResponseBean<UserPaginationResponseDTO>> pagination(HttpServletRequest request, Pager paginationDTO);
}
