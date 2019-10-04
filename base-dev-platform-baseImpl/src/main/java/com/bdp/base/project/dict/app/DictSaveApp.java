/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-04 13:15:02</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.dict.convert.DictSaveConvert;
import com.bdp.base.project.dict.entity.dto.DictSaveDTO;
import com.bdp.base.project.dict.entity.po.DictEntity;
import com.bdp.jdbc.base.app.BaseSaveApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dict.save")
@TransactionalPrimary
public class DictSaveApp extends BaseSaveApp<DictSaveDTO,DictEntity> {

    public DictSaveApp(){
        this.dtoClass = DictSaveDTO.class;
        this.converter=DictSaveConvert.INSTANCE;
    }
    @Resource(name="dict.saveDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
