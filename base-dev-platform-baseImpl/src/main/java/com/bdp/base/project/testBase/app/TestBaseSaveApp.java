/**
* 基础场景-测试基础场景表-这是一个测试基础场景的示例
* <p>完成日期：2019-10-02 19:31:08</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testBase.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.testBase.convert.TestBaseSaveConvert;
import com.bdp.base.project.testBase.entity.dto.TestBaseSaveDTO;
import com.bdp.base.project.testBase.entity.po.TestBaseEntity;
import com.bdp.jdbc.base.app.BaseSaveApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("testBase.save")
public class TestBaseSaveApp extends BaseSaveApp<TestBaseSaveDTO,TestBaseEntity> {

    public TestBaseSaveApp(){
        this.dtoClass = TestBaseSaveDTO.class;
        this.converter=TestBaseSaveConvert.INSTANCE;
    }
    @Resource(name="testBase.saveDomain")
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
