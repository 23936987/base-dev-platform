/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-02 19:39:29</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.app;

import com.bdp.base.project.dictList.entity.dto.DictListPaginationDTO;
import com.bdp.base.project.dictList.entity.po.DictListEntity;
import com.bdp.jdbc.base.app.BasePaginationApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dictList.pagination")
public class DictListPaginationApp extends BasePaginationApp<DictListPaginationDTO,DictListEntity> {

    public DictListPaginationApp(){
        this.dtoClass = DictListPaginationDTO.class;
    }
    @Resource(name="dictList.paginationDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
