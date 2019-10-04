/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.parameter.convert.ParameterSaveConvert;
import com.bdp.base.project.parameter.entity.dto.ParameterSaveDTO;
import com.bdp.base.project.parameter.entity.po.ParameterEntity;
import com.bdp.jdbc.base.app.BaseSaveApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("parameter.save")
@TransactionalPrimary
public class ParameterSaveApp extends BaseSaveApp<ParameterSaveDTO,ParameterEntity> {

    public ParameterSaveApp(){
        this.dtoClass = ParameterSaveDTO.class;
        this.converter=ParameterSaveConvert.INSTANCE;
    }
    @Resource(name="parameter.saveDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
