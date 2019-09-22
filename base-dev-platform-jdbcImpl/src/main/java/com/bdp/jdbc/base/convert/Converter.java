package com.bdp.jdbc.base.convert;

import com.bdp.jdbc.base.entity.po.Entity;

import java.util.List;

public interface Converter<E extends Entity,V> {

    V domain2dto(E entity);
    List<V> domain2dto(List<E> entitys);

    E dto2domain(V domain);
    List<E> dto2domain(List<V> domains);
}
