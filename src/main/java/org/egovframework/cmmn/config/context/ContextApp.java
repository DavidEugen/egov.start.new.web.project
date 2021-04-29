package org.egovframework.cmmn.config.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName : ContextApp.java
 * @Description :
 *
 * @author : 윤주호
 * @since  : 2021. 4. 14
 * @version : 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자               수정내용
 *  -------------  ------------   ---------------------
 *   2021. 4. 14    윤주호               최초 생성
 * </pre>
 *
 */

@Configuration
@ImportResource(value= {
//		"classpath:egovframework/spring/context-common.xml" ,
//		"classpath:egovframework/spring/context-sqlMap.xml",
//		"classpath:egovframework/spring/context-idgen.xml",
//		"classpath:egovframework/spring/context-properties.xml",
//		"classpath:egovframework/spring/context-aspect.xml",
//		"classpath:egovframework/spring/context-mapper.xml",
//		"classpath:egovframework/spring/context-transaction.xml",
//		"classpath:egovframework/spring/context-datasource.xml",
//		"classpath:egovframework/spring/context-validator.xml"
})
public class ContextApp {

}
