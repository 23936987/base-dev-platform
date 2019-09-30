package com.bdp.base.project.test.domain;

import com.bdp.base.project.test.entity.dto.TestListDTO;
import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseListDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("test.listDomain")
public class TestListDomain extends BaseListDomain<TestListDTO,TestEntity> {
    public TestListDomain(){
        this.clazz = TestListDTO.class;
        this.entityClass = TestEntity.class;
    }
   @Autowired
    @Override
    public void setDao(BaseDao<TestEntity> dao) {
        super.setDao(dao);
    }
}
