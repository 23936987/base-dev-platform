/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-04 13:15:02</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.dict.entity.po.DictEntity;
import com.bdp.jdbc.base.app.BaseDeleteApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dict.delete")
@TransactionalPrimary
public class DictDeleteApp extends BaseDeleteApp<DictEntity> {

    public DictDeleteApp(){
    }
    @Resource(name="dict.deleteDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
