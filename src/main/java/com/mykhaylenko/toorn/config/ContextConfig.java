package com.mykhaylenko.toorn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by pavlo.mykhaylenko on 8/12/2015.
 */
@Configuration
@ComponentScan(basePackages = { "com.mykhaylenko.toorn" },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
@Import({ DbConfig.class })
public class ContextConfig {
}
