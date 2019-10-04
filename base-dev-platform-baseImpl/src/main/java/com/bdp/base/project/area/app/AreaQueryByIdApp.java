/**
* 基础场景-区域表-区域表
* <p>完成日期：2019-10-04 13:14:59</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.area.app;

import com.bdp.base.project.area.convert.AreaQueryByIdConvert;
import com.bdp.base.project.area.entity.dto.AreaQueryByIdResponseDTO;
import com.bdp.base.project.area.entity.po.AreaEntity;
import com.bdp.jdbc.base.app.BaseQueryForIdApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("area.queryById")
public class AreaQueryByIdApp extends BaseQueryForIdApp<AreaQueryByIdResponseDTO,AreaEntity> {

    public AreaQueryByIdApp(){
        this.dtoClass = AreaQueryByIdResponseDTO.class;
        this.converter= AreaQueryByIdConvert.INSTANCE;
    }
    @Resource(name="area.queryByIdDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
