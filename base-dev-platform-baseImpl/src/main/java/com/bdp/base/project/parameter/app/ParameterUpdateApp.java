/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 10:08:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.parameter.entity.po.ParameterEntity;
import com.bdp.jdbc.base.app.BaseUpdateApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("parameter.update")
public class ParameterUpdateApp extends BaseUpdateApp<ParameterEntity> {

    public ParameterUpdateApp(){
    }
    @Resource(name="parameter.updateDomain")
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
