package com.bdp.base.project.test.domain;

import com.bdp.base.project.test.entity.dto.TestPaginationDTO;
import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BasePaginationDomain;
import com.bdp.jdbc.base.domain.BaseQueryByIdDomain;
import com.bdp.jdbc.base.entity.dto.PaginationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("test.paginationDomain")
public class TestPaginationDomain extends BasePaginationDomain<TestPaginationDTO,TestEntity> {
    public TestPaginationDomain(){
        this.clazz = TestPaginationDTO.class;
        this.entityClass = TestEntity.class;
    }
   @Autowired
    @Override
    public void setDao(BaseDao<TestEntity> dao) {
        super.setDao(dao);
    }
}
