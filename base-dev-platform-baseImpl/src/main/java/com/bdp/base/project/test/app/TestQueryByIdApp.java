package com.bdp.base.project.test.app;

import com.bdp.base.project.test.convert.TestQueryByIdConvert;
import com.bdp.base.project.test.entity.dto.TestQueryByIdDTO;
import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.app.BaseQueryForIdApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("test.queryById")
public class TestQueryByIdApp extends BaseQueryForIdApp<TestQueryByIdDTO,TestEntity> {

    public TestQueryByIdApp(){
        this.dtoClass = TestQueryByIdDTO.class;
        this.converter= TestQueryByIdConvert.INSTANCE;
    }
    @Resource(name="test.queryByIdDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
