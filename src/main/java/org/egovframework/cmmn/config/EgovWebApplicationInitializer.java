package org.egovframework.cmmn.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.egovframework.cmmn.config.context.ContextApp;
import org.egovframework.cmmn.config.context.ContextAppAspect;
import org.egovframework.cmmn.config.context.ContextAppCommon;
import org.egovframework.cmmn.config.context.ContextAppDatasource;
import org.egovframework.cmmn.config.context.ContextAppIdgen;
import org.egovframework.cmmn.config.context.ContextAppMapper;
import org.egovframework.cmmn.config.context.ContextAppProperties;
import org.egovframework.cmmn.config.context.ContextAppSqlMap;
import org.egovframework.cmmn.config.context.ContextAppTransaction;
import org.egovframework.cmmn.config.context.ContextAppValidator;
import org.egovframework.cmmn.filter.HTMLTagFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Class Name : EgovWebApplicationInitializer Description : <Notice> Servlet3.x
 * 을 지원하기 위해 web.xml의 기능을 WebApplicationInitializer 기능으로 처리 </Notice>
 * <Disclaimer> N/A
 *
 * @author 윤주호
 * @since 2021.04.07
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일        수정자           수정내용
 *  -------      -------------  ----------------------
 *   2021.04.07  윤주호           최초 생성
 *      </pre>
 */

public class EgovWebApplicationInitializer implements WebApplicationInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovWebApplicationInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		LOGGER.debug("EgovWebApplicationInitializer START-============================================");
		// -------------------------------------------------------------
		// Egov Web ServletContextListener 설정
		// -------------------------------------------------------------
		servletContext.addListener(new org.egovframework.cmmn.context.EgovWebServletContextListener());

		// -------------------------------------------------------------
		// Spring CharacterEncodingFilter 설정
		// -------------------------------------------------------------
		FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("encodingFilter",
				new org.springframework.web.filter.CharacterEncodingFilter());
		characterEncoding.setInitParameter("encoding", "UTF-8");
		characterEncoding.setInitParameter("forceEncoding", "true");
		characterEncoding.addMappingForUrlPatterns(null, false, "*.do");
		// characterEncoding.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),
		// true, "*.do");

		// -------------------------------------------------------------
		// Spring ServletContextListener 설정
		// -------------------------------------------------------------
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		//rootContext.setConfigLocations(new String[] { "classpath*:egovframework/spring/**/context-*.xml" });
		// rootContext.setConfigLocations(new String[] {
		// "classpath*:egovframework/spring/com/context-*.xml","classpath*:egovframework/spring/com/*/context-*.xml"
		// });
		rootContext.register(
				ContextApp.class,
				ContextAppDatasource.class,
				ContextAppAspect.class,
				ContextAppCommon.class,
				ContextAppSqlMap.class,
				ContextAppMapper.class,
				ContextAppTransaction.class,
				ContextAppIdgen.class,
				ContextAppProperties.class,
				ContextAppValidator.class

		);

		rootContext.refresh();
		rootContext.start();

		servletContext.addListener(new ContextLoaderListener(rootContext));

		// -------------------------------------------------------------
		// Spring ServletContextListener 설정
		// -------------------------------------------------------------
		XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();
		xmlWebApplicationContext.setConfigLocations(new String[] {
				"/WEB-INF/config/egovframework/springmvc/dispatcher-servlet.xml"
		});

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
				new DispatcherServlet(xmlWebApplicationContext));
		dispatcher.addMapping("*.do");
		//dispatcher.addMapping("/"); // Facebook OAuth 사용시 변경
		dispatcher.setLoadOnStartup(1);

		//-------------------------------------------------------------
	    // HTMLTagFilter의 경우는 파라미터에 대하여 XSS 오류 방지를 위한 변환을 처리합니다.
		//-------------------------------------------------------------
	    // HTMLTagFIlter의 경우는 JSP의 <c:out /> 등을 사용하지 못하는 특수한 상황에서 사용하시면 됩니다.
	    // (<c:out />의 경우 뷰단에서 데이터 출력시 XSS 방지 처리가 됨)
		FilterRegistration.Dynamic htmlTagFilter = servletContext.addFilter("htmlTagFilter", new HTMLTagFilter());
		htmlTagFilter.addMappingForUrlPatterns(null, false, "*.do");


		//-------------------------------------------------------------
		// Spring RequestContextListener 설정
		//-------------------------------------------------------------
		servletContext.addListener(new org.springframework.web.context.request.RequestContextListener());

		LOGGER.debug("EgovWebApplicationInitializer END-============================================");
	}

}
