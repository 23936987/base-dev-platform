/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-04 13:15:01</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.app;

import com.bdp.base.project.contrast.convert.ContrastQueryByIdConvert;
import com.bdp.base.project.contrast.entity.dto.ContrastQueryByIdResponseDTO;
import com.bdp.base.project.contrast.entity.po.ContrastEntity;
import com.bdp.jdbc.base.app.BaseQueryForIdApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("contrast.queryById")
public class ContrastQueryByIdApp extends BaseQueryForIdApp<ContrastQueryByIdResponseDTO,ContrastEntity> {

    public ContrastQueryByIdApp(){
        this.dtoClass = ContrastQueryByIdResponseDTO.class;
        this.converter= ContrastQueryByIdConvert.INSTANCE;
    }
    @Resource(name="contrast.queryByIdDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
