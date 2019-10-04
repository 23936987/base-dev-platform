/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-04 13:15:02</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.app;

import com.bdp.base.project.dict.entity.dto.DictPaginationDTO;
import com.bdp.base.project.dict.entity.po.DictEntity;
import com.bdp.jdbc.base.app.BasePaginationApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dict.pagination")
public class DictPaginationApp extends BasePaginationApp<DictPaginationDTO,DictEntity> {

    public DictPaginationApp(){
        this.dtoClass = DictPaginationDTO.class;
    }
    @Resource(name="dict.paginationDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
