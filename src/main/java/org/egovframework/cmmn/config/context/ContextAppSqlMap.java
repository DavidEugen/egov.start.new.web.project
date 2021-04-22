package org.egovframework.cmmn.config.context;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import egovframework.rte.psl.orm.ibatis.SqlMapClientFactoryBean;

/**
 * @ClassName : ContextAppSqlMap.java
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
public class ContextAppSqlMap {
	@Bean
	public SqlMapClientFactoryBean sqlMapClient(DataSource dataSource) {
		SqlMapClientFactoryBean sqlMapClientFactoryBean = new SqlMapClientFactoryBean();
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

		sqlMapClientFactoryBean.setConfigLocation(
			pathMatchingResourcePatternResolver
				.getResource("classpath:/egovframework/sqlmap/example/sql-map-config.xml"));
		sqlMapClientFactoryBean.setDataSource(dataSource);

		return sqlMapClientFactoryBean;
	}
}
