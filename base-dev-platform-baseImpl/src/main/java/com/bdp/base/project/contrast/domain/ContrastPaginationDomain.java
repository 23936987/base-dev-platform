/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-04 13:15:01</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.domain;

import com.bdp.base.project.contrast.entity.dto.ContrastPaginationDTO;
import com.bdp.base.project.contrast.entity.po.ContrastEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BasePaginationDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("contrast.paginationDomain")
public class ContrastPaginationDomain extends BasePaginationDomain<ContrastPaginationDTO,ContrastEntity> {
    public ContrastPaginationDomain(){
        this.clazz = ContrastPaginationDTO.class;
        this.entityClass = ContrastEntity.class;
    }
   @Autowired
    @Override
    public void setDao(BaseDao<ContrastEntity> dao) {
        super.setDao(dao);
    }
}
