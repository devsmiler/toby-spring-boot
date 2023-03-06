//package devsmilespring.config.autoconfig;
//
//import devsmilespring.config.MyAutoConfiguration;
//import org.springframework.boot.context.properties.bind.Binder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.env.Environment;
//
//@MyAutoConfiguration
//public class ServerPropertiesConfig {
//
//    @Bean
//    public ServerProperties serverProperties(Environment environment) {
//        return Binder.get(environment).bind("", ServerProperties.class).get();
//
////        ServerProperties props = new ServerProperties();
////
////        props.setContextPath(environment.getProperty("contextPath"));
////        props.setPort(Integer.parseInt(environment.getProperty("port")));
////        return props;
//
//    }
//}
