package devsmilespring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void successHelloController() {
        HelloController helloController = new HelloController(name -> name);

        String result = helloController.hello("Test");
        Assertions.assertThat(result).isEqualTo("Test");
    }
    @Test
    void failHelloController() {
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("    ");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
