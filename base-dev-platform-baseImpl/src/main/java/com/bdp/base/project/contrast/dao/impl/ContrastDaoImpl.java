/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-04 13:15:01</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.dao.impl;

import com.bdp.base.project.contrast.dao.ContrastDao;
import com.bdp.base.project.contrast.entity.po.ContrastEntity;
import com.bdp.jdbc.base.dao.impl.BaseDaoImpl;
import com.bdp.jdbc.db.JdbcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ContrastDaoImpl extends BaseDaoImpl<ContrastEntity> implements ContrastDao{

    @Autowired
    @Qualifier("primaryJdbcContext")
    @Override
    public void setJdbcContext(JdbcContext jdbcContext) {
        super.setJdbcContext(jdbcContext);
    }
}
