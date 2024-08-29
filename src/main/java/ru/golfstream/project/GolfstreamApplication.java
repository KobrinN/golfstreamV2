package ru.golfstream.project;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.golfstream.project.service.UserService;

@SpringBootApplication
public class GolfstreamApplication {
	public static void main(String[] args) {
		SpringApplication.run(GolfstreamApplication.class, args);
	}

}
