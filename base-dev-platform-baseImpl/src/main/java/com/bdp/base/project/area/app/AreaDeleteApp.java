/**
* 基础场景-区域表-区域表
* <p>完成日期：2019-10-04 13:14:59</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.area.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.area.entity.po.AreaEntity;
import com.bdp.jdbc.base.app.BaseDeleteApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("area.delete")
@TransactionalPrimary
public class AreaDeleteApp extends BaseDeleteApp<AreaEntity> {

    public AreaDeleteApp(){
    }
    @Resource(name="area.deleteDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
