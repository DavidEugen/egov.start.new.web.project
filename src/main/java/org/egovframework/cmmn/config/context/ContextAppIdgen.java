package org.egovframework.cmmn.config.context;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.EgovIdGnrStrategy;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import egovframework.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;

/**
 * @ClassName : ContextAppIdgen.java
 * @Description :
 *
 * @author : 윤주호
 * @since  : 2021. 4. 29
 * @version : 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자               수정내용
 *  -------------  ------------   ---------------------
 *   2021. 4. 29    윤주호               최초 생성
 * </pre>
 *
 */

@Configuration
public class ContextAppIdgen {

	@Bean(destroyMethod = "destroy")
	public EgovTableIdGnrServiceImpl egovIdGnrService(DataSource datasource, EgovIdGnrStrategy strategy) {
		EgovTableIdGnrServiceImpl egovTableIdGnrServiceImpl = new EgovTableIdGnrServiceImpl();

		egovTableIdGnrServiceImpl.setDataSource(datasource);
		egovTableIdGnrServiceImpl.setStrategy(strategy);

		egovTableIdGnrServiceImpl.setBlockSize(10);
		egovTableIdGnrServiceImpl.setTable("IDS");	//ID 생성을 위한 테이블 명
		egovTableIdGnrServiceImpl.setTableName("SAMPLE");	//

		return egovTableIdGnrServiceImpl;
	}

	@Bean
	public EgovIdGnrStrategyImpl mixPrefixSample() {
		EgovIdGnrStrategyImpl egovIdGnrStrategyImpl = new EgovIdGnrStrategyImpl();

		egovIdGnrStrategyImpl.setPrefix("SAMPLE-");
		egovIdGnrStrategyImpl.setCipers(5);	//자리수
		egovIdGnrStrategyImpl.setFillChar('0');	//채울 캐릭터

		return egovIdGnrStrategyImpl;
	}

}
