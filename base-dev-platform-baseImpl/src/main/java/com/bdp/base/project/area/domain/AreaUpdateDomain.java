/**
* 基础场景-区域表-区域表
* <p>完成日期：2019-10-04 13:14:59</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.area.domain;

import com.bdp.base.project.area.entity.po.AreaEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseUpdateDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("area.updateDomain")
public class AreaUpdateDomain extends BaseUpdateDomain<AreaEntity> {
   @Autowired
    @Override
    public void setDao(BaseDao<AreaEntity> dao) {
        super.setDao(dao);
    }
}
