package net.koreate.test.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages= {"net.koreate.test.dao"})
public class RootConfig {
	
	@Inject
	ApplicationContext context;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Seoul");
		ds.setUsername("java");
		ds.setPassword("java");
		return ds;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setConfigLocation(context.getResource("classpath:/MyBatisConfig.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSession sqlSession() throws Exception{
		SqlSession session = new SqlSessionTemplate(sqlSessionFactory());
		return session;
	}
}
