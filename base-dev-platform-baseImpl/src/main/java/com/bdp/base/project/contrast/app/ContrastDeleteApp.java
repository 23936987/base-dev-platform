/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-01 08:14:13</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.app;

import com.bdp.base.annotation.TransactionalPrimary;
import com.bdp.base.project.contrast.entity.po.ContrastEntity;
import com.bdp.jdbc.base.app.BaseDeleteApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("contrast.delete")
public class ContrastDeleteApp extends BaseDeleteApp<ContrastEntity> {

    public ContrastDeleteApp(){
    }
    @Resource(name="contrast.deleteDomain")
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
