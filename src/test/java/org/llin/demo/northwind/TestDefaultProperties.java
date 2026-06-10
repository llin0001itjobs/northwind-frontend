package org.llin.demo.northwind;

import org.junit.jupiter.api.Test;
import org.llin.demo.northwind.config.PropertyDefaultProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDefaultProperties {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(TestConfig.class);   // ← this enables binding

    @Test
    void testDefaultPropertyValues() {
        contextRunner
            .withPropertyValues(
                "server.port=8082",
                "server.servlet.context-path=/northwind"
            )
            .run(context -> {
                PropertyDefaultProperties props = context.getBean(PropertyDefaultProperties.class);

                System.out.println("server.port = " + props.getServer().getPort());
                System.out.println("server.servlet.context-path = " + props.getServer().getServlet().getContextPath());

                assertThat(props.getServer().getPort())
                        .isNotNull()
                        .isEqualTo(8082);

                assertThat(props.getServer().getServlet().getContextPath())
                        .isNotNull()
                        .isEqualTo("/northwind");
            });
    }

    // Tiny helper configuration that makes @ConfigurationProperties work in the runner
    @Configuration
    @EnableConfigurationProperties(PropertyDefaultProperties.class)
    static class TestConfig {
    }
}