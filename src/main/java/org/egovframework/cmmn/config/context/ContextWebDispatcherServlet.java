package org.egovframework.cmmn.config.context;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import egovframework.example.cmmn.web.EgovBindingInitializer;

@Configuration
@ComponentScan(basePackages = "egovframework", excludeFilters = {
	@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
	@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class),
	@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
})
public class ContextWebDispatcherServlet extends WebMvcConfigurationSupport {

	final static String INTERCEPTOR_LOCALE_CHANGE_PRAM_NAME = "language";

	final static String RESOLVER_DEFAULT_ERROR_VIEW = "cmm/egovError";

	// =====================================================================
	// RequestMappingHandlerAdapter 설정
	// =====================================================================
	@Bean
	@Override
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = super.requestMappingHandlerAdapter();
		requestMappingHandlerAdapter.setWebBindingInitializer(new EgovBindingInitializer());
		return requestMappingHandlerAdapter;
	}

	// =====================================================================
	// RequestMappingHandlerMapping 설정
	// =====================================================================
	// -------------------------------------------------------------
	// RequestMappingHandlerMapping 설정 - Interceptor 추가 - localeChangeInterceptor
	// -------------------------------------------------------------
	@Bean
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName(INTERCEPTOR_LOCALE_CHANGE_PRAM_NAME);
		return localeChangeInterceptor;
	}

	// =====================================================================
	// Resolver 설정
	// =====================================================================
	// -------------------------------------------------------------
	// localeResolver 설정 - SessionLocaleResolver / CookieLocaleResolver 중 선택
	// -------------------------------------------------------------
	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		return sessionLocaleResolver;

	}
	//	쿠키를 이용한 Locale 이용시 사용
	//	@Bean
	//	public CookieLocaleResolver localeResolver(){
	//		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
	//		return cookieLocaleResolver;
	//	}

	// -------------------------------------------------------------
	// HandlerExceptionResolver 설정
	// -------------------------------------------------------------
	@Override
	protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();

		simpleMappingExceptionResolver.setDefaultErrorView(RESOLVER_DEFAULT_ERROR_VIEW);

		Properties mappings = new Properties();
		mappings.setProperty("org.springframework.dao.DataAccessException", "cmmn/dataAccessFailure");
		mappings.setProperty("org.springframework.transaction.TransactionException", "cmmn/transactionFailure");
		mappings.setProperty("egovframework.rte.fdl.cmmn.exception.EgovBizException", "cmmn/egovError");
		mappings.setProperty("org.springframework.security.AccessDeniedException", "cmmn/egovError");

		simpleMappingExceptionResolver.setExceptionMappings(mappings);

		exceptionResolvers.add(simpleMappingExceptionResolver);
	}
}
