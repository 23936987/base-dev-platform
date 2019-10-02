/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-01 08:14:13</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.domain;

import com.bdp.base.project.contrast.entity.po.ContrastEntity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseSaveDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("contrast.saveDomain")
public class ContrastSaveDomain extends BaseSaveDomain<ContrastEntity> {
   @Autowired
    @Override
    public void setDao(BaseDao<ContrastEntity> dao) {
        super.setDao(dao);
    }
}
