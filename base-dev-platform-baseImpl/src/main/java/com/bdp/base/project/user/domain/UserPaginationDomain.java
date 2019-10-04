/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 10:08:20</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.domain;

import com.bdp.base.project.user.entity.dto.UserPaginationDTO;
import com.bdp.base.project.user.entity.po.UserEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BasePaginationDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("user.paginationDomain")
public class UserPaginationDomain extends BasePaginationDomain<UserPaginationDTO,UserEntity> {
    public UserPaginationDomain(){
        this.clazz = UserPaginationDTO.class;
        this.entityClass = UserEntity.class;
    }
   @Autowired
    @Override
    public void setDao(BaseDao<UserEntity> dao) {
        super.setDao(dao);
    }
}
