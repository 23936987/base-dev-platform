/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.app;

import com.bdp.base.project.dictList.entity.dto.DictListListDTO;
import com.bdp.base.project.dictList.entity.po.DictListEntity;
import com.bdp.jdbc.base.app.BaseListApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dictList.list")
public class DictListListApp extends BaseListApp<DictListListDTO,DictListEntity> {

    public DictListListApp(){
        this.dtoClass = DictListListDTO.class;
    }
    @Resource(name="dictList.listDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
