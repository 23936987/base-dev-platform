/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-04 13:15:02</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.app;

import com.bdp.base.project.dict.convert.DictQueryByIdConvert;
import com.bdp.base.project.dict.entity.dto.DictQueryByIdResponseDTO;
import com.bdp.base.project.dict.entity.po.DictEntity;
import com.bdp.jdbc.base.app.BaseQueryForIdApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dict.queryById")
public class DictQueryByIdApp extends BaseQueryForIdApp<DictQueryByIdResponseDTO,DictEntity> {

    public DictQueryByIdApp(){
        this.dtoClass = DictQueryByIdResponseDTO.class;
        this.converter= DictQueryByIdConvert.INSTANCE;
    }
    @Resource(name="dict.queryByIdDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
