/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 13:15:04</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.user.convert.UserSaveConvert;
import com.bdp.base.project.user.entity.dto.UserSaveDTO;
import com.bdp.base.project.user.entity.po.UserEntity;
import com.bdp.jdbc.base.app.BaseSaveApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("user.save")
@TransactionalPrimary
public class UserSaveApp extends BaseSaveApp<UserSaveDTO,UserEntity> {

    public UserSaveApp(){
        this.dtoClass = UserSaveDTO.class;
        this.converter=UserSaveConvert.INSTANCE;
    }
    @Resource(name="user.saveDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
