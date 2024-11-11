package org.gradle.fins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinsApiApplication {
    public String getRunningStatus() {
        return "FinsApiApplication Running";
    }

	public static void main(String[] args) {
        System.out.println(new FinsApiApplication().getRunningStatus());
		SpringApplication.run(FinsApiApplication.class, args);
	}

}
