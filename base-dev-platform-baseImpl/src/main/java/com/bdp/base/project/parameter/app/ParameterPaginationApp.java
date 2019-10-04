/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 10:08:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.app;

import com.bdp.base.project.parameter.entity.dto.ParameterPaginationDTO;
import com.bdp.base.project.parameter.entity.po.ParameterEntity;
import com.bdp.jdbc.base.app.BasePaginationApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("parameter.pagination")
public class ParameterPaginationApp extends BasePaginationApp<ParameterPaginationDTO,ParameterEntity> {

    public ParameterPaginationApp(){
        this.dtoClass = ParameterPaginationDTO.class;
    }
    @Resource(name="parameter.paginationDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
