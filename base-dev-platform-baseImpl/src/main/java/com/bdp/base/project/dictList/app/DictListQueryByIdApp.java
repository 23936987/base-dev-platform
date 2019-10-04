/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.app;

import com.bdp.base.project.dictList.convert.DictListQueryByIdConvert;
import com.bdp.base.project.dictList.entity.dto.DictListQueryByIdResponseDTO;
import com.bdp.base.project.dictList.entity.po.DictListEntity;
import com.bdp.jdbc.base.app.BaseQueryForIdApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dictList.queryById")
public class DictListQueryByIdApp extends BaseQueryForIdApp<DictListQueryByIdResponseDTO,DictListEntity> {

    public DictListQueryByIdApp(){
        this.dtoClass = DictListQueryByIdResponseDTO.class;
        this.converter= DictListQueryByIdConvert.INSTANCE;
    }
    @Resource(name="dictList.queryByIdDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
