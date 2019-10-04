/**
* 基础场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:21:07</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testCrud.domain;

import com.bdp.base.project.testCrud.entity.po.TestCrudEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseUpdateDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("testCrud.updateDomain")
public class TestCrudUpdateDomain extends BaseUpdateDomain<TestCrudEntity> {
   @Autowired
    @Override
    public void setDao(BaseDao<TestCrudEntity> dao) {
        super.setDao(dao);
    }
}
