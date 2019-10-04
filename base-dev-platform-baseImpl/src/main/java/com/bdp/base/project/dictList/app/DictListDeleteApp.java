/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.dictList.entity.po.DictListEntity;
import com.bdp.jdbc.base.app.BaseDeleteApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dictList.delete")
@TransactionalPrimary
public class DictListDeleteApp extends BaseDeleteApp<DictListEntity> {

    public DictListDeleteApp(){
    }
    @Resource(name="dictList.deleteDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
