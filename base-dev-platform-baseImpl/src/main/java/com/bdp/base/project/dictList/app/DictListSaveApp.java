/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-02 19:39:29</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.dictList.convert.DictListSaveConvert;
import com.bdp.base.project.dictList.entity.dto.DictListSaveDTO;
import com.bdp.base.project.dictList.entity.po.DictListEntity;
import com.bdp.jdbc.base.app.BaseSaveApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dictList.save")
public class DictListSaveApp extends BaseSaveApp<DictListSaveDTO,DictListEntity> {

    public DictListSaveApp(){
        this.dtoClass = DictListSaveDTO.class;
        this.converter=DictListSaveConvert.INSTANCE;
    }
    @Resource(name="dictList.saveDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }


    @Override
    @TransactionalPrimary
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        return super.execute(requestDTO);
    }
}
