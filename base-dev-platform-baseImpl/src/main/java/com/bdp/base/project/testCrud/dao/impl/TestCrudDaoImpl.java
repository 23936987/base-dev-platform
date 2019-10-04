/**
* CRUD场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:37:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testCrud.dao.impl;

import com.bdp.base.project.testCrud.dao.TestCrudDao;
import com.bdp.base.project.testCrud.entity.po.TestCrudEntity;
import com.bdp.jdbc.crud.dao.impl.CrudDaoImpl;
import com.bdp.jdbc.db.JdbcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class TestCrudDaoImpl extends CrudDaoImpl<TestCrudEntity> implements TestCrudDao{

    @Autowired
    @Qualifier("primaryJdbcContext")
    @Override
    public void setJdbcContext(JdbcContext jdbcContext) {
        super.setJdbcContext(jdbcContext);
    }
}
