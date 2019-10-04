/**
* 基础场景-测试表现-测试基础框架功能
* <p>完成日期：2019-10-04 13:12:42</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.test.convert;

import com.bdp.base.project.test.entity.dto.TestQueryByIdResponseDTO;
import com.bdp.base.project.test.entity.po.TestEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TestQueryByIdConvert extends Converter<TestEntity, TestQueryByIdResponseDTO> {
    TestQueryByIdConvert INSTANCE = Mappers.getMapper(TestQueryByIdConvert.class);
    @Mappings({
    })
    @Override
    public TestQueryByIdResponseDTO domain2dto(TestEntity entity);
    @Mappings({
    })
    @Override
    public List<TestQueryByIdResponseDTO> domain2dto(List<TestEntity> entitys);
    @Mappings({
    })
    @Override
    public TestEntity dto2domain(TestQueryByIdResponseDTO domain);
    @Mappings({
    })
    @Override
    public List<TestEntity> dto2domain(List<TestQueryByIdResponseDTO> domains);
}
