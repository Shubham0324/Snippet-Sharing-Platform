package tech.shubhamthe.snippetsharingplatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RestController;
import tech.shubhamthe.snippetsharingplatform.exception.ApplicationFailed;
import tech.shubhamthe.snippetsharingplatform.structure.Code;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/*  Learn more about Spring and get started here : https://spring.io/quickstart */

@SpringBootApplication
@RestController
public class SnippetSharingPlatformApplication implements Serializable {
	public static LinkedHashMap<String, Code> uuid = new LinkedHashMap<>();
	public static String latest;

	public static void main(String[] args) throws ApplicationFailed {

		SpringApplication.run(SnippetSharingPlatformApplication.class, args);
		loadState();
	}

	private static void loadState() throws ApplicationFailed {
		try{

			FileInputStream fileIn = new FileInputStream("database/database.txt");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			uuid = (LinkedHashMap<String, Code>) objectIn.readObject();
			objectIn.close();
		}catch (IOException | ClassNotFoundException e){
			System.out.println();
		}
	}

}
