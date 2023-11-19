package mybatis.mapper;

import mybatis.dto.TestDTO;

import java.util.List;

public interface TestMapper {

    int insertTest(TestDTO dto);

    List<TestDTO> selectAll();
}

