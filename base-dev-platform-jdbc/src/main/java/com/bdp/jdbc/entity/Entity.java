package com.bdp.jdbc.entity;

import com.bdp.jdbc.annotation.Column;
import com.bdp.jdbc.annotation.Id;

/***
 *
 * @ClassName: Entity
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/22 10:17
 * @version : 1.0
 */

public class Entity {
    @Id
    @Column(name = "id",nameCn = "主键",label = "主键",comment = "主键")
    private String id;
}



