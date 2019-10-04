/**
* 基础场景-测试基础场景表-这是一个测试基础场景的示例
* <p>完成日期：2019-10-04 13:15:19</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testBase.domain;

import com.bdp.base.project.testBase.entity.dto.TestBasePaginationDTO;
import com.bdp.base.project.testBase.entity.po.TestBaseEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BasePaginationDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("testBase.paginationDomain")
public class TestBasePaginationDomain extends BasePaginationDomain<TestBasePaginationDTO,TestBaseEntity> {
    public TestBasePaginationDomain(){
        this.clazz = TestBasePaginationDTO.class;
        this.entityClass = TestBaseEntity.class;
    }
   @Autowired
    @Override
    public void setDao(BaseDao<TestBaseEntity> dao) {
        super.setDao(dao);
    }
}
