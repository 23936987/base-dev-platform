package com.bdp.base.project.test.app;

import com.bdp.base.project.test.entity.dto.TestListDTO;
import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.app.BaseListApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("test.list")
public class TestListApp extends BaseListApp<TestListDTO,TestEntity> {

    public TestListApp(){
        this.dtoClass = TestListDTO.class;
    }
    @Resource(name="test.listDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
