/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 13:15:04</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.user.entity.po.UserEntity;
import com.bdp.jdbc.base.app.BaseUpdateApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("user.update")
@TransactionalPrimary
public class UserUpdateApp extends BaseUpdateApp<UserEntity> {

    public UserUpdateApp(){
    }
    @Resource(name="user.updateDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }
 
}
