package org.esupportail.helpdesk.data.web.config;

import org.esupportail.helpdesk.data.dao.config.DaoConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


@Configuration
@Import({ DaoConfig.class })
@PropertySource(value = "file:${config.location}")
public class ContextConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();

		final Resource[] resources = new Resource[] {
				new ClassPathResource("/properties/defaults.properties")};
		pspc.setLocations(resources);
		pspc.setIgnoreUnresolvablePlaceholders(true);

		return pspc;
	}

}
