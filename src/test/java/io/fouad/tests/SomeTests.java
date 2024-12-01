package io.fouad.tests;

import io.fouad.AppLauncher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppLauncher.class)
public class SomeTests {
    @Test void someTest() {

    }
}