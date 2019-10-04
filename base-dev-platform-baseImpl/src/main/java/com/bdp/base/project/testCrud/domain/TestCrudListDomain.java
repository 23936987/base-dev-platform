/**
* CRUD场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:37:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testCrud.domain;

import com.bdp.base.project.testCrud.entity.dto.TestCrudListDTO;
import com.bdp.base.project.testCrud.entity.po.TestCrudEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.crud.domain.CrudListDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("testCrud.listDomain")
public class TestCrudListDomain extends CrudListDomain<TestCrudListDTO,TestCrudEntity> {
    public TestCrudListDomain(){
        this.clazz = TestCrudListDTO.class;
        this.entityClass = TestCrudEntity.class;
    }
   @Autowired
    @Override
    public void setDao(BaseDao<TestCrudEntity> dao) {
        super.setDao(dao);
    }
}
