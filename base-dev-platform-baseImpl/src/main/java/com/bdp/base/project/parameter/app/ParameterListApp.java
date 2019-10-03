/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 01:40:24</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.app;

import com.bdp.base.project.parameter.entity.dto.ParameterListDTO;
import com.bdp.base.project.parameter.entity.po.ParameterEntity;
import com.bdp.jdbc.base.app.BaseListApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("parameter.list")
public class ParameterListApp extends BaseListApp<ParameterListDTO,ParameterEntity> {

    public ParameterListApp(){
        this.dtoClass = ParameterListDTO.class;
    }
    @Resource(name="parameter.listDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
