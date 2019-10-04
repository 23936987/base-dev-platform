/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-04 13:15:01</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.app;

import com.bdp.base.project.contrast.entity.dto.ContrastPaginationDTO;
import com.bdp.base.project.contrast.entity.po.ContrastEntity;
import com.bdp.jdbc.base.app.BasePaginationApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("contrast.pagination")
public class ContrastPaginationApp extends BasePaginationApp<ContrastPaginationDTO,ContrastEntity> {

    public ContrastPaginationApp(){
        this.dtoClass = ContrastPaginationDTO.class;
    }
    @Resource(name="contrast.paginationDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
