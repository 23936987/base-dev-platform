package com.bdp.base.project.test.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.app.BaseUpdateApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("test.update")
public class TestUpdateApp extends BaseUpdateApp<TestEntity> {

    public TestUpdateApp(){
    }
    @Resource(name="test.updateDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }


    @Override
    @TransactionalPrimary
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        return super.execute(requestDTO);
    }
}
