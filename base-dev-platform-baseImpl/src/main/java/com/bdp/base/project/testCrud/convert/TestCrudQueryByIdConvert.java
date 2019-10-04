/**
* 基础场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:21:07</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testCrud.convert;

import com.bdp.base.project.testCrud.entity.dto.TestCrudQueryByIdResponseDTO;
import com.bdp.base.project.testCrud.entity.po.TestCrudEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TestCrudQueryByIdConvert extends Converter<TestCrudEntity, TestCrudQueryByIdResponseDTO> {
    TestCrudQueryByIdConvert INSTANCE = Mappers.getMapper(TestCrudQueryByIdConvert.class);
    @Mappings({
    })
    @Override
    public TestCrudQueryByIdResponseDTO domain2dto(TestCrudEntity entity);
    @Mappings({
    })
    @Override
    public List<TestCrudQueryByIdResponseDTO> domain2dto(List<TestCrudEntity> entitys);
    @Mappings({
    })
    @Override
    public TestCrudEntity dto2domain(TestCrudQueryByIdResponseDTO domain);
    @Mappings({
    })
    @Override
    public List<TestCrudEntity> dto2domain(List<TestCrudQueryByIdResponseDTO> domains);
}
