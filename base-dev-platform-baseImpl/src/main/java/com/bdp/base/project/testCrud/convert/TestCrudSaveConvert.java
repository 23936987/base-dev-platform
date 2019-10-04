/**
* CRUD场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:37:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testCrud.convert;

import com.bdp.base.project.testCrud.entity.dto.TestCrudSaveDTO;
import com.bdp.base.project.testCrud.entity.po.TestCrudEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface TestCrudSaveConvert extends Converter<TestCrudEntity, TestCrudSaveDTO> {
    TestCrudSaveConvert INSTANCE = Mappers.getMapper(TestCrudSaveConvert.class);
    @Mappings({
    })
    @Override
    public TestCrudSaveDTO domain2dto(TestCrudEntity entity);
    @Mappings({
    })
    @Override
    public List<TestCrudSaveDTO> domain2dto(List<TestCrudEntity> entitys);
    @Mappings({
    })
    @Override
    public TestCrudEntity dto2domain(TestCrudSaveDTO domain);
    @Mappings({
    })
    @Override
    public List<TestCrudEntity> dto2domain(List<TestCrudSaveDTO> domains);
}
