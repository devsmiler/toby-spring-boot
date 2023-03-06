package devsmilespring.config.autoconfig;

import devsmilespring.config.ConditionalMyOnClass;
import devsmilespring.config.EnableMyConfigurationProperties;
import devsmilespring.config.MyAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@EnableMyConfigurationProperties(ServerProperties.class)
public class TomcatWebServerConfig {
    @Value("${contextPath:}")
    String contextPath;

    @Value("${port:8080}")
    int port;

    @Bean("TomcatWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(ServerProperties properties) {
        TomcatServletWebServerFactory t = new TomcatServletWebServerFactory();
        t.setContextPath(properties.getContextPath());
        t.setPort(properties.getPort());
        return t;
    }

}
