/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-02 19:39:29</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.dao.impl;

import com.bdp.base.project.dictList.dao.DictListDao;
import com.bdp.base.project.dictList.entity.po.DictListEntity;
import com.bdp.jdbc.base.dao.impl.BaseDaoImpl;
import com.bdp.jdbc.db.JdbcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DictListDaoImpl extends BaseDaoImpl<DictListEntity> implements DictListDao{

    @Autowired
    @Qualifier("primaryJdbcContext")
    @Override
    public void setJdbcContext(JdbcContext jdbcContext) {
        super.setJdbcContext(jdbcContext);
    }
}
