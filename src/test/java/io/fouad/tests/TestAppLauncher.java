package io.fouad.tests;

import io.fouad.AppLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestAppLauncher {
	public static void main(String[] args) {
		SpringApplication.from(AppLauncher::main).with(TestRunConfig.class).run(args);
	}
}