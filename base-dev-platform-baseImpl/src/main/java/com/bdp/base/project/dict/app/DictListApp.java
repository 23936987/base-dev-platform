/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-02 19:39:27</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.app;

import com.bdp.base.project.dict.entity.dto.DictListDTO;
import com.bdp.base.project.dict.entity.po.DictEntity;
import com.bdp.jdbc.base.app.BaseListApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dict.list")
public class DictListApp extends BaseListApp<DictListDTO,DictEntity> {

    public DictListApp(){
        this.dtoClass = DictListDTO.class;
    }
    @Resource(name="dict.listDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
