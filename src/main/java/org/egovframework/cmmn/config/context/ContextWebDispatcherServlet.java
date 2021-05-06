package org.egovframework.cmmn.config.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
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

	final static String LOCALE_CHANGE_PRAM_NAME = "language";

	@Bean
	@Override
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() { //@EnableWebMvc 어노테이션을 붙이지 않고
		//RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = super.requestMappingHandlerAdapter();
		requestMappingHandlerAdapter.setWebBindingInitializer(new EgovBindingInitializer());
		return requestMappingHandlerAdapter;
	}

	//TODO 삭제하기
	// 이렇게 등록이 될 것 같으나  그러면 Interceptor가 중복으로 등록이 된다.(
	// WebMvcConfigurationSupport 에서는 Interceptor를 추가할 수 있는 메소드를 따로 빼 놨다.
//	@Bean
//	@Override
//	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//		RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
//		requestMappingHandlerMapping.setInterceptors(localeChangeInterceptor());
//		return requestMappingHandlerMapping;
//	}

	@Bean
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		//super.addInterceptors(registry);
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName(LOCALE_CHANGE_PRAM_NAME);
		return localeChangeInterceptor;
	}

	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		return sessionLocaleResolver ;

	}
	//	쿠키를 이용한 Locale 이용시 사용
//	@Bean
//	public CookieLocaleResolver localeResolver(){
//		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//		return cookieLocaleResolver;
//	}

}
