/**
* 基础场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:21:07</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testCrud.app;

import com.bdp.base.project.testCrud.entity.dto.TestCrudPaginationDTO;
import com.bdp.base.project.testCrud.entity.po.TestCrudEntity;
import com.bdp.jdbc.base.app.BasePaginationApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("testCrud.pagination")
public class TestCrudPaginationApp extends BasePaginationApp<TestCrudPaginationDTO,TestCrudEntity> {

    public TestCrudPaginationApp(){
        this.dtoClass = TestCrudPaginationDTO.class;
    }
    @Resource(name="testCrud.paginationDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
