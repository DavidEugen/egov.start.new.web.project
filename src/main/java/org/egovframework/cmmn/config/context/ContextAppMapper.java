package org.egovframework.cmmn.config.context;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import egovframework.rte.psl.dataaccess.mapper.MapperConfigurer;

/**
 * @ClassName : ContextAppMapper.java
 * @Description :
 *
 * @author : 윤주호
 * @since  : 2021. 4. 22
 * @version : 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자               수정내용
 *  -------------  ------------   ---------------------
 *   2021. 4. 22    윤주호               최초 생성
 * </pre>
 *
 */

@Configuration
public class ContextAppMapper {

	@Bean
	public MapperConfigurer mapperConfigurer() {
		MapperConfigurer mapperConfigurer = new MapperConfigurer();
		mapperConfigurer.setBasePackage("egovframework.example.sample.service.impl");
		return mapperConfigurer;
	}

	@Bean
	public SqlSessionFactoryBean sqlSession(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);

		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

		sqlSessionFactoryBean.setConfigLocation(pathMatchingResourcePatternResolver
			.getResource("classpath:/egovframework/sqlmap/example/sql-mapper-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(
			pathMatchingResourcePatternResolver.getResources("classpath:/egovframework/sqlmap/example/mappers/*.xml"));
		return sqlSessionFactoryBean;
	}

}
