package io.fouad.tests;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
    @PropertySource("classpath:application.properties"),
    @PropertySource("classpath:application-test.properties")
})
@TestConfiguration(proxyBeanMethods = false)
public class TestRunConfig {}