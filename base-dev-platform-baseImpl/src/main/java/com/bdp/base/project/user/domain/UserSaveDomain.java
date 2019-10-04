/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 13:15:04</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.domain;

import com.bdp.base.project.user.entity.po.UserEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseSaveDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("user.saveDomain")
public class UserSaveDomain extends BaseSaveDomain<UserEntity> {
   @Autowired
    @Override
    public void setDao(BaseDao<UserEntity> dao) {
        super.setDao(dao);
    }
}
