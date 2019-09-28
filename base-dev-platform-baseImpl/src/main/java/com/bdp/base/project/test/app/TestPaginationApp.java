package com.bdp.base.project.test.app;

import com.bdp.base.project.test.convert.TestQueryByIdConvert;
import com.bdp.base.project.test.entity.dto.TestPaginationDTO;
import com.bdp.base.project.test.entity.dto.TestQueryByIdDTO;
import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.app.BasePaginationApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("test.pagination")
public class TestPaginationApp extends BasePaginationApp<TestPaginationDTO,TestEntity> {

    public TestPaginationApp(){
        this.dtoClass = TestPaginationDTO.class;
    }
    @Resource(name="test.paginationDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
