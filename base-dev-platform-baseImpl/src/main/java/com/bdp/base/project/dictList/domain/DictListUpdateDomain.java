/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-02 19:39:29</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.domain;

import com.bdp.base.project.dictList.entity.po.DictListEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseUpdateDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dictList.updateDomain")
public class DictListUpdateDomain extends BaseUpdateDomain<DictListEntity> {
   @Autowired
    @Override
    public void setDao(BaseDao<DictListEntity> dao) {
        super.setDao(dao);
    }
}
