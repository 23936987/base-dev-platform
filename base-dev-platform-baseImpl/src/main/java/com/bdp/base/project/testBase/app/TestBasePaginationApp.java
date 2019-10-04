/**
* 基础场景-测试基础场景表-这是一个测试基础场景的示例
* <p>完成日期：2019-10-04 13:15:19</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testBase.app;

import com.bdp.base.project.testBase.entity.dto.TestBasePaginationDTO;
import com.bdp.base.project.testBase.entity.po.TestBaseEntity;
import com.bdp.jdbc.base.app.BasePaginationApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("testBase.pagination")
public class TestBasePaginationApp extends BasePaginationApp<TestBasePaginationDTO,TestBaseEntity> {

    public TestBasePaginationApp(){
        this.dtoClass = TestBasePaginationDTO.class;
    }
    @Resource(name="testBase.paginationDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
