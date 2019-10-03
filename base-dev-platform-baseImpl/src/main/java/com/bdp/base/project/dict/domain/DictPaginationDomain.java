/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-02 19:39:27</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.domain;

import com.bdp.base.project.dict.entity.dto.DictPaginationDTO;
import com.bdp.base.project.dict.entity.po.DictEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BasePaginationDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dict.paginationDomain")
public class DictPaginationDomain extends BasePaginationDomain<DictPaginationDTO,DictEntity> {
    public DictPaginationDomain(){
        this.clazz = DictPaginationDTO.class;
        this.entityClass = DictEntity.class;
    }
   @Autowired
    @Override
    public void setDao(BaseDao<DictEntity> dao) {
        super.setDao(dao);
    }
}
