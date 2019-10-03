/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-02 19:39:27</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.domain;

import com.bdp.base.project.dict.entity.po.DictEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseQueryByIdDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dict.queryByIdDomain")
public class DictQueryByIdDomain extends BaseQueryByIdDomain<DictEntity> {
   @Autowired
    @Override
    public void setDao(BaseDao<DictEntity> dao) {
        super.setDao(dao);
    }
}
