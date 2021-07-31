package tech.shubhamthe.snippetsharingplatform.applicationprocesses;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.shubhamthe.snippetsharingplatform.SnippetSharingPlatformApplication;
import tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.getrequests.GetMethodsCommClass;
import tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.getrequests.apirequests.GetApiMethodsCommClass;
import tech.shubhamthe.snippetsharingplatform.applicationprocesses.crudoperations.postrequest.PostMethodsCommClass;
import tech.shubhamthe.snippetsharingplatform.exception.ApplicationFailed;

/**
* PlatformController class will listen to  any GET/POST request and will act according to mapping
* provided above each function
* Only public class user will interact directly
* Contains method both for api's and html
* */

/**
* his annotation serves as a specialization of @Component,
* allowing for implementation classes to be autodetected through classpath scanning.
* Implements all the basic features of a core spring framework like Inversion of Control, Dependency Injection*/
@Controller
/**
 * Know more here https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/EnableAutoConfiguration.html
 * */
@EnableAutoConfiguration
public class PlatformController extends SnippetSharingPlatformApplication {

/*
* Objects of respective public communicating class to call necessary methods when required
* static as they are common for each process/object
* */
    static GetMethodsCommClass viewer = new GetMethodsCommClass();
    static PostMethodsCommClass apiWorker = new PostMethodsCommClass();
    static GetApiMethodsCommClass apiRequester = new GetApiMethodsCommClass();

/*
* About  @GetMapping */

    // Listen @localhost:8889 for the landing page call viewer.landingPage() which returns ResponseEntity<T> With status 200
    @GetMapping(value = "/" , produces = "text/html")
    public ResponseEntity<String> landingPage() {
       return viewer.landingPage();
    }

    // Helps User to create and store new snippet
    @GetMapping(value = "/code/new", produces = "text/html")
    public ResponseEntity<String> submitNewCode() {
        return viewer.submitNewCode();
    }


    // After Responding with status : 200 by ApiCodeNew().
    // This method is called from userInput.html with the help of function showConfirmPage()
    @GetMapping(value = "/code/confirm.html")
    public ResponseEntity<String> showConfirmPage() {
        return viewer.showConfirmPage();
    }



    // Listen @localhost:8889/code/uuid-uuid-.. for the finding code snippet based on UUID. It cals
    // viewer.getNthCode which returns ResponseEntity<T>
    @GetMapping(value = "/code/{uuidForCode}", produces = "text/html")
    public ResponseEntity<String> getNthCode(@PathVariable String uuidForCode) {
        return viewer.getNthCode(uuidForCode);
    }

    //Returns 10 random latest snippet
    @GetMapping(value = "/code/latest", produces = "text/html")
    public ResponseEntity<String> getLatest() {
        return viewer.getRandomLatest();
    }



    // Listen @localhost:8889/api/code/new which is called when send() func is triggered in UserInput.html
    // Helps in creating and saving data entered by user
    @PostMapping(value = "api/code/new", consumes = "application/json")
    public ResponseEntity<String> getApiCodeNew(@RequestBody String code) throws ApplicationFailed {

        return apiWorker.getApiCodeNew(code);
    }


    //Returns JSON form of snippet containing author nme, genre, date created, text
    @GetMapping(value = "/api/code/{uuid}")
    public ResponseEntity<String> getApiCodeNth(@PathVariable String uuid) {

        return apiRequester.getApiCodeNth(uuid);
    }

    //Returns JSON form of 10 random snippet containing author nme, genre, date created, text
    @GetMapping(value = "/api/code/latest")
    public ResponseEntity<String> getApiCodeLatest(){

        return apiRequester.getApiRandomLatest();
    }

    //Method is  triggered when there is mapping of the url entered by user
    //  or when user try to visit snippet whose view count/time maximum limit is reached
    @GetMapping(value = "/code/error", produces = "text/html")
    public ResponseEntity<String> getNthCode() {
        return viewer.err();
    }


}
