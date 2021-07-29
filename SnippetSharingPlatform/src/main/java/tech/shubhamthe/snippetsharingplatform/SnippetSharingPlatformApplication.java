package tech.shubhamthe.snippetsharingplatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SnippetSharingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnippetSharingPlatformApplication.class, args);
	}

}
