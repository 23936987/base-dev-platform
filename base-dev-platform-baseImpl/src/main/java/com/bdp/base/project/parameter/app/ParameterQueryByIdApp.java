/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.app;

import com.bdp.base.project.parameter.convert.ParameterQueryByIdConvert;
import com.bdp.base.project.parameter.entity.dto.ParameterQueryByIdResponseDTO;
import com.bdp.base.project.parameter.entity.po.ParameterEntity;
import com.bdp.jdbc.base.app.BaseQueryForIdApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("parameter.queryById")
public class ParameterQueryByIdApp extends BaseQueryForIdApp<ParameterQueryByIdResponseDTO,ParameterEntity> {

    public ParameterQueryByIdApp(){
        this.dtoClass = ParameterQueryByIdResponseDTO.class;
        this.converter= ParameterQueryByIdConvert.INSTANCE;
    }
    @Resource(name="parameter.queryByIdDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
