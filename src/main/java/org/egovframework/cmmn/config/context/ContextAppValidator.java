package org.egovframework.cmmn.config.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.springmodules.validation.commons.DefaultValidatorFactory;

/**
 * @ClassName : ContextAppValidator.java
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
public class ContextAppValidator {
	@Bean
	public DefaultBeanValidator beanValidator(DefaultValidatorFactory validatorFactory) {
		DefaultBeanValidator defaultBeanValidator = new DefaultBeanValidator();
		defaultBeanValidator.setValidatorFactory(validatorFactory);
		return defaultBeanValidator;
	}

	@Bean
	public DefaultValidatorFactory validatorFactory() {
		DefaultValidatorFactory defaultValidatorFactory= new DefaultValidatorFactory();

		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

		Resource[] validationConfigLocations = new Resource[]{
			pathMatchingResourcePatternResolver.getResource("classpath:egovframework/validator/validator-rules.xml") ,
			pathMatchingResourcePatternResolver.getResource("classpath:egovframework/validator/validator.xml")
		};

		defaultValidatorFactory.setValidationConfigLocations(validationConfigLocations);
		return defaultValidatorFactory;

	}

}
