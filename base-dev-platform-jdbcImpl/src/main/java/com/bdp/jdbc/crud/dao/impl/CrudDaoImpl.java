package com.bdp.jdbc.crud.dao.impl;

import com.bdp.jdbc.base.dao.impl.BaseDaoImpl;
import com.bdp.jdbc.crud.dao.CrudDao;
import com.bdp.jdbc.crud.entity.po.CrudEntity;

/***
 *
 * @ClassName: BaseDaoImpl
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/23 22:20
 * @version : 1.0
 */

public abstract class CrudDaoImpl<E extends CrudEntity>  extends BaseDaoImpl<E> implements CrudDao<E> {

}



