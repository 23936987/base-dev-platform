/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-01 08:14:13</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.app;

import com.bdp.base.project.contrast.entity.dto.ContrastListDTO;
import com.bdp.base.project.contrast.entity.po.ContrastEntity;
import com.bdp.jdbc.base.app.BaseListApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("contrast.list")
public class ContrastListApp extends BaseListApp<ContrastListDTO,ContrastEntity> {

    public ContrastListApp(){
        this.dtoClass = ContrastListDTO.class;
    }
    @Resource(name="contrast.listDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
