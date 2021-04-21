package org.egovframework.cmmn.config.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;

import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import egovframework.rte.fdl.cmmn.trace.handler.DefaultTraceHandler;
import egovframework.rte.fdl.cmmn.trace.manager.DefaultTraceHandleManager;

/**
 * @ClassName : ContextAppCommon.java 
 * @Description :
 *
 * @author : 윤주호
 * @since  : 2021. 4. 21
 * @version : 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자               수정내용
 *  -------------  ------------   ---------------------
 *   2021. 4. 21    윤주호               최초 생성
 * </pre>
 *
 */
@Configuration
@ComponentScan(
		basePackages="egovframework",
		excludeFilters = {
				@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Controller.class),
				@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Configuration.class) //useDefaultFilters속성 default 값 true
				
		}
)
public class ContextAppCommon {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasenames(
				"classpath:/egovframework/message/message-common",
				"classpath:/egovframework/rte/fdl/idgnr/messages/idgnr",
				"classpath:/egovframework/rte/fdl/property/messages/properties"
		);
		reloadableResourceBundleMessageSource.setCacheSeconds(60);
		return reloadableResourceBundleMessageSource;
	}
	
	@Bean
	public LeaveaTrace leaveaTrace(DefaultTraceHandleManager traceHandlerServices) {
		LeaveaTrace leaveaTrace = new LeaveaTrace();
		leaveaTrace.setTraceHandlerServices(new DefaultTraceHandleManager[] {traceHandlerServices});
		return leaveaTrace;
	}
	
	@Bean
	public DefaultTraceHandleManager traceHandlerService(AntPathMatcher antPathMater, DefaultTraceHandler defaultTraceHandler) {
		DefaultTraceHandleManager defaultTraceHandleManager = new DefaultTraceHandleManager();
		defaultTraceHandleManager.setReqExpMatcher(antPathMater);
		defaultTraceHandleManager.setPatterns(new String[] {"*"});
		defaultTraceHandleManager.setHandlers(new DefaultTraceHandler [] {defaultTraceHandler});
		
		return defaultTraceHandleManager;
	}
	
	@Bean
	public AntPathMatcher antPathMater() {
		AntPathMatcher antPathMater = new AntPathMatcher();
		return antPathMater;
	}
	
	@Bean
	public DefaultTraceHandler defaultTraceHandler() {
		DefaultTraceHandler defaultTraceHandler = new DefaultTraceHandler();
		return defaultTraceHandler;
	}
}
