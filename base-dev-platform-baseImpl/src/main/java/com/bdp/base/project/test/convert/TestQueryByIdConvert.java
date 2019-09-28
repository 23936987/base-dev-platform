package com.bdp.base.project.test.convert;

import com.bdp.base.project.test.entity.dto.TestQueryByIdDTO;
import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TestQueryByIdConvert extends Converter<TestEntity, TestQueryByIdDTO> {
    TestQueryByIdConvert INSTANCE = Mappers.getMapper(TestQueryByIdConvert.class);
    @Mappings({
    })
    @Override
    public TestQueryByIdDTO domain2dto(TestEntity entity);
    @Mappings({
    })
    @Override
    public List<TestQueryByIdDTO> domain2dto(List<TestEntity> entitys);
    @Mappings({
    })
    @Override
    public TestEntity dto2domain(TestQueryByIdDTO domain);
    @Mappings({
    })
    @Override
    public List<TestEntity> dto2domain(List<TestQueryByIdDTO> domains);
}
