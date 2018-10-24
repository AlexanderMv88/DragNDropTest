package org.DragNDropTest;

import org.DragNDropTest.entity.Employee;
import org.DragNDropTest.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DragNDropTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DragNDropTestApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(EmployeeRepository repo) {
		return args -> {
			repo.save(new Employee("Александр"));
		};
	}
}
