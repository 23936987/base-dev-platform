/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-04 13:15:02</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.domain;

import com.bdp.base.project.dict.entity.po.DictEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseSaveDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dict.saveDomain")
public class DictSaveDomain extends BaseSaveDomain<DictEntity> {
   @Autowired
    @Override
    public void setDao(BaseDao<DictEntity> dao) {
        super.setDao(dao);
    }
}
