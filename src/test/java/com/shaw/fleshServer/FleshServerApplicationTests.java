package com.shaw.fleshServer;

import com.shaw.fleshServer.entity.TblUser;
import com.shaw.fleshServer.mapper.TblUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.Reader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FleshServerApplicationTests {

	@Test
	public void contextLoads() throws IOException {
		SqlSessionFactory factory = new DefaultSqlSessionFactory(new Configuration());

		TblUser user = new TblUser();
		user.setIsVip("1");
		user.setPassword("");
		user.setUserId("000000000");
		user.setUserName("EMULATOR");

		SqlSession sqlSession = factory.openSession();
		try {
			TblUserMapper mapper = sqlSession.getMapper(TblUserMapper.class);
			mapper.addUser(user);
			mapper.updateUser(user);
		} finally {
			sqlSession.close();
		}
	}

}
