package mybatis.dao;

import mybatis.dto.TestDTO;
import mybatis.factory.MyBatisSessionFactory;
import mybatis.mapper.TestMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TestDAO {
    public void readTest() {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        TestDTO testDTO = new TestDTO();
        testDTO.setName("name1");
        testDTO.setAge(20);
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        int result = mapper.insertTest(testDTO);

        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("데이터 저장 오류");
        }
        sqlSession.close();
    }

    public List<TestDTO> selectAllTest() {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        List<TestDTO> testList = mapper.selectAll();
        sqlSession.close();
        return testList;
    }
}

