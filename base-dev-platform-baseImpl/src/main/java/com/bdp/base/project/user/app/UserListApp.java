/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 10:08:20</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.app;

import com.bdp.base.project.user.entity.dto.UserListDTO;
import com.bdp.base.project.user.entity.po.UserEntity;
import com.bdp.jdbc.base.app.BaseListApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("user.list")
public class UserListApp extends BaseListApp<UserListDTO,UserEntity> {

    public UserListApp(){
        this.dtoClass = UserListDTO.class;
    }
    @Resource(name="user.listDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
