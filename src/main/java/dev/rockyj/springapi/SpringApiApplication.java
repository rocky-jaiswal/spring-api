package dev.rockyj.springapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import reactor.blockhound.BlockHound;

@SpringBootApplication
public class SpringApiApplication {

	public static void main(String[] args) {
		// BlockHound.install();
		SpringApplication.run(SpringApiApplication.class, args);
	}

}
