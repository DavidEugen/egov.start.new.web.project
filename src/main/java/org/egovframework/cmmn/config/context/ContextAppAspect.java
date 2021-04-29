package org.egovframework.cmmn.config.context;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.AntPathMatcher;

import egovframework.example.cmmn.EgovSampleExcepHndlr;
import egovframework.example.cmmn.EgovSampleOthersExcepHndlr;
import egovframework.rte.fdl.cmmn.aspect.ExceptionTransfer;
import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import egovframework.rte.fdl.cmmn.exception.manager.DefaultExceptionHandleManager;

/**
 * @ClassName : ContextAppAspect.java
 * @Description :
 *
 * @author : 윤주호
 * @since  : 2021. 4. 19
 * @version : 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자               수정내용
 *  -------------  ------------   ---------------------
 *   2021. 4. 19    윤주호               최초 생성
 * </pre>
 *
 */
@Configuration
@EnableAspectJAutoProxy
public class ContextAppAspect {

	@Bean
	public AopExceptionTransfer aopExceptionTransfer(ExceptionTransfer exceptionTransfer) {
		AopExceptionTransfer aopExceptionTransfer = new AopExceptionTransfer();
		aopExceptionTransfer.setExceptionTransfer(exceptionTransfer);
		return aopExceptionTransfer;
	}

	@Bean
	public ExceptionTransfer exceptionTransfer(
		@Qualifier("defaultExceptionHandlerManager") DefaultExceptionHandleManager defaultExceptionHandlerManager,
		@Qualifier("otherExceptionHandlerManager") DefaultExceptionHandleManager otherExceptionHandlerManager) {
		ExceptionTransfer exceptionTransfer = new ExceptionTransfer();
		exceptionTransfer.setExceptionHandlerService(
			new DefaultExceptionHandleManager[] {defaultExceptionHandlerManager, otherExceptionHandlerManager});
		return exceptionTransfer;
	}

	@Bean
	public DefaultExceptionHandleManager defaultExceptionHandlerManager(AntPathMatcher antPathMater,
		EgovSampleExcepHndlr egovHandler) {
		DefaultExceptionHandleManager defaultExceptionHandleManager = new DefaultExceptionHandleManager();
		defaultExceptionHandleManager.setReqExpMatcher(antPathMater);
		defaultExceptionHandleManager.setPatterns(new String[] {"**service.impl.*"});
		defaultExceptionHandleManager.setHandlers(new ExceptionHandler[] {egovHandler});
		return defaultExceptionHandleManager;
	}

	@Bean
	public DefaultExceptionHandleManager otherExceptionHandlerManager(AntPathMatcher antPathMater,
		EgovSampleOthersExcepHndlr otherHandler) {
		DefaultExceptionHandleManager otherExceptionHandleManager = new DefaultExceptionHandleManager();
		otherExceptionHandleManager.setReqExpMatcher(antPathMater);
		otherExceptionHandleManager.setPatterns(new String[] {"**service.impl.*"});
		otherExceptionHandleManager.setHandlers(new ExceptionHandler[] {otherHandler});
		return otherExceptionHandleManager;
	}

	@Bean
	public EgovSampleExcepHndlr egovHandler() {
		EgovSampleExcepHndlr egovSampleExcepHndlr = new EgovSampleExcepHndlr();
		return egovSampleExcepHndlr;
	}

	@Bean
	public EgovSampleOthersExcepHndlr otherHandler() {
		return new EgovSampleOthersExcepHndlr();
	}
}
