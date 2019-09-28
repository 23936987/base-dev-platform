package com.bdp.base.project.test.domain;

import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseDeleteDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("test.deleteDomain")
public class TestDeleteDomain extends BaseDeleteDomain<TestEntity> {
   @Autowired
    @Override
    public void setDao(BaseDao<TestEntity> dao) {
        super.setDao(dao);
    }
}
