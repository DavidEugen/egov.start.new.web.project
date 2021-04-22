package org.egovframework.cmmn.config.context;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @ClassName : ContextAppDatasource.java
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
public class ContextAppDatasource {

	// -------------------------------------------------------------
	// EmbeddedDatabase 이용
	// -------------------------------------------------------------
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:/db/sampledb.sql")
//				.addScript("classpath:/otherpath/other.sql")
				.build();
	}

	// -------------------------------------------------------------
	// Connection Pool 이용
	// -------------------------------------------------------------
//	// hsql (테스트용 메모리 DB)
//	@Bean(destroyMethod="close")
//	public BasicDataSource dataSource() {
//		BasicDataSource basicDataSource = new BasicDataSource();
//		basicDataSource.setDriverClassName("net.sf.log4jdbc.DriverSpy");
//		basicDataSource.setUrl("jdbc:log4jdbc:hsqldb:hsql://localhost/sampledb");
//		basicDataSource.setUsername("sa");
//		return basicDataSource;
//	}

//	// mysql 예제
//	// commons-dbcp, mysql-connector-java 의존성 설정 필요
//	@Bean(destroyMethod="close")
//	public BasicDataSource dataSource() {
//		BasicDataSource basicDataSource = new BasicDataSource();
//		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		basicDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/example");
//		basicDataSource.setUsername("user");
//		basicDataSource.setPassword("password");
//		return basicDataSource;
//	}

//	// oracle 예제
//	// commons-dbcp, ojdbc(라이센스 사항으로 별도로 배포되지 않음) 의존성 설정 필요
//	@Bean(destroyMethod="close")
//	public BasicDataSource dataSource() {
//		BasicDataSource basicDataSource = new BasicDataSource();
//		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		basicDataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:example");
//		basicDataSource.setUsername("user");
//		basicDataSource.setPassword("password");
//		return basicDataSource;
//	}
}
