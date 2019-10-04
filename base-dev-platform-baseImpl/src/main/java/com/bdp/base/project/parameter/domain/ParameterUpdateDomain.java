/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 10:08:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.domain;

import com.bdp.base.project.parameter.entity.po.ParameterEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseUpdateDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("parameter.updateDomain")
public class ParameterUpdateDomain extends BaseUpdateDomain<ParameterEntity> {
   @Autowired
    @Override
    public void setDao(BaseDao<ParameterEntity> dao) {
        super.setDao(dao);
    }
}
