package com.homework.swedbank;

import org.springframework.boot.SpringApplication;

public class TestSwedbankApplication {

	public static void main(String[] args) {
		SpringApplication.from(SwedbankApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
