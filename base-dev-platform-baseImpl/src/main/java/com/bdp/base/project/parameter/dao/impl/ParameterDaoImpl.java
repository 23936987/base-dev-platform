/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 10:08:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.dao.impl;

import com.bdp.base.project.parameter.dao.ParameterDao;
import com.bdp.base.project.parameter.entity.po.ParameterEntity;
import com.bdp.jdbc.base.dao.impl.BaseDaoImpl;
import com.bdp.jdbc.db.JdbcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ParameterDaoImpl extends BaseDaoImpl<ParameterEntity> implements ParameterDao{

    @Autowired
    @Qualifier("primaryJdbcContext")
    @Override
    public void setJdbcContext(JdbcContext jdbcContext) {
        super.setJdbcContext(jdbcContext);
    }
}
