package com.bdp.base.project.test.convert;

import com.bdp.base.project.test.entity.dto.TestSaveDTO;
import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface TestSaveConvert extends Converter<TestEntity, TestSaveDTO> {
    TestSaveConvert INSTANCE = Mappers.getMapper(TestSaveConvert.class);
    @Mappings({
    })
    @Override
    public TestSaveDTO domain2dto(TestEntity entity);
    @Mappings({
    })
    @Override
    public List<TestSaveDTO> domain2dto(List<TestEntity> entitys);
    @Mappings({
    })
    @Override
    public TestEntity dto2domain(TestSaveDTO domain);
    @Mappings({
    })
    @Override
    public List<TestEntity> dto2domain(List<TestSaveDTO> domains);
}
