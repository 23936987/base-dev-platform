/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-04 13:15:01</p>
* @varsion 1.0
* @author hj
*/
        
package com.bdp.base.project.contrast.entity.po;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import com.bdp.jdbc.base.entity.po.BaseEntity;

@Data
@Table(name = "sys_contrast", base = "contrast",nameCn="中英对照表",label="中英对照表",comment="中英对照表")
public class ContrastEntity extends BaseEntity {
/*********************************属性*********************************/
    @Column(nameCn = "英文名称",label = "英文名称",comment = "英文名称")
    private java.lang.String nameEn;
    @Column(nameCn = "中文名称",label = "中文名称",comment = "中文名称")
    private java.lang.String nameCn;
}
