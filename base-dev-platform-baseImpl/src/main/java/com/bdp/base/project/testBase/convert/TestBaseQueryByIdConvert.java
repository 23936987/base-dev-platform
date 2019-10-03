/**
* 基础场景-测试基础场景表-这是一个测试基础场景的示例
* <p>完成日期：2019-10-02 19:31:08</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testBase.convert;

import com.bdp.base.project.testBase.entity.dto.TestBaseQueryByIdResponseDTO;
import com.bdp.base.project.testBase.entity.po.TestBaseEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TestBaseQueryByIdConvert extends Converter<TestBaseEntity, TestBaseQueryByIdResponseDTO> {
    TestBaseQueryByIdConvert INSTANCE = Mappers.getMapper(TestBaseQueryByIdConvert.class);
    @Mappings({
    })
    @Override
    public TestBaseQueryByIdResponseDTO domain2dto(TestBaseEntity entity);
    @Mappings({
    })
    @Override
    public List<TestBaseQueryByIdResponseDTO> domain2dto(List<TestBaseEntity> entitys);
    @Mappings({
    })
    @Override
    public TestBaseEntity dto2domain(TestBaseQueryByIdResponseDTO domain);
    @Mappings({
    })
    @Override
    public List<TestBaseEntity> dto2domain(List<TestBaseQueryByIdResponseDTO> domains);
}
