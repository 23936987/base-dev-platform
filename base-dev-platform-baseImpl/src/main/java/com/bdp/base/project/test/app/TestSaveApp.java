package com.bdp.base.project.test.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.test.convert.TestSaveConvert;
import com.bdp.base.project.test.entity.dto.TestSaveDTO;
import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.app.BaseSaveApp;
import com.bdp.jdbc.base.domain.BaseSaveDomain;
import com.bdp.jdbc.dto.RequestDTO;
import com.bdp.jdbc.dto.ResponseDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("test.save")
public class TestSaveApp extends BaseSaveApp<TestSaveDTO,TestEntity> {

    public TestSaveApp(){
        this.dtoClass = TestSaveDTO.class;
        this.converter=TestSaveConvert.INSTANCE;
    }
    @Resource(name="test.saveDomain")
    @Override
    public void setDomain(BaseSaveDomain domain) {
        super.setDomain(domain);
    }


    @Override
    @TransactionalPrimary
    public ResponseDTO execute(RequestDTO requestDTO) throws Exception {
        return super.execute(requestDTO);
    }
}
