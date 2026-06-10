package org.llin.demo.northwind;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.llin.demo.northwind.config.PropertyDefaultProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

public class TestDefaultProperties {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(TestConfig.class)
            .withInitializer(new ConfigDataApplicationContextInitializer());

    @Test
    void testDefaultPropertyValues() {
        contextRunner.run(context -> {
            PropertyDefaultProperties props = context.getBean(PropertyDefaultProperties.class);

            // Server section (from main/resources/application.properties)
            assertThat(props.getServer().getPort())
                    .isNotNull()
                    .isEqualTo(8082);

            assertThat(props.getServer().getServlet().getContextPath())
                    .isNotNull()
                    .isEqualTo("/northwind");

            // App section (from main/resources/application.properties)
            assertThat(props.getApp().getApi().getDogs().getImages().getRandom())
                    .isEqualTo("https://dog.ceo/api/breeds/image/random");

            assertThat(props.getApp().getApi().getUsage()).isTrue();
            assertThat(props.getApp().getEmployeeChunkSize()).isEqualTo(7);
            assertThat(props.getApp().getImageBasePath()).isEqualTo("/src/main/resources/");

            // Spring section examples
            assertThat(props.getSpring().getProfiles().getActive()).isEqualTo("dev");
            assertThat(props.getSpring().getMail().getHost()).isEqualTo("smtp.gmail.com");

            System.out.println("✅ All properties from /src/main/resources/application.properties bound successfully!");
        });
    }

    @Configuration
    @EnableConfigurationProperties(PropertyDefaultProperties.class)
    static class TestConfig {
    }
}