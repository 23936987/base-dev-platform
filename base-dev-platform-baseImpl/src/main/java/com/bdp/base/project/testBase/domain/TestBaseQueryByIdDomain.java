/**
* 基础场景-测试基础场景表-这是一个测试基础场景的示例
* <p>完成日期：2019-10-02 19:31:08</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testBase.domain;

import com.bdp.base.project.testBase.entity.po.TestBaseEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseQueryByIdDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("testBase.queryByIdDomain")
public class TestBaseQueryByIdDomain extends BaseQueryByIdDomain<TestBaseEntity> {
   @Autowired
    @Override
    public void setDao(BaseDao<TestBaseEntity> dao) {
        super.setDao(dao);
    }
}
