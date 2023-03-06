package devsmilespring.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalTest {
    @Test
    void conditional() {
        //true
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class).run(context -> {
            assertThat(context).hasSingleBean(MyBean.class);
            assertThat(context).hasSingleBean(Config1.class);
        });


        //false
        new ApplicationContextRunner().withUserConfiguration(Config2.class).run(context -> {
            assertThat(context).doesNotHaveBean(MyBean.class);
            assertThat(context).doesNotHaveBean(Config1.class);
        });


    }
    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean myBean(){
            return new MyBean();
        }
    }
    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean(){
            return new MyBean();
        }
    }

    static class MyBean {

    }

    @Retention(RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface TrueConditional {}

    @Retention(RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    @interface FalseConditional {}

    @Retention(RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    static class TrueCondition implements Condition{

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }
    static class FalseCondition implements Condition{

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }
    static class BooleanCondition implements Condition{

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
             Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
             return (Boolean)annotationAttributes.get("value");
        }
    }
}
