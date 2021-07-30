package tech.shubhamthe.snippetsharingplatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import tech.shubhamthe.snippetsharingplatform.structure.Code;

import java.util.LinkedHashMap;


@SpringBootApplication
@RestController
public class SnippetSharingPlatformApplication {
	public static LinkedHashMap<String, Code> uuid = new LinkedHashMap<>();
	public static String latest;

	public static void main(String[] args) {
		SpringApplication.run(SnippetSharingPlatformApplication.class, args);
	}

}
