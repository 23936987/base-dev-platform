/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-04 13:15:01</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.contrast.convert.ContrastSaveConvert;
import com.bdp.base.project.contrast.entity.dto.ContrastSaveDTO;
import com.bdp.base.project.contrast.entity.po.ContrastEntity;
import com.bdp.jdbc.base.app.BaseSaveApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("contrast.save")
@TransactionalPrimary
public class ContrastSaveApp extends BaseSaveApp<ContrastSaveDTO,ContrastEntity> {

    public ContrastSaveApp(){
        this.dtoClass = ContrastSaveDTO.class;
        this.converter=ContrastSaveConvert.INSTANCE;
    }
    @Resource(name="contrast.saveDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
