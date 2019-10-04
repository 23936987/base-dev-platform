/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 13:15:04</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.app;

import com.bdp.base.project.user.entity.dto.UserPaginationDTO;
import com.bdp.base.project.user.entity.po.UserEntity;
import com.bdp.jdbc.base.app.BasePaginationApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("user.pagination")
public class UserPaginationApp extends BasePaginationApp<UserPaginationDTO,UserEntity> {

    public UserPaginationApp(){
        this.dtoClass = UserPaginationDTO.class;
    }
    @Resource(name="user.paginationDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
