package org.egovframework.cmmn.config.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.property.impl.EgovPropertyServiceImpl;

/**
 * @ClassName : ContextAppProperties.java
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
public class ContextAppProperties {
	@Bean
	public EgovPropertyServiceImpl propertiesService() {
		EgovPropertyServiceImpl egovPropertyServiceImpl = new EgovPropertyServiceImpl();

		Map<String, String> properties = new HashMap<String,String>();
		properties.put("pageUnit", "10");
		properties.put("pageSize", "10");

		egovPropertyServiceImpl.setProperties(properties );

		return egovPropertyServiceImpl;
	}

}
