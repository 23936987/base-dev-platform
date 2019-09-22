package com.bdp.base.project.test.controller;

import com.bdp.base.client.ResponseBean;
import com.bdp.base.project.test.dto.TestQueryDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/***
 *
 * @ClassName: ITestController
 * @Description: test测试
 * @Auther: yecao
 * @Date: 2019/9/22 15:13
 * @version : 1.0
 */

public interface ITestController {
    /**
     *  按主键查询
     * @param id
     * @return
     */
    ResponseEntity<ResponseBean<TestQueryDTO>> queryForId(HttpServletRequest request, String id);
}



