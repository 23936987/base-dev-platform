/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 10:08:20</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.dao.impl;

import com.bdp.base.project.user.dao.UserDao;
import com.bdp.base.project.user.entity.po.UserEntity;
import com.bdp.jdbc.base.dao.impl.BaseDaoImpl;
import com.bdp.jdbc.db.JdbcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDao{

    @Autowired
    @Qualifier("primaryJdbcContext")
    @Override
    public void setJdbcContext(JdbcContext jdbcContext) {
        super.setJdbcContext(jdbcContext);
    }
}
