package tech.shubhamthe.snippetsharingplatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import tech.shubhamthe.snippetsharingplatform.exception.ApplicationFailed;
import tech.shubhamthe.snippetsharingplatform.structure.Code;

import java.io.*;

import java.util.LinkedHashMap;

/**  Learn more about Spring and get started here : https://spring.io/quickstart
* 	@SpringBootApplication - Shortcut to enable @EnableAutoConfiguration, @ComponentScan, @Configuration
*
* */

@SpringBootApplication
@RestController
public class SnippetSharingPlatformApplication implements Serializable {
	public static LinkedHashMap<String, Code> uuid = new LinkedHashMap<>();
	public static String latest;

	public static void main(String[] args) throws ApplicationFailed {

		/**
		* Spring container gets started once SpringApplication.run() method is called.
		* Learn more here  : https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-using-springbootapplication-annotation.html
		 */
		SpringApplication.run(SnippetSharingPlatformApplication.class, args);

		loadState();
	}

	/*
	* Function to  load data when application starts
	* Data is saved in form of object using stream.
	*
	* */

	private static void loadState() throws ApplicationFailed { //throws error
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
