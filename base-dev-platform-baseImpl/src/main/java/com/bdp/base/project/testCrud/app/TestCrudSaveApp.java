/**
* CRUD场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:37:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testCrud.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.testCrud.convert.TestCrudSaveConvert;
import com.bdp.base.project.testCrud.entity.dto.TestCrudSaveDTO;
import com.bdp.base.project.testCrud.entity.po.TestCrudEntity;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.crud.app.CrudSaveApp;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("testCrud.save")
@TransactionalPrimary
public class TestCrudSaveApp extends CrudSaveApp<TestCrudSaveDTO,TestCrudEntity> {

    public TestCrudSaveApp(){
        this.dtoClass = TestCrudSaveDTO.class;
        this.converter=TestCrudSaveConvert.INSTANCE;
    }
    @Resource(name="testCrud.saveDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
